package com.example.library.controllers;

import com.example.library.DTO.RentalDTO;
import com.example.library.entities.Rental;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody void addRental(@RequestBody RentalDTO rentalDTO) throws Exception {
        try {
            rentalService.addRental(rentalDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Rental> getAllRentals() {
        try {
            return rentalService.getAllRentals();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}