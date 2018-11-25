package CrudServiceBeans;

import CrudServices.ComplaintCrudService;
import Entities.Complaint;
import Entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ComplaintCrudServiceBean implements ComplaintCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public Complaint findById(long id) {
        return em.find(Complaint.class, id);
    }

    @Override
    public void deleteById(long id) {
        Complaint obj = em.getReference(Complaint.class, id);
        em.remove(obj);
    }

    @Override
    public Complaint save(Complaint obj) {
        return em.merge(obj)
    }

    @Override
    public List<Complaint> findAll() {
        return em.;
    }

    @Override
    public List<Complaint> findByUser(User user) {
        return null;
    }

    @Override
    public List<Complaint> findBySender(User sender) {
        return null;
    }
}
