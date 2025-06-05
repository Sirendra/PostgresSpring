package com.db.Postgres.services;

import com.db.Postgres.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    public AuthorDto createAuthor(AuthorDto authorDto);
    public List<AuthorDto> findAll();
    public AuthorDto findById(Long id);
    public AuthorDto put(Long id, AuthorDto authorDto);
    public AuthorDto patch(Long id, AuthorDto authorDto);
    public void delete(Long id);

}
