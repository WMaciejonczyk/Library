package com.example.library.services;


import com.example.library.DTO.ReviewDTO;
import com.example.library.entities.Review;
import com.example.library.enums.Rating;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.ReviewRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Review addReview(ReviewDTO reviewDTO) {
        Review review = mapDTOToReview(reviewDTO);
        return reviewRepository.save(review);
    }

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    private Review mapDTOToReview(ReviewDTO reviewDTO) {
        var review = new Review();
        review.setUserReviews(userRepository.findById(reviewDTO.getUserId()).get());
        review.setBookReviews(bookRepository.findById(reviewDTO.getBookId()).get());
        review.setRating(Rating.valueOf(reviewDTO.getRating()));
        review.setComment(reviewDTO.getComment());
        review.setReviewDate(reviewDTO.getReviewDate());
        return review;
    }
}
