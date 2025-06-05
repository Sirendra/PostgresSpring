package com.db.Postgres.mappers;

public interface MapperObject<A,B> {
    A mapToDto(B b);
    B mapToEntity(A a);
}
