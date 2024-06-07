package com.example.library.controllers;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.BookDetailsDTO;
import com.example.library.DTO.BookDetailsResponseDTO;
import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing books.
 */
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    /**
     * Constructor for BookController.
     *
     * @param bookService the service to handle book operations
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint to add a new book.
     *
     * @param bookDTO the book data transfer object
     * @return the added book
     * @throws ResponseStatusException if parameters of the book are invalid
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book addBook(@RequestBody BookDTO bookDTO) throws ResponseStatusException {
        try {
            return bookService.addBook(bookDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to delete a book by id.
     *
     * @param id the id of the book to delete
     * @throws ResponseStatusException if given id is invalid
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody void deleteBook(@PathVariable("id") String id) throws ResponseStatusException {
        try {
            bookService.deleteBook(Integer.parseInt(id));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get all books.
     *
     * @return all books
     * @throws ResponseStatusException if book repository is empty
     */
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<Book> getAllBooks() throws ResponseStatusException {
        try {
            return bookService.getAllBooks();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get books by author.
     *
     * @param author the author of the books to get
     * @return books by the given author
     * @throws ResponseStatusException if entered author is invalid
     */
    @GetMapping("/getByAuthor")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Book> getBookByAuthor(@RequestParam("author") String author) throws ResponseStatusException {
        try {
            return bookService.getBookByAuthor(author);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get a book by ISBN.
     *
     * @param isbn the ISBN of the book to get
     * @return the book with the given ISBN
     * @throws ResponseStatusException if entered ISBN is invalid
     */
    @GetMapping("/getByIsbn")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Book getBookByIsbn(@RequestParam("isbn") String isbn) throws ResponseStatusException {
        try {
            return bookService.getBookByIsbn(isbn);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get a book by title.
     *
     * @param title the title of the book to get
     * @return the book with the given title
     * @throws ResponseStatusException if entered title is invalid
     */
    @GetMapping("/getByTitle")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Book getBookByTitle(@RequestParam("title") String title) throws ResponseStatusException {
        try {
            return bookService.getBookByTitle(title);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to update a book.
     *
     * @param id the id of the book to update
     * @param title the new title of the book
     * @param publisher the new publisher of the book
     * @param isbn the new ISBN of the book
     * @param availableCopies the new number of available copies of the book
     * @return the updated book
     * @throws ResponseStatusException if entered parameters of updated book are invalid
     */
    @PostMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Book updateBook(@PathVariable("id") String id,
                                         @RequestParam(value = "title", required = false) String title,
                                         @RequestParam(value = "publisher", required = false) String publisher,
                                         @RequestParam(value = "isbn", required = false) String isbn,
                                         @RequestParam(value = "availableCopies", required = false) String availableCopies) throws ResponseStatusException {
        try {
            if (availableCopies == null) {
                return bookService.updateBook(Integer.parseInt(id), title, publisher, isbn, -1);
            }
            return bookService.updateBook(Integer.parseInt(id), title, publisher, isbn, Integer.parseInt(availableCopies));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to add details to a book.
     *
     * @param bookDetailsDTO the book details data transfer object
     * @return the added book details
     * @throws ResponseStatusException if entered parameters of book details are invalid
     */
    @PostMapping("/addDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetails addBookDetails(@RequestBody BookDetailsDTO bookDetailsDTO) throws ResponseStatusException {
        try {
            return bookService.addBookDetails(bookDetailsDTO);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    /**
     * Endpoint to get all book details.
     *
     * @return all book details
     * @throws ResponseStatusException if the book details repository is empty
     */
    @GetMapping("/getAllDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<BookDetailsResponseDTO> getAllBookDetails() throws ResponseStatusException {
        try {
        return bookService.getAllBookDetails();
        } catch (EmptyRepositoryException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

}
