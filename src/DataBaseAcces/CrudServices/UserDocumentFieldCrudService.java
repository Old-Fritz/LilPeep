package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.UserDocument;
import DataBaseAcces.Entities.UserDocumentField;

import java.util.List;

public interface UserDocumentFieldCrudService extends CrudService<UserDocumentField>{
    @Override
    UserDocumentField findById(long id);

    @Override
    void deleteById(long id);

    @Override
    UserDocumentField update(UserDocumentField obj);

    @Override
    void save(UserDocumentField obj);

    @Override
    List<UserDocumentField> findAll();

    /** Список полей документа пользователя */
    List<UserDocumentField> findByUserDocument(UserDocument userDocument);
}
