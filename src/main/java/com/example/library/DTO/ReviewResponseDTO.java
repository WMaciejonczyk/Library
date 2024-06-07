package com.example.library.DTO;

import java.sql.Date;

public class ReviewResponseDTO {
    private Integer id;
    private String rating;
    private String comment;
    private Date reviewDate;
    private Integer userId;
    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user ID of the review.
     *
     * @return the user ID of the review
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the review.
     *
     * @param userId the user ID of the review
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the book ID of the review.
     *
     * @return the book ID of the review
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book ID of the review.
     *
     * @param bookId the book ID of the review
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the comment of the review.
     *
     * @return the comment of the review
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the review.
     *
     * @param comment the comment of the review
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the rating of the review.
     *
     * @return the rating of the review
     */
    public String getRating() {
        return rating;
    }

    /**
     * Sets the rating of the review.
     *
     * @param rating the rating of the review
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
