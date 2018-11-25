package CrudServices;

import Entities.User;
import Entities.UserForm;

import java.util.List;

public interface UserFormCrudService extends CrudService<UserForm>{
    @Override
    UserForm findById(long id);

    @Override
    void deleteById(long id);

    @Override
    UserForm update(UserForm obj);

    @Override
    void save(UserForm obj);

    @Override
    List<UserForm> findAll();

    List<UserForm> getByUser(User user);
    List<UserForm> getByUserAndName(User user, String name);
}
