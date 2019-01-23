package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.FieldCrudService;
import DataBaseAcces.Entities.DocumentKind;
import DataBaseAcces.Entities.Field;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FieldCrudServiceBean implements FieldCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public Field findById(long id) {
        return em.find(Field.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(Field.class, id);
        em.remove(ref);
    }

    @Override
    public void save(Field obj) {
        em.persist(obj);
    }

    @Override
    public Field update(Field obj) {
        return em.merge(obj);
    }

    @Override
    public List<Field> findAll() {
        return em.createQuery("select t from Field t", Field.class).getResultList();
    }

    @Override
    public List<Field> findByDocumentKind(DocumentKind documentKind) {
        return em.createQuery("select t from Field t where t.documentKind.id = " + documentKind.getId(), Field.class).getResultList();
    }
}
