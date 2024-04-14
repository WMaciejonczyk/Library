package com.example.library.services;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.BookDetailsDTO;
import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import com.example.library.exceptions.EmptyRepositoryException;
import com.example.library.exceptions.InvalidInputException;
import com.example.library.repositories.BookDetailsRepository;
import com.example.library.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


/**
 * Service for managing books.
 * This service provides methods for adding, deleting, retrieving, and updating books.
 */
@Service
public class BookService {

    /**
     * The repository for accessing books.
     */
    private final BookRepository bookRepository;

    /**
     * The repository for accessing book details.
     */
    private final BookDetailsRepository bookDetailsRepository;

    /**
     * The model mapper for mapping between DTO and entity classes.
     */
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Constructs a new BookService with the specified repositories.
     *
     * @param bookRepository the repository for accessing books
     * @param bookDetailsRepository the repository for accessing book details
     */
    @Autowired
    public BookService(BookRepository bookRepository, BookDetailsRepository bookDetailsRepository) {
        this.bookRepository = bookRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

    /**
     * Adds a new book.
     *
     * @param bookDTO the DTO of the book to add
     * @return the added book
     * @throws InvalidInputException if the input is invalid
     */
    public Book addBook(BookDTO bookDTO) throws InvalidInputException {
        if (bookDTO.getPublishYear() > LocalDate.now().getYear() || bookDTO.getPublishYear() < 1970) { // no ISBN before then
            throw new InvalidInputException("Invalid publish year.");
        }
        else if (bookDTO.getAvailableCopies() < 0) {
            throw new InvalidInputException("Number of available copies cannot be negative.");
        }
        Book book = modelMapper.map(bookDTO, Book.class);
        return bookRepository.save(book);
    }

    /**
     * Deletes a book.
     *
     * @param id the ID of the book to delete
     * @throws InvalidInputException if the input is invalid
     */
    public void deleteBook(int id) throws InvalidInputException {
        if (!bookRepository.existsById(id)) {
            throw new InvalidInputException("There is no book in catalog with id: " + id + ".");
        }
        bookRepository.deleteById(id);
    }

    /**
     * Retrieves all books.
     *
     * @return all books
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<Book> getAllBooks() throws EmptyRepositoryException {
        if (bookRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered books.");
        }
        return bookRepository.findAll();
    }

    /**
     * Retrieves books by author.
     *
     * @param author the author of the books to retrieve
     * @return the books by the specified author
     * @throws InvalidInputException if the input is invalid
     */
    public Iterable<Book> getBookByAuthor(String author) throws InvalidInputException {
        if (bookRepository.findAll().stream().noneMatch(book -> book.getAuthor().equals(author))) {
            throw new InvalidInputException("There are not any books written by this author.");
        }
        List<Book> list = bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();
        for (Book book : list) {
            book.setBookRentals(null);
        }
        return list;
    }

    /**
     * Retrieves a book by ISBN.
     *
     * @param isbn the ISBN of the book to retrieve
     * @return the book with the specified ISBN
     * @throws InvalidInputException if the input is invalid
     */
    public Book getBookByIsbn(String isbn) throws InvalidInputException {
        if (bookRepository.findAll().stream().noneMatch(book -> book.getIsbn().equals(isbn))) {
            throw new InvalidInputException("There is not a book with this isbn.");
        }
        Book book = bookRepository.findAll().stream()
                .filter(book1 -> book1.getIsbn().equals(isbn))
                .toList().get(0);
        book.setBookRentals(null);
        return book;
    }

    /**
     * Retrieves a book by title.
     *
     * @param title the title of the book to retrieve
     * @return the book with the specified title
     * @throws InvalidInputException if the input is invalid
     */
    public Book getBookByTitle(String title) throws InvalidInputException {
        if (bookRepository.findAll().stream().noneMatch(book -> book.getIsbn().equals(title))) {
            throw new InvalidInputException("There is not a book with this title.");
        }
        Book book = bookRepository.findAll().stream()
                .filter(book1 -> book1.getIsbn().equals(title))
                .toList().get(0);
        book.setBookRentals(null);
        return book;
    }

    /**
     * Updates a book.
     *
     * @param id the ID of the book to update
     * @param title the new title
     * @param publisher the new publisher
     * @param isbn the new ISBN
     * @param availableCopies the new number of available copies
     * @return the updated book
     * @throws InvalidInputException if the input is invalid
     */
    public Book updateBook(int id, String title, String publisher, String isbn, int availableCopies) throws InvalidInputException {
        if (!bookRepository.existsById(id)) {
            throw new InvalidInputException("There is no book in catalog with id: " + id + ".");
        }
        if (title == null && publisher == null && isbn == null && availableCopies < 0) {
            throw new InvalidInputException("There is not any info to update the book with.");
        }
        Book book = bookRepository.findById(id).get();
        if (title != null) {
            book.setTitle(title);
        }
        if (publisher != null) {
            book.setPublisher(publisher);
        }
        if (isbn != null) {
            book.setIsbn(isbn);
        }
        if (availableCopies >= 0) {
            book.setAvailableCopies(availableCopies);
        }
        return bookRepository.save(book);
    }

    /**
     * Adds book details.
     *
     * @param bookDetailsDTO the DTO of the book details to add
     * @return the added book details
     * @throws InvalidInputException if the input is invalid
     */
    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO) throws InvalidInputException {
        if (!bookRepository.existsById(bookDetailsDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        BookDetails bookDetails = mapDTOToBookDetails(bookDetailsDTO);
        return bookDetailsRepository.save(bookDetails);
    }

    /**
     * Retrieves all book details.
     *
     * @return all book details
     * @throws EmptyRepositoryException if the repository is empty
     */
    public Iterable<BookDetails> getAllBookDetails() throws EmptyRepositoryException {
        if (bookDetailsRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered book details.");
        }
        return bookDetailsRepository.findAll();
    }

    /**
     * Maps a BookDetailsDTO to a BookDetails entity.
     *
     * @param bookDetailsDTO the DTO to map
     * @return the mapped BookDetails entity
     */
    private BookDetails mapDTOToBookDetails(BookDetailsDTO bookDetailsDTO) {
        var bookDetails = new BookDetails();
        bookDetails.setBookDetails(bookRepository.findById(bookDetailsDTO.getBookId()).get());
        bookDetails.setGenre(bookDetailsDTO.getGenre());
        bookDetails.setDescription(bookDetailsDTO.getDescription());
        bookDetails.setCoverImageURL(bookDetailsDTO.getCoverImageURL());
        return bookDetails;
    }
}
