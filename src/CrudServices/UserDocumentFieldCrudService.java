package CrudServices;

import Entities.User;
import Entities.UserDocument;
import Entities.UserDocumentField;

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

    List<UserDocumentField> findByUserDocument(UserDocument userDocument);
}
