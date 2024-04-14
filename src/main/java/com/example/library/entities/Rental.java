package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

/**
 * Represents a rental of a book.
 */
@Entity
public class Rental {

    /**
     * The unique ID of the rental.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The book associated with this rental.
     */
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book bookRentals;

    /**
     * The user associated with this rental.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User userRentals;

    /**
     * The date the book was rented.
     */
    private Date rentDate;

    /**
     * The due date for returning the book.
     */
    private Date dueDate;

    /**
     * The date the book was returned.
     */
    private Date returnDate;

    /**
     * Returns the unique ID of the rental.
     *
     * @return the ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique ID of the rental.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the book associated with this rental.
     *
     * @return the book rentals
     */
    public Book getBookRentals() {
        return bookRentals;
    }

    /**
     * Sets the book associated with this rental.
     *
     * @param bookRentals the book rentals to set
     */
    public void setBookRentals(Book bookRentals) {
        this.bookRentals = bookRentals;
    }

    /**
     * Returns the user associated with this rental.
     *
     * @return the user rentals
     */
    public User getUserRentals() {
        return userRentals;
    }

    /**
     * Sets the user associated with this rental.
     *
     * @param userRentals the user rentals to set
     */
    public void setUserRentals(User userRentals) {
        this.userRentals = userRentals;
    }

    /**
     * Returns the date the book was rented.
     *
     * @return the rent date
     */
    public Date getRentDate() {
        return rentDate;
    }

    /**
     * Sets the date the book was rented.
     *
     * @param rentDate the rent date to set
     */
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * Returns the due date for returning the book.
     *
     * @return the due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for returning the book.
     *
     * @param dueDate the due date to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the date the book was returned.
     *
     * @return the return date
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the date the book was returned.
     *
     * @param returnDate the return date to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
