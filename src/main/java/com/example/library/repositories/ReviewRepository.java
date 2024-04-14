package com.example.library.repositories;

import com.example.library.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Review entities.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
