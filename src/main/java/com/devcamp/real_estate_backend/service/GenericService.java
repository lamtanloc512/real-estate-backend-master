package com.devcamp.real_estate_backend.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    T create(T entity);
    List<T> getAll();
    Optional<T> getById(Integer id);
    T update(Integer id, T entity);
    void delete(Integer id);
}
