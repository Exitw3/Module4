package com.codegym.service;

import java.util.Optional;

public interface IGeneral<T> {
    Iterable<T> findAll();

    Optional<T> findById(int id);

    void save(T t);

    void remove(int id);

    void remove(T t);
}
