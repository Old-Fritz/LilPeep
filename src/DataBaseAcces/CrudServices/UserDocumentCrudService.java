package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.DocumentKind;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserDocument;

import java.util.List;

public interface UserDocumentCrudService extends CrudService<UserDocument>{
    @Override
    UserDocument findById(long id);

    @Override
    void deleteById(long id);

    @Override
    UserDocument update(UserDocument obj);

    @Override
    void save(UserDocument obj);

    @Override
    List<UserDocument> findAll();

    /** Список документов пользователя */
    List<UserDocument> findByUser(User user);

    /** Поиск документов пользователя по названию */
    List<UserDocument> findByUserAndName(User user, String name);

    /** Поиск документа пользователя определенного типа **/
    UserDocument findByUserAndDocument(User user, DocumentKind documentKind);
}
