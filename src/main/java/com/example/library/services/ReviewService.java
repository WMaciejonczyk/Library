package com.example.library.services;


import com.example.library.DTO.ReviewDTO;
import com.example.library.entities.Review;
import com.example.library.enums.Rating;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.ReviewRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;

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
        if (!userRepository.existsById(reviewDTO.getUserId())) {
            throw new InvalidInputException("There is no user with entered id.");
        }
        else if (!bookRepository.existsById(reviewDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        Review review = mapDTOToReview(reviewDTO);
        return reviewRepository.save(review);
    }

    /**
     * Retrieves all reviews.
     *
     * @return all reviews
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<Review> getAllReviews() throws EmptyRepositoryException {
        if (reviewRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered reviews.");
        }
        return reviewRepository.findAll();
    }

    /**
     * Maps a ReviewDTO to a Review entity.
     *
     * @param reviewDTO the DTO to map
     * @return the mapped Review entity
     */
    private Review mapDTOToReview(ReviewDTO reviewDTO) {
        var review = new Review();
        review.setUserReviews(userRepository.findById(reviewDTO.getUserId()).get());
        review.setBookReviews(bookRepository.findById(reviewDTO.getBookId()).get());
        review.setRating(Rating.valueOf(reviewDTO.getRating().toUpperCase()));
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(Date.valueOf(LocalDate.now()));
        return review;
    }
}
