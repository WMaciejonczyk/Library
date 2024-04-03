package com.example.library.controllers;

import com.example.library.DTO.ReviewDTO;
import com.example.library.entities.Review;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Review addReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
        try {
            return reviewService.addReview(reviewDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Review> getAllReviews() {
        try {
            return reviewService.getAllReviews();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}