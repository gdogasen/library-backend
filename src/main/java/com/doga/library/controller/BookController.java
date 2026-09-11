package com.doga.library.controller; //java dosyada hangi package icinde oldugu

import com.doga.library.entity.Book; //booku import ediyoruz
import com.doga.library.service.BookService; //book service import ediyoruz
import org.springframework.web.bind.annotation.*; //web annotationları import
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List; //list import

import jakarta.validation.Valid;


@RestController //http üzerinden gelen isteklere cevap verecek. REST Controlller
@RequestMapping("/books")
@CrossOrigin(origins = {
        "http://localhost:8081",
        "https://library-keeper-65.lovable.app"
})

public class BookController {

    private final BookService bookService; //Spring'in verdiği BookService'i kendi bookService değişkenime kaydet.

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping //get isteği geldiğinde metodu çalıştır

    public List<Book> getAllBooks(){

        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@Valid @RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id,@Valid @RequestBody Book newBook){
        return  bookService.updateBook(id,newBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){

        bookService.deleteBook(id);
    }


}