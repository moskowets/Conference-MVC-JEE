package eu.mktcode.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);

    int first = 1;
    int second = 2;
    int third = 3;
    int fourth = 4;
    int fifth = 5;
    int sixth = 6;

}