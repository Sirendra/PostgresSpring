package com.db.Postgres.mappers.impl;

import com.db.Postgres.domain.dto.AuthorDto;
import com.db.Postgres.domain.entities.AuthorEntity;
import com.db.Postgres.mappers.MapperObject;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements MapperObject<AuthorDto, AuthorEntity> {

    @Override
    public AuthorDto mapToDto(AuthorEntity authorEntity) {
        return AuthorDto.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .age(authorEntity.getAge())
                .build();
    }

    @Override
    public AuthorEntity mapToEntity(AuthorDto authorDto) {
        return AuthorEntity.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .age(authorDto.getAge())
                .build();
    }
}
