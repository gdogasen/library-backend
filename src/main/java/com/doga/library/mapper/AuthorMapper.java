package com.doga.library.mapper;

import com.doga.library.dto.AuthorDTO;
import com.doga.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO toDTO(Author author);

    Author toEntity(AuthorDTO authorDTO);
}