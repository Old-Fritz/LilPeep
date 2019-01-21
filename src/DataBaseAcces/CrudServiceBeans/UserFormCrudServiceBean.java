package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.UserFormCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserForm;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserFormCrudServiceBean implements UserFormCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserForm findById(long id) {
        return em.find(UserForm.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(UserForm.class, id);
        em.remove(ref);
    }

    @Override
    public void save(UserForm obj) {
        em.persist(obj);
    }

    @Override
    public UserForm update(UserForm obj) {
        return em.merge(obj);
    }

    @Override
    public List<UserForm> findAll() {
        return em.createQuery("select t from UserForm t", UserForm.class).getResultList();
    }

    @Override
    public List<UserForm> findByUser(User user) {
        return em.createQuery("select t from UserForm t where t.user.id = " +user.getId(), UserForm.class).getResultList();
    }

    @Override
    public List<UserForm> findByUserAndName(User user, String name) {
        return em.createQuery("select t from UserForm t where t.user.id = " +user.getId() + " and LOWER(t.name) like '%" + name.toLowerCase() + "'%" , UserForm.class).getResultList();
    }
}
