package com.example.library.controllers;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.BookDetailsDTO;
import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book addBook(@RequestBody BookDTO bookDTO) throws Exception {
        try {
            return bookService.addBook(bookDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @PostMapping("/addDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO) throws Exception {
        try {
            return bookService.addBookDetails(bookDetailsDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Book> getAllBooks() {
        try {
            return bookService.getAllBooks();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAllDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<BookDetails> getAllBookDetails() {
        try {
        return bookService.getAllBookDetails();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

}
