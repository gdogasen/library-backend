package com.doga.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter



public class BookDTO {

    private Long id;
    private String title;
    private String isbn;
    private int publicationYear;
    private int pageCount;
    private String genre;
    private Long authorId;
    private String authorName;
}