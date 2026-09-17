package com.doga.library.controller;

import com.doga.library.entity.Author;
import com.doga.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.doga.library.dto.AuthorDTO;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = {
        "http://localhost:8081",
        "https://library-keeper-65.lovable.app"
})

public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){ this.authorService = authorService;}


    @GetMapping

    public List<AuthorDTO> getAllAuthors(){

        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author createAuthor(@Valid @RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id,@Valid @RequestBody Author newAuthor) {
        return authorService.updateAuthor(id, newAuthor);
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor (@PathVariable Long id){
        authorService.deleteAuthor(id);
    }
}

