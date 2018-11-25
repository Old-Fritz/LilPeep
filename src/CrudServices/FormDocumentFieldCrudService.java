package CrudServices;

import Entities.FormDocument;
import Entities.FormDocumentField;

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

    List<FormDocumentField> findByFormDocument(FormDocument formDocument);
    List<FormDocumentField> findNotUsedByFormDocument(FormDocument formDocument);
}