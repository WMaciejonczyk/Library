package com.example.library.services;

import com.example.library.DTO.ReviewDTO;
import com.example.library.DTO.ReviewResponseDTO;
import com.example.library.entities.Review;
import com.example.library.enums.Rating;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.ReviewRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing reviews.
 * This service provides methods for adding and retrieving reviews.
 */
@Service
public class ReviewService {

    /**
     * The repository for accessing reviews.
     */
    private final ReviewRepository reviewRepository;

    /**
     * The repository for accessing users.
     */
    private final UserRepository userRepository;

    /**
     * The repository for accessing books.
     */
    private final BookRepository bookRepository;

    /**
     * Constructs a new ReviewService with the specified repositories.
     *
     * @param reviewRepository the repository for accessing reviews
     * @param userRepository the repository for accessing users
     * @param bookRepository the repository for accessing books
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new review.
     *
     * @param reviewDTO the DTO of the review to add
     * @return the added review
     * @throws InvalidInputException if the input is invalid
     */
    public Review addReview(ReviewDTO reviewDTO) throws InvalidInputException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = Integer.parseInt(auth.getPrincipal().toString());
        System.out.println(auth.getPrincipal().toString());
        if (!userRepository.existsById(id)) {
            throw new InvalidInputException("There is no user with entered id.");
        }
        else if (!bookRepository.existsById(reviewDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        Review review = mapDTOToReview(id, reviewDTO);
        return reviewRepository.save(review);
    }

    /**
     * Retrieves all reviews.
     *
     * @return all reviews
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<ReviewResponseDTO> getAllReviews() throws EmptyRepositoryException {
        if (reviewRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered reviews.");
        }
        Iterable<Review> reviews = reviewRepository.findAll();
        List<ReviewResponseDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            reviewDTOs.add(mapReviewToDTO(review));
        }
        return reviewDTOs;
    }

    /**
     * Maps a ReviewDTO to a Review entity.
     *
     * @param id id of the user
     * @param reviewDTO the DTO to map
     * @return the mapped Review entity
     */
    private Review mapDTOToReview(int id, ReviewDTO reviewDTO) {
        var review = new Review();
        review.setUserReviews(userRepository.findById(id).get());
        review.setBookReviews(bookRepository.findById(reviewDTO.getBookId()).get());
        review.setRating(Rating.valueOf(reviewDTO.getRating().toUpperCase()));
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(Date.valueOf(LocalDate.now()));
        return review;
    }

    private ReviewResponseDTO mapReviewToDTO(Review review) {
        var reviewDTO = new ReviewResponseDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating().name());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setReviewDate(review.getReviewDate());
        reviewDTO.setBookId(review.getBookReviews().getId());
        reviewDTO.setUserId(review.getUserReviews().getId());
        return reviewDTO;
    }
}
