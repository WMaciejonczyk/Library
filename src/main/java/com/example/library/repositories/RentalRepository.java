package com.example.library.repositories;

import com.example.library.entities.Rental;
import com.example.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for managing Rental entities.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    /**
     * Finds all rentals associated with a specific user.
     *
     * @param user the user whose rentals are to be found
     * @return a list of rentals associated with the user
     */
    @Query("SELECT r FROM Rental r WHERE r.userRentals = :user")
    List<Rental> findAllByUser(User user);
}

