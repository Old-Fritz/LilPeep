package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.FormDocument;
import DataBaseAcces.Entities.UserForm;

import java.util.List;

public interface FormDocumentCrudService extends CrudService<FormDocument> {
    @Override
    FormDocument findById(long id);

    @Override
    void deleteById(long id);

    @Override
    FormDocument update(FormDocument obj);

    @Override
    void save(FormDocument obj);

    @Override
    List<FormDocument> findAll();

    /** Список документов формы по форме владельца */
    List<FormDocument> findByUserForm(UserForm form);
}
