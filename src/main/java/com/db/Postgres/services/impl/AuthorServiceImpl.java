package com.db.Postgres.services.impl;

import com.db.Postgres.domain.dto.AuthorDto;
import com.db.Postgres.domain.entities.AuthorEntity;
import com.db.Postgres.exceptions.ResourceAlreadyExistException;
import com.db.Postgres.exceptions.ResourceNotFoundException;
import com.db.Postgres.mappers.impl.AuthorMapperImpl;
import com.db.Postgres.repositories.AuthorRepository;
import com.db.Postgres.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapperImpl authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapperImpl authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        AuthorEntity authorEntity = authorMapper.mapToEntity(authorDto);
        if(authorEntity.getId()!=null && authorRepository.existsById(authorEntity.getId())) {
            throw new ResourceAlreadyExistException("Author already exists");
        }
        authorEntity.setId(null);
        AuthorEntity result = authorRepository.save(authorEntity);
        return authorMapper.mapToDto(result);
    }

    @Override
    public List<AuthorDto> findAll() {
        List<AuthorEntity> authorEntity = authorRepository.findAll();
        return authorEntity.stream().map(authorMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Long id) {
        AuthorEntity authorEntity = authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author not found"));
        return authorMapper.mapToDto(authorEntity);
    }

    @Override
    public AuthorDto put(Long id, AuthorDto authorDto) {
        if(!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
       AuthorEntity authorEntity = authorMapper.mapToEntity(authorDto);
       authorEntity.setId(id);
       AuthorEntity result = authorRepository.save(authorEntity);
       return authorMapper.mapToDto(result);
    }

    @Override
    public void delete(Long id) {
        if(!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDto patch(Long id, AuthorDto authorDto) {
        authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author not found"));
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(id);
        if(authorDto.getAge() != null) {
            authorEntity.setAge(authorDto.getAge());
        }
        if(authorDto.getName() != null) {
            authorEntity.setName(authorDto.getName());
        }
        AuthorEntity author=authorRepository.save(authorEntity);
        return authorMapper.mapToDto(author);
    }
}
