package CrudServices;

import Entities.DocumentKind;
import Entities.User;
import Entities.UserForm;

import java.util.List;

public interface DocumentKindCrudService  extends CrudService<DocumentKind> {
    @Override
    DocumentKind findById(long id);

    @Override
    void deleteById(long id);

    @Override
    void save(DocumentKind obj);

    @Override
    DocumentKind update(DocumentKind obj);

    @Override
    List<DocumentKind> findAll();

    List<DocumentKind> findByName(String name);

    List<DocumentKind> findNotUsedByUserAndName(User user, String name);
    List<DocumentKind> findUsedByUser(User user);

    List<DocumentKind> findNotUsedByUserFormAndName(UserForm userForm, String name);
    List<DocumentKind> findUsedByUserForm(UserForm userForm);
}
