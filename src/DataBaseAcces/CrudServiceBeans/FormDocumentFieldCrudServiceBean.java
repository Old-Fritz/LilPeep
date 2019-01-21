package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.FormDocumentFieldCrudService;
import DataBaseAcces.Entities.FormDocument;
import DataBaseAcces.Entities.FormDocumentField;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormDocumentFieldCrudServiceBean implements FormDocumentFieldCrudService  {
    @PersistenceContext
    EntityManager em;

    @Override
    public FormDocumentField findById(long id) {
        return em.find(FormDocumentField.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(FormDocumentField.class, id);
        em.remove(ref);
    }

    @Override
    public void save(FormDocumentField obj) {
        em.persist(obj);
    }

    @Override
    public FormDocumentField update(FormDocumentField obj) {
        return em.merge(obj);
    }

    @Override
    public List<FormDocumentField> findAll() {
        return em.createQuery("select t from FormDocumentField t", FormDocumentField.class).getResultList();
    }

    @Override
    public List<FormDocumentField> findByFormDocument(FormDocument formDocument) {
        return em.createQuery("select t from FormDocumentField t where t.formDocument.id = " + formDocument.getId(), FormDocumentField.class).getResultList();
    }

    @Override
    public List<FormDocumentField> findNotUsedByFormDocument(FormDocument formDocument) {
        return formDocument.getFormDocumentFields();
    }
}
