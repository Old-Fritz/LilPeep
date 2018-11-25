package CrudServices;

import java.util.List;

public interface CrudService<T> {
    T findById(long id);
    void deleteById(long id);
    T save(T obj);
    List<T> findAll();
}
