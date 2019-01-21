package DataBaseAcces.CrudServices;

import java.util.List;

public interface CrudService<T> {
    /** Поиск сущности по ID */
    T findById(long id);

    /** Удаление сущности по ID */
    void deleteById(long id);

    /** Обновить сущность */
    T update(T obj);

    /** Сохранить сущность */
    void save(T obj);

    /** Найти все сущности данного типа */
    List<T> findAll();
}
