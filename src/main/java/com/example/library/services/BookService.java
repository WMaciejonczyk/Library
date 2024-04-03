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

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDetailsRepository bookDetailsRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BookService(BookRepository bookRepository, BookDetailsRepository bookDetailsRepository) {
        this.bookRepository = bookRepository;
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public Book addBook(BookDTO bookDTO) throws Exception {
        if (bookDTO.getPublishYear() > LocalDate.now().getYear() || bookDTO.getPublishYear() < 1970) { // no ISBN before then
            throw new InvalidInputException("Invalid publish year.");
        }
        else if (bookDTO.getAvailableCopies() < 0) {
            throw new InvalidInputException("Number of available copies cannot be negative.");
        }
        Book book = modelMapper.map(bookDTO, Book.class);
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() throws EmptyRepositoryException {
        if (bookRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered books.");
        }
        return bookRepository.findAll();
    }

    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO) throws Exception {
        if (!bookRepository.existsById(bookDetailsDTO.getBookId())) {
            throw new InvalidInputException("There is no book with entered id.");
        }
        BookDetails bookDetails = mapDTOToBookDetails(bookDetailsDTO);
        return bookDetailsRepository.save(bookDetails);
    }

    public Iterable<BookDetails> getAllBookDetails() throws EmptyRepositoryException {
        if (bookDetailsRepository.findAll().isEmpty()) {
            throw new EmptyRepositoryException("There are not any registered book details.");
        }
        return bookDetailsRepository.findAll();
    }

    private BookDetails mapDTOToBookDetails(BookDetailsDTO bookDetailsDTO) {
        var bookDetails = new BookDetails();
        bookDetails.setBookDetails(bookRepository.findById(bookDetailsDTO.getBookId()).get());
        bookDetails.setGenre(bookDetailsDTO.getGenre());
        bookDetails.setDescription(bookDetailsDTO.getDescription());
        bookDetails.setCoverImageURL(bookDetailsDTO.getCoverImageURL());
        return bookDetails;
    }
}
