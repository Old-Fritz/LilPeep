package CrudServices;

import Entities.User;
import Entities.UserDocument;

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

    List<UserDocument> findByUser(User user);
}
