package com.example.library.controllers;

import com.example.library.DTO.ReviewDTO;
import com.example.library.DTO.ReviewResponseDTO;
import com.example.library.entities.Review;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing reviews.
 */
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * Constructor for ReviewController.
     *
     * @param reviewService the service to handle review operations
     */
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Endpoint to add a new review.
     *
     * @param reviewDTO the review data transfer object
     * @return the added review
     * @throws ResponseStatusException if parameters of the review are invalid
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Review addReview(@RequestBody ReviewDTO reviewDTO) throws ResponseStatusException {
        try {
            return reviewService.addReview(reviewDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get all reviews.
     *
     * @return all reviews
     * @throws ResponseStatusException if the review repository is empty
     */
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<ReviewResponseDTO> getAllReviews() throws ResponseStatusException {
        try {
            return reviewService.getAllReviews();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}