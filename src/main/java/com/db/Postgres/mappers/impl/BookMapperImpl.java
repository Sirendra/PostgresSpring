package com.db.Postgres.mappers.impl;

import com.db.Postgres.domain.dto.BookDto;
import com.db.Postgres.domain.entities.BookEntity;
import com.db.Postgres.mappers.MapperObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements MapperObject<BookDto, BookEntity> {

    private final AuthorMapperImpl authorMapper;

    @Autowired
    public BookMapperImpl(AuthorMapperImpl authorMapper) {
        this.authorMapper = authorMapper;
    }
    @Override
    public BookDto mapToDto(BookEntity bookEntity) {
        return BookDto.builder()
                .author(authorMapper.mapToDto(bookEntity.getAuthor()))
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .build();
    }

    @Override
    public BookEntity mapToEntity(BookDto bookDto) {
        return BookEntity.builder()
                .author(authorMapper.mapToEntity(bookDto.getAuthor()))
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .build();
    }
}
