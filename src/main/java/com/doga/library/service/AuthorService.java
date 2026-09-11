package com.doga.library.service;
import com.doga.library.entity.Author;
import com.doga.library.exception.ResourceNotFoundException;
import com.doga.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.doga.library.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author not found"));
    }


    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id,Author newAuthor){

        Author author= authorRepository.findById(id).orElseThrow( ()->new ResourceNotFoundException("Author not found"));

        author.setFirstName(newAuthor.getFirstName());
        author.setLastName(newAuthor.getLastName());

        return authorRepository.save(author);

    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        authorRepository.delete(author);
    }



}


