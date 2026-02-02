package Repository.interfaces;

import java.util.List;

public interface CrudRepository<T> {
    void create(T t);
    List<T> getAll();
    T getById(int id);
    void update(int id, T t);
    void delete(int id);
}
