package com.example.library.entities;

import com.example.library.enums.Rating;
import jakarta.persistence.*;

import java.sql.Date;

/**
 * Represents a review of a book.
 */
@Entity
public class Review {

    /**
     * The unique ID of the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The user who wrote the review.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userReviews;

    /**
     * The book that the review is about.
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book bookReviews;

    /**
     * The rating given by the user.
     */
    @Enumerated(EnumType.STRING)
    private Rating rating;

    /**
     * The comment written by the user.
     */
    private String comment;

    /**
     * The date the review was written.
     */
    private Date reviewDate;

    /**
     * Returns the unique ID of the review.
     *
     * @return the ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique ID of the review.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the user who wrote the review.
     *
     * @return the user reviews
     */
    public User getUserReviews() {
        return userReviews;
    }

    /**
     * Sets the user who wrote the review.
     *
     * @param userReviews the user reviews to set
     */
    public void setUserReviews(User userReviews) {
        this.userReviews = userReviews;
    }

    /**
     * Returns the book that the review is about.
     *
     * @return the book reviews
     */
    public Book getBookReviews() {
        return bookReviews;
    }

    /**
     * Sets the book that the review is about.
     *
     * @param bookReviews the book reviews to set
     */
    public void setBookReviews(Book bookReviews) {
        this.bookReviews = bookReviews;
    }

    /**
     * Returns the comment written by the user.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment written by the user.
     *
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the date the review was written.
     *
     * @return the review date
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * Sets the date the review was written.
     *
     * @param reviewDate the review date to set
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * Returns the rating given by the user.
     *
     * @return the rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Sets the rating given by the user.
     *
     * @param rating the rating to set
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
