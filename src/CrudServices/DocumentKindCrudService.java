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

    /** Поиск по имени */
    List<DocumentKind> findByName(String name);

    /** Поиск по документов, которых нет у пользователя */
    List<DocumentKind> findNotUsedByUserAndName(User user, String name);

    /** Документы, которые использует пользователь */
    List<DocumentKind> findUsedByUser(User user);

    /** Дкументы, которые не используются в форме */
    List<DocumentKind> findNotUsedByUserFormAndName(UserForm userForm, String name);

    /** Документы, которые используются */
    List<DocumentKind> findUsedByUserForm(UserForm userForm);
}
