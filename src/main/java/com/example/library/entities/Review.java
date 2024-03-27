package com.example.library.entities;

import com.example.library.enums.Rating;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userReviews;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book bookReviews;
    @Enumerated(EnumType.STRING)
    private Rating rating;
    private String comment;
    private Date reviewDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(User userReviews) {
        this.userReviews = userReviews;
    }

    public Book getBookReviews() {
        return bookReviews;
    }

    public void setBookReviews(Book bookReviews) {
        this.bookReviews = bookReviews;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
