package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Entity representing a book.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer publishYear;
    private Integer availableCopies;
    @JsonIgnore
    @OneToMany(mappedBy = "bookRentals", cascade = CascadeType.ALL)
    private List<Rental> rental;
    @JsonIgnore
    @OneToMany(mappedBy = "bookReviews", cascade = CascadeType.ALL)
    private List<Review> review;
    @OneToOne(mappedBy = "bookDetails")
    private BookDetails bookDetails;

    /**
     * Gets the ID of the book.
     *
     * @return the ID of the book
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     *
     * @param id the ID of the book
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher the publisher of the book
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the publish year of the book.
     *
     * @return the publish year of the book
     */
    public Integer getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publish year of the book.
     *
     * @param publishYear the publish year of the book
     */
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Gets the available copies of the book.
     *
     * @return the available copies of the book
     */
    public Integer getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Sets the available copies of the book.
     *
     * @param availableCopies the available copies of the book
     */
    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    /**
     * Gets the rentals of the book.
     *
     * @return the rentals of the book
     */
    public List<Rental> getBookRentals() {
        return rental;
    }

    /**
     * Sets the rentals of the book.
     *
     * @param rental the rentals of the book
     */
    public void setBookRentals(List<Rental> rental) {
        this.rental = rental;
    }

    /**
     * Gets the reviews of the book.
     *
     * @return the reviews of the book
     */
    public List<Review> getReview() {
        return review;
    }

    /**
     * Sets the reviews of the book.
     *
     * @param review the reviews of the book
     */
    public void setReview(List<Review> review) {
        this.review = review;
    }

    /**
     * Gets the details of the book.
     *
     * @return the details of the book
     */
    public BookDetails getBookDetails() {
        return bookDetails;
    }

    /**
     * Sets the details of the book.
     *
     * @param bookDetails the details of the book
     */
    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }
}