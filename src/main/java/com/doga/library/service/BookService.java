package com.doga.library.service;

import com.doga.library.dto.BookDTO;
import com.doga.library.entity.Book;
import com.doga.library.exception.ResourceNotFoundException;
import com.doga.library.mapper.BookMapper;
import com.doga.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public Page<BookDTO> getAllBooks(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Book> books;

        if (search == null || search.isBlank()) {
            books = bookRepository.findAll(pageable);
        } else {
            books = bookRepository
                    .findByTitleContainingIgnoreCaseOrIsbnContainingIgnoreCaseOrGenreContainingIgnoreCase(
                            search,
                            search,
                            search,
                            pageable
                    );
        }

        return books.map(bookMapper::toDTO);
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return bookMapper.toDTO(book);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBook) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(newBook.getTitle());
        book.setIsbn(newBook.getIsbn());
        book.setPublicationYear(newBook.getPublicationYear());
        book.setPageCount(newBook.getPageCount());
        book.setGenre(newBook.getGenre());
        book.setAuthor(newBook.getAuthor());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }
}