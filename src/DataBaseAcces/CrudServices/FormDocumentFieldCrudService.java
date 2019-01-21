package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.FormDocument;
import DataBaseAcces.Entities.FormDocumentField;

import java.util.List;

public interface FormDocumentFieldCrudService extends CrudService<FormDocumentField> {
    @Override
    FormDocumentField findById(long id);

    @Override
    void deleteById(long id);

    @Override
    FormDocumentField update(FormDocumentField obj);

    @Override
    void save(FormDocumentField obj);

    @Override
    List<FormDocumentField> findAll();

    /** Список полей документа формы */
    List<FormDocumentField> findByFormDocument(FormDocument formDocument);

    /** Список неиспользованных полей документа формы */
    List<FormDocumentField> findNotUsedByFormDocument(FormDocument formDocument);
}
