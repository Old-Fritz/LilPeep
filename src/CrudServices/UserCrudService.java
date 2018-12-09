package CrudServices;

import Entities.User;
import Entities.UserKind;

import java.util.List;

public interface UserCrudService extends CrudService<User> {
    @Override
    User findById(long id);

    @Override
    void deleteById(long id);

    @Override
    User update(User obj);

    @Override
    void save(User obj);

    @Override
    List<User> findAll();

    /** Поиск пользователя по e-mail и типу */
    User findByEmailAndKind(String email, UserKind kind);

    List<User> findByEmailLikeAndKind(String email, UserKind kind);
}
