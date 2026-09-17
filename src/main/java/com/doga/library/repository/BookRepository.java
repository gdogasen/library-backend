package com.doga.library.repository;

import com.doga.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCaseOrIsbnContainingIgnoreCaseOrGenreContainingIgnoreCase(
            String title,
            String isbn,
            String genre,
            Pageable pageable
    );
}