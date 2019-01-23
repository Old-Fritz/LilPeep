package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.SettingsCrudService;
import DataBaseAcces.Entities.Settings;
import DataBaseAcces.Entities.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SettingsCrudServiceBean implements SettingsCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public Settings findById(long id) {
        return em.find(Settings.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(Settings.class, id);
        em.remove(ref);
    }

    @Override
    public void save(Settings obj) {
        em.persist(obj);
    }

    @Override
    public Settings update(Settings obj) {
        return em.merge(obj);
    }

    @Override
    public List<Settings> findAll() {
        return em.createQuery("select t from Settings t", Settings.class).getResultList();
    }

    @Override
    public List<Settings> findByUser(User user) {
        return em.createQuery("select t from Settings t where t.user.id = " + user.getId(), Settings.class).getResultList();
    }
}
