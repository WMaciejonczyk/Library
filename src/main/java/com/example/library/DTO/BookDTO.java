package com.example.library.DTO;

/**
 * Data Transfer Object for book.
 */
public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer publishYear;
    private Integer availableCopies;

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
}
