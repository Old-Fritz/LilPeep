package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.UserDocumentFieldCrudService;
import DataBaseAcces.Entities.UserDocument;
import DataBaseAcces.Entities.UserDocumentField;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserDocumentFieldCrudServiceBean implements UserDocumentFieldCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserDocumentField findById(long id) {
        return em.find(UserDocumentField.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(UserDocumentField.class, id);
        em.remove(ref);
    }

    @Override
    public void save(UserDocumentField obj) {
        em.persist(obj);
    }

    @Override
    public UserDocumentField update(UserDocumentField obj) {
        return em.merge(obj);
    }

    @Override
    public List<UserDocumentField> findAll() {
        return em.createQuery("select t from UserDocumentField t", UserDocumentField.class).getResultList();
    }

    @Override
    public List<UserDocumentField> findByUserDocument(UserDocument userDocument) {
        return em.createQuery("select t from UserDocumentField t where t.field.id = " + userDocument.getId(), UserDocumentField.class).getResultList();
    }
}
