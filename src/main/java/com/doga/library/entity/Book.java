package com.doga.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter

public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;

    @Min(value = 1, message = "Publication year must be greater than 0")
    private int publicationYear;

    @Min(value = 1, message = "Page count must be greater than 0")
    private int pageCount;

    @NotBlank(message = "Genre cannot be empty")
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
