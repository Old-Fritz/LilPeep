package CrudServices;

import Entities.UserKind;

import java.util.List;

public interface UserKindCrudService extends CrudService<UserKind>{
    @Override
    UserKind findById(long id);

    @Override
    void deleteById(long id);

    @Override
    UserKind update(UserKind obj);

    @Override
    void save(UserKind obj);

    @Override
    List<UserKind> findAll();

    UserKind getByName(String name);
}
