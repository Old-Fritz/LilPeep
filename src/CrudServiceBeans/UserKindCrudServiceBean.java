package CrudServiceBeans;

import CrudServices.UserKindCrudService;
import Entities.UserKind;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserKindCrudServiceBean implements UserKindCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserKind findById(long id) {
        return em.find(UserKind.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(UserKind.class, id);
        em.remove(ref);
    }

    @Override
    public void save(UserKind obj) {
        em.persist(obj);
    }

    @Override
    public UserKind update(UserKind obj) {
        return em.merge(obj);
    }

    @Override
    public List<UserKind> findAll() {
        return em.createQuery("select t from UserKind t", UserKind.class).getResultList();
    }

    @Override
    public UserKind getByName(String name) {
        return em.createQuery("select t from UserKind t where t.name = " + name, UserKind.class).getSingleResult();
    }
}
