package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.UserCrudService;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserKind;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserCrudServiceBean implements UserCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public User findById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(User.class, id);
        em.remove(ref);
    }

    @Override
    public void save(User obj) {
        em.persist(obj);
    }

    @Override
    public User update(User obj) {
        return em.merge(obj);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select t from User t", User.class).getResultList();
    }

    @Override
    public User findByEmailAndKind(String email, UserKind kind) {
        try {
            return em.createQuery("select t from User t where t.email = '" + email + "' and t.userKind.id = " + kind.getId(), User.class).getResultList().get(0);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<User> findByEmailLikeAndKind(String email, UserKind kind) {
        try {
            return em.createQuery("select t from User t where LOWER(t.email) like '%" + email.toLowerCase() + "%' and t.userKind.id = " + kind.getId(), User.class).getResultList();
        }catch (Exception e){
            return null;
        }
    }
}