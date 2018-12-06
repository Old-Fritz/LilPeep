package CrudServiceBeans;

import CrudServices.UserDocumentCrudService;
import Entities.User;
import Entities.UserDocument;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserDocumentCrudServiceBean implements UserDocumentCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public UserDocument findById(long id) {
        return em.find(UserDocument.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(UserDocument.class, id);
        em.remove(ref);
    }

    @Override
    public void save(UserDocument obj) {
        em.persist(obj);
    }

    @Override
    public UserDocument update(UserDocument obj) {
        return em.merge(obj);
    }

    @Override
    public List<UserDocument> findAll() {
        return em.createQuery("select t from UserDocument t", UserDocument.class).getResultList();
    }
    @Override
    public List<UserDocument> findByUser(User user) {
        return em.createQuery("select t from UserDocument t where t.user.id = " + user.getId(), UserDocument.class).getResultList();
    }

    @Override
    public List<UserDocument> findByUserAndName(User user, String name) {
        return em.createQuery("select t from UserDocument t inner join t.documentKind d" +
                " where t.user.id = " + user.getId() + " and LOWER(d.name) like '%"+ name.toLowerCase()+"%'", UserDocument.class).getResultList();
    }
}
