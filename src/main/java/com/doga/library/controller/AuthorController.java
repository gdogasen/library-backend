package com.doga.library.controller;

import com.doga.library.dto.AuthorDTO;
import com.doga.library.entity.Author;
import com.doga.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@CrossOrigin(origins = {
        "http://localhost:8080",
        "http://localhost:8081",
        "https://library-keeper-65.lovable.app"
})
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Page<AuthorDTO> getAllAuthors(
            Pageable pageable,
            @RequestParam(required = false) String search) {

        return authorService.getAllAuthors(pageable, search);
    }

    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody Author newAuthor) {

        return authorService.updateAuthor(id, newAuthor);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}