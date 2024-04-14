package com.example.library.DTO;

/**
 * Data Transfer Object for book details.
 */
public class BookDetailsDTO {
    private Integer bookId;
    private String genre;
    private String description;
    private String coverImageURL;

    /**
     * Gets the book ID.
     *
     * @return the book ID
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Sets the book ID.
     *
     * @param bookId the book ID
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the description of the book.
     *
     * @return the description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description the description of the book
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the cover image URL of the book.
     *
     * @return the cover image URL of the book
     */
    public String getCoverImageURL() {
        return coverImageURL;
    }

    /**
     * Sets the cover image URL of the book.
     *
     * @param coverImageURL the cover image URL of the book
     */
    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }
}

