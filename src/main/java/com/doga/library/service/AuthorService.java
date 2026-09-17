package com.doga.library.service;

import com.doga.library.dto.AuthorDTO;
import com.doga.library.entity.Author;
import com.doga.library.exception.ResourceNotFoundException;
import com.doga.library.mapper.AuthorMapper;
import com.doga.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public Page<AuthorDTO> getAllAuthors(Pageable pageable, String search) {

        Page<Author> authors;

        if (search == null || search.isBlank()) {
            authors = authorRepository.findAll(pageable);
        } else {
            authors =
                    authorRepository
                            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                                    search, search, pageable);
        }

        return authors.map(authorMapper::toDTO);
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author =
                authorRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        return authorMapper.toDTO(author);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author newAuthor) {

        Author author =
                authorRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        author.setFirstName(newAuthor.getFirstName());
        author.setLastName(newAuthor.getLastName());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        Author author =
                authorRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        authorRepository.delete(author);
    }
}