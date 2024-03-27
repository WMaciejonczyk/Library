package com.example.library.controllers;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.BookDetailsDTO;
import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody Book addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PostMapping("/addDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO) {
        return bookService.addBookDetails(bookDetailsDTO);
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getAllDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<BookDetails> getAllBookDetails() {
        return bookService.getAllBookDetails();
    }

}
