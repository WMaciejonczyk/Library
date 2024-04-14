package com.example.library.entities;

import com.example.library.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a user of the system.
 */
@Entity
public class User {

    /**
     * The unique ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The full name of the user.
     */
    private String fullName;

    /**
     * The rentals associated with the user.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "userRentals", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    /**
     * The reviews written by the user.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "userReviews", cascade = CascadeType.ALL)
    private List<Review> reviews;

    /**
     * Returns the unique ID of the user.
     *
     * @return the ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of the user.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the rentals associated with the user.
     *
     * @return the rentals
     */
    public List<Rental> getRentals() {
        return rentals;
    }

    /**
     * Sets the rentals associated with the user.
     *
     * @param rentals the rentals to set
     */
    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    /**
     * Returns the reviews written by the user.
     *
     * @return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Sets the reviews written by the user.
     *
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the user.
     *
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
