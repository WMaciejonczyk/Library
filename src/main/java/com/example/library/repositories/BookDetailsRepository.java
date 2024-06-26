package com.example.library.repositories;

import com.example.library.entities.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing BookDetails entities.
 */
@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer> {

}

