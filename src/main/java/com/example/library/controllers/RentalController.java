package com.example.library.controllers;

import com.example.library.DTO.RentalDTO;
import com.example.library.DTO.RentalResponseDTO;
import com.example.library.entities.Rental;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing rentals.
 */
@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    /**
     * Constructor for RentalController.
     *
     * @param rentalService the service to handle rental operations
     */
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * Endpoint to add a new rental.
     *
     * @param rentalDTO the rental data transfer object
     * @throws ResponseStatusException if parameters of the rental are invalid
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody void addRental(@RequestBody RentalDTO rentalDTO) throws ResponseStatusException {
        try {
            rentalService.addRental(rentalDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to end a rental by id.
     *
     * @param id the id of the rental to end
     * @throws ResponseStatusException if the id is invalid
     */
    @PostMapping("/end/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody void endRental(@PathVariable String id) throws ResponseStatusException {
        try {
            rentalService.endRental(Integer.parseInt(id));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get all rentals.
     *
     * @return all rentals
     * @throws ResponseStatusException if the rental repository is empty
     */
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<RentalResponseDTO> getAllRentals() throws ResponseStatusException {
        try {
            return rentalService.getAllRentals();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get user rentals.
     *
     * @return rentals of the user
     * @throws ResponseStatusException if the rental repository is empty
     */
    @GetMapping("/showHistory")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Rental> getUserRentals() throws ResponseStatusException {
        try {
            return rentalService.getUserRentals();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}