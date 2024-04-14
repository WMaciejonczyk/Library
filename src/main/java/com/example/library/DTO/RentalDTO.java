package com.example.library.DTO;

import java.sql.Date;

/**
 * Data Transfer Object for rental.
 */
public class RentalDTO {
    private Integer bookId;
    private Integer userId;
    private Date dueDate;

    /**
     * Gets the due date of the rental.
     *
     * @return the due date of the rental
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the rental.
     *
     * @param dueDate the due date of the rental
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the book ID of the rental.
     *
     * @return the book ID of the rental
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book ID of the rental.
     *
     * @param bookId the book ID of the rental
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the user ID of the rental.
     *
     * @return the user ID of the rental
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the rental.
     *
     * @param userId the user ID of the rental
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
