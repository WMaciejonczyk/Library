package com.example.library.repositories;

import com.example.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Book entities.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
