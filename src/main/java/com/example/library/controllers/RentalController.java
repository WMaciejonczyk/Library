package com.example.library.controllers;

import com.example.library.DTO.RentalDTO;
import com.example.library.entities.Rental;
import com.example.library.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody void addRental(@RequestBody RentalDTO rentalDTO) {
        rentalService.addRental(rentalDTO);
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }
}