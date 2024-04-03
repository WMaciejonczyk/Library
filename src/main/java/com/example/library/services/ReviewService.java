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
import java.util.Arrays;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Review addReview(ReviewDTO reviewDTO) throws Exception {
        if (!userRepository.existsById(reviewDTO.getUserId())) {
            throw new InvalidInputException("There is no user with entered id.");
        }
        else if (!bookRepository.existsById(reviewDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        Review review = mapDTOToReview(reviewDTO);
        return reviewRepository.save(review);
    }

    public Iterable<Review> getAllReviews() throws EmptyRepositoryException {
        if (reviewRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered reviews.");
        }
        return reviewRepository.findAll();
    }

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
