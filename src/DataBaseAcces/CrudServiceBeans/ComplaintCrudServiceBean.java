package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.ComplaintCrudService;
import DataBaseAcces.Entities.Complaint;
import DataBaseAcces.Entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ComplaintCrudServiceBean  implements ComplaintCrudService{
    @PersistenceContext
    EntityManager em;


    @Override
    public Complaint findById(long id) {
        return em.find(Complaint.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(Complaint.class, id);
        em.remove(ref);
    }

    @Override
    public void save(Complaint obj) {
        em.persist(obj);
    }

    @Override
    public Complaint update(Complaint obj) {
        return em.merge(obj);
    }

    @Override
    public List<Complaint> findAll() {
        return em.createQuery("select t from Complaint t", Complaint.class).getResultList();
    }

    @Override
    public List<Complaint> findByUser(User user) {
        return em.createQuery("select t from Complaint t where t.user.id = " + user.getId(), Complaint.class).getResultList();
    }

    @Override
    public List<Complaint> findBySender(User sender) {
        return em.createQuery("select t from Complaint t where t.sender.id = " + sender.getId(), Complaint.class).getResultList();
    }
}
