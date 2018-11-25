package CrudServiceBeans;

import CrudServices.UserFormCrudService;
import Entities.User;
import Entities.UserForm;

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
    public List<UserForm> getByUser(User user) {
        return em.createQuery("select t from UserForm t where t.user.id = " +user.getId(), UserForm.class).getResultList();
    }

    @Override
    public List<UserForm> getByUserAndName(User user, String name) {
        return em.createQuery("select t from UserForm t where t.user.id = " +user.getId() + " and t.name like '%" + name + "'%" , UserForm.class).getResultList();
    }
}
