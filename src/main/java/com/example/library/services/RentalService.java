package com.example.library.services;

import com.example.library.DTO.RentalDTO;
import com.example.library.DTO.RentalResponseDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Rental;
import com.example.library.entities.User;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.RentalRepository;
import com.example.library.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing rentals.
 * This service provides methods for adding, ending, retrieving all rentals and user rentals.
 */
@Service
public class RentalService {

    /**
     * The repository for accessing rentals.
     */
    private final RentalRepository rentalRepository;

    /**
     * The repository for accessing users.
     */
    private final UserRepository userRepository;

    /**
     * The repository for accessing books.
     */
    private final BookRepository bookRepository;

    /**
     * Constructs a new RentalService with the specified repositories.
     *
     * @param rentalRepository the repository for accessing rentals
     * @param userRepository the repository for accessing users
     * @param bookRepository the repository for accessing books
     */
    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new rental.
     *
     * @param rentalDTO the DTO of the rental to add
     * @throws InvalidInputException if the input is invalid
     */
    public void addRental(RentalDTO rentalDTO) throws InvalidInputException {
        if (!userRepository.existsById(rentalDTO.getUserId())) {
            throw new InvalidInputException("There is no user with entered id.");
        }
        else if (!bookRepository.existsById(rentalDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        else if (rentalDTO.getDueDate().before(Date.valueOf(LocalDate.now()))) {
            throw new InvalidInputException("Invalid date parameters.");
        }
        else if (bookRepository.findById(rentalDTO.getBookId()).get().getAvailableCopies() <= 0) {
            throw new InvalidInputException("There are no more available copies of the book.");
        }
        Rental rental = mapDTOToRental(rentalDTO);
        Book book = bookRepository.findById(rental.getBookRentals().getId()).get();
        Integer copies = book.getAvailableCopies();
        book.setAvailableCopies(copies - 1);
        bookRepository.save(book);
        rental.setBookRentals(book);
        rentalRepository.save(rental);
    }

    /**
     * Ends a rental.
     *
     * @param id the ID of the rental to end
     * @throws InvalidInputException if the input is invalid
     */
    public void endRental(int id) throws InvalidInputException {
        if (!rentalRepository.existsById(id)) {
            throw new InvalidInputException("There is no rental with id: " + id);
        }
        Rental rental = rentalRepository.findById(id).get();
        Book book = rental.getBookRentals();
        rental.setReturnDate(Date.valueOf(LocalDate.now()));
        rentalRepository.save(rental);
        Integer copies = book.getAvailableCopies();
        book.setAvailableCopies(copies + 1);
        bookRepository.save(book);

    }

    /**
     * Retrieves all rentals.
     *
     * @return all rentals
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<RentalResponseDTO> getAllRentals() throws EmptyRepositoryException {
        if (rentalRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered rentals.");
        }
        Iterable<Rental> rentals = rentalRepository.findAll();
        List<RentalResponseDTO> rentalDTOs = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalDTOs.add(mapRentalToDTO(rental));
        }
        return rentalDTOs;
    }

    /**
     * Retrieves rentals of a user.
     *
     * @return rentals of a user
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<RentalResponseDTO> getUserRentals() throws EmptyRepositoryException {
        if (rentalRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered rentals.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findById(Integer.parseInt(currentPrincipalName)).get();
        if (rentalRepository.findAllByUser(user).isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered rentals by you.");
        }
        Iterable<Rental> rentals = rentalRepository.findAllByUser(user);
        List<RentalResponseDTO> rentalDTOs = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalDTOs.add(mapRentalToDTO(rental));
        }
        return rentalDTOs;
    }

    /**
     * Maps a RentalDTO to a Rental entity.
     *
     * @param rentalDTO the DTO to map
     * @return the mapped Rental entity
     */
    private Rental mapDTOToRental(RentalDTO rentalDTO) {
        var rental = new Rental();
        rental.setUserRentals(userRepository.findById(rentalDTO.getUserId()).get());
        rental.setBookRentals(bookRepository.findById(rentalDTO.getBookId()).get());
        rental.setRentDate(Date.valueOf(LocalDate.now()));
        rental.setDueDate(rentalDTO.getDueDate());
        rental.setReturnDate(null);
        return rental;
    }

    private RentalResponseDTO mapRentalToDTO(Rental rental) {
        var rentalDTO = new RentalResponseDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setDueDate(rental.getDueDate());
        rentalDTO.setRentDate(rental.getRentDate());
        rentalDTO.setReturnDate(rental.getReturnDate());
        rentalDTO.setBookId(rental.getBookRentals().getId());
        rentalDTO.setUserId(rental.getUserRentals().getId());
        return rentalDTO;
    }
}
