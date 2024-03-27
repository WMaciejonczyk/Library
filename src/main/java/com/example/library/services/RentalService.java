package com.example.library.services;

import com.example.library.DTO.RentalDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Rental;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.RentalRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void addRental(RentalDTO rentalDTO) {
        Rental rental = mapDTOToRental(rentalDTO);
        Book book = bookRepository.findById(rentalDTO.getBookId()).get();
        rentalRepository.save(rental);
        book.setBookRentals(rental);
        bookRepository.save(book);
    }

    public Iterable<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    private Rental mapDTOToRental(RentalDTO rentalDTO) {
        var rental = new Rental();
        if (rental.getBooks() == null) {
            rental.setBooks(new ArrayList<>());
        }
        rental.setUserRentals(userRepository.findById(rentalDTO.getUserId()).get());
        rental.getBooks().add(bookRepository.findById(rentalDTO.getBookId()).get());
        rental.setRentDate(rentalDTO.getRentDate());
        rental.setDueDate(rentalDTO.getDueDate());
        rental.setReturnDate(rentalDTO.getReturnDate());
        return rental;
    }
}
