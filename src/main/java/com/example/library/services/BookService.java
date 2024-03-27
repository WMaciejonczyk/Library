package com.example.library.services;

import com.example.library.DTO.BookDTO;
import com.example.library.DTO.BookDetailsDTO;
import com.example.library.entities.Book;
import com.example.library.entities.BookDetails;
import com.example.library.repositories.BookDetailsRepository;
import com.example.library.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Book addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDetails addBookDetails(BookDetailsDTO bookDetailsDTO) {
        BookDetails bookDetails = mapDTOToBookDetails(bookDetailsDTO);
        return bookDetailsRepository.save(bookDetails);
    }

    public Iterable<BookDetails> getAllBookDetails() {
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
