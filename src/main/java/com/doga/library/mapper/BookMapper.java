package com.doga.library.mapper;

import com.doga.library.dto.BookDTO;
import com.doga.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(
            expression = "java(book.getAuthor().getFirstName() + \" \" + book.getAuthor().getLastName())",
            target = "authorName"
    )
    BookDTO toDTO(Book book);
}