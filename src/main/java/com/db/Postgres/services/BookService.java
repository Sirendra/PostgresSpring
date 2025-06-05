package com.db.Postgres.services;

import com.db.Postgres.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto);
    public List<BookDto> findAll();
}
