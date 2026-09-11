package com.doga.library.service;

import com.doga.library.entity.Book;
import com.doga.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.doga.library.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book not found"));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id,Book newBook){

        Book book= bookRepository.findById(id).orElseThrow( ()->new ResourceNotFoundException("Book not found"));

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