package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserForm;

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

    /** Список форм владельца */
    List<UserForm> findByUser(User user);

    /** Поиск форм владельца по названию */
    List<UserForm> findByUserAndName(User user, String name);
}
