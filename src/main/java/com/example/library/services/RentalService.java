package com.example.library.services;

import com.example.library.DTO.RentalDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Rental;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.RentalRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void addRental(RentalDTO rentalDTO) throws Exception {
        if (!userRepository.existsById(rentalDTO.getUserId())) {
            throw new InvalidInputException("There is no user with entered id.");
        }
        else if (!bookRepository.existsById(rentalDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        else if (rentalDTO.getDueDate().before(Date.valueOf(LocalDate.now())) || rentalDTO.getReturnDate().before(Date.valueOf(LocalDate.now()))) {
            throw new InvalidInputException("Invalid date parameters.");
        }
        Rental rental = mapDTOToRental(rentalDTO);
        Book book = bookRepository.findById(rentalDTO.getBookId()).get();
        rentalRepository.save(rental);
        book.setBookRentals(rental);
        bookRepository.save(book);
    }

    public Iterable<Rental> getAllRentals() throws EmptyRepositoryException {
        if (rentalRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered rentals.");
        }
        return rentalRepository.findAll();
    }

    private Rental mapDTOToRental(RentalDTO rentalDTO) {
        var rental = new Rental();
        if (rental.getBooks() == null) {
            rental.setBooks(new ArrayList<>());
        }
        rental.setUserRentals(userRepository.findById(rentalDTO.getUserId()).get());
        rental.getBooks().add(bookRepository.findById(rentalDTO.getBookId()).get());
        rental.setRentDate(Date.valueOf(LocalDate.now()));
        rental.setDueDate(rentalDTO.getDueDate());
        rental.setReturnDate(rentalDTO.getReturnDate());
        return rental;
    }
}
