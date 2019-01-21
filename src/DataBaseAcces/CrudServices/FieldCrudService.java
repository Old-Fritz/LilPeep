package CrudServices;

import Entities.DocumentKind;
import Entities.Field;

import java.util.List;

public interface FieldCrudService  extends CrudService<Field> {
    @Override
    Field findById(long id);

    @Override
    void deleteById(long id);

    @Override
    void save(Field obj);

    @Override
    Field update(Field obj);

    @Override
    List<Field> findAll();

    /** Список полей документа */
    List<Field> findByDocumentKind(DocumentKind documentKind);
}
