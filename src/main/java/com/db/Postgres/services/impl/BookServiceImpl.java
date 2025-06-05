package com.db.Postgres.services.impl;

import com.db.Postgres.domain.dto.BookDto;
import com.db.Postgres.domain.entities.BookEntity;
import com.db.Postgres.mappers.impl.BookMapperImpl;
import com.db.Postgres.repositories.BookRepository;
import com.db.Postgres.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapperImpl bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapperImpl bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapToEntity(bookDto);
        BookEntity book=bookRepository.save(bookEntity);
        return bookMapper.mapToDto(book);
    }

    @Override
    public List<BookDto> findAll(){
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookEntities.stream().map(bookMapper::mapToDto).collect(Collectors.toList());
    }
}
