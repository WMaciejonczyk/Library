package com.example.library.repositories;

import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends CrudRepository<BookDetails, Integer> {

}
