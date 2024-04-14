package com.example.library.entities;

import jakarta.persistence.*;

/**
 * Represents the details of a book.
 */
@Entity
public class BookDetails {

    /**
     * The unique ID of the book details.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The book associated with these details.
     */
    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book bookDetails;

    /**
     * The genre of the book.
     */
    private String genre;

    /**
     * The description of the book.
     */
    private String description;

    /**
     * The URL of the book's cover image.
     */
    private String coverImageURL;

    /**
     * Returns the book associated with these details.
     *
     * @return the book details
     */
    public Book getBookDetails() {
        return bookDetails;
    }

    /**
     * Sets the book associated with these details.
     *
     * @param bookDetails the book details to set
     */
    public void setBookDetails(Book bookDetails) {
        this.bookDetails = bookDetails;
    }

    /**
     * Returns the genre of the book.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns the description of the book.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the URL of the book's cover image.
     *
     * @return the cover image URL
     */
    public String getCoverImageURL() {
        return coverImageURL;
    }

    /**
     * Sets the URL of the book's cover image.
     *
     * @param coverImageURL the cover image URL to set
     */
    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    /**
     * Returns the unique ID of the book details.
     *
     * @return the ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique ID of the book details.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
