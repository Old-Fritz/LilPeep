package CrudServices;

import Entities.FieldType;

import java.util.List;

public interface FieldTypeCrudService extends CrudService<FieldType>{
    @Override
    FieldType findById(long id);

    @Override
    void deleteById(long id);

    @Override
    void save(FieldType obj);

    @Override
    FieldType update(FieldType obj);

    @Override
    List<FieldType> findAll();

    /** Поиск типа поля по имени */
    FieldType findByName(String name);
}
