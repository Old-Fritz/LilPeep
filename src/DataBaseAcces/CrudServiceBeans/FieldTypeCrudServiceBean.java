package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.FieldTypeCrudService;
import DataBaseAcces.Entities.FieldType;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FieldTypeCrudServiceBean implements FieldTypeCrudService  {
    @PersistenceContext
    EntityManager em;

    @Override
    public FieldType findById(long id) {
        return em.find(FieldType.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(FieldType.class, id);
        em.remove(ref);
    }

    @Override
    public void save(FieldType obj) {
        em.persist(obj);
    }

    @Override
    public FieldType update(FieldType obj) {
        return em.merge(obj);
    }

    @Override
    public List<FieldType> findAll() {
        return em.createQuery("select t from FieldType t", FieldType.class).getResultList();
    }

    @Override
    public FieldType findByName(String name) {
        return em.createQuery("select t from FieldType t where t.name = " + name, FieldType.class).getResultList().get(0);
    }
}
