package com.example.library.DTO;

/**
 * Data Transfer Object for review.
 */
public class ReviewDTO {
    private Integer bookId;
    private String rating;
    private String comment;

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
}
