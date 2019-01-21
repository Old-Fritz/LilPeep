package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.FormDocumentCrudService;
import DataBaseAcces.Entities.FormDocument;
import DataBaseAcces.Entities.UserForm;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FormDocumentCrudServiceBean implements FormDocumentCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public FormDocument findById(long id) {
        return em.find(FormDocument.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(FormDocument.class, id);
        em.remove(ref);
    }

    @Override
    public void save(FormDocument obj) {
        em.persist(obj);
    }

    @Override
    public FormDocument update(FormDocument obj) {
        return em.merge(obj);
    }

    @Override
    public List<FormDocument> findAll() {
        return em.createQuery("select t from FormDocument t", FormDocument.class).getResultList();
    }

    @Override
    public List<FormDocument> findByUserForm(UserForm form) {
        return form.getFormDocuments();
    }
}
