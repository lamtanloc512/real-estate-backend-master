package com.devcamp.real_estate_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public class GenericServiceImpl<T> implements GenericService<T> {
    private final JpaRepository<T, Integer> repository;

    public GenericServiceImpl(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public T update(Integer id, T entity) {
        return repository.findById(id).map(existingEntity -> {
            // Assume the merge logic is handled externally or customize here.
            return repository.save(entity);
        }).orElseThrow(() -> new RuntimeException("Entity not found with id " + id));
    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Entity not found with id " + id);
        }
    }
}
