package CrudServiceBeans;

import CrudServices.SettingsCrudService;
import Entities.Settings;
import Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public List<Settings> getByUser(User user) {
        return user.getSettings();
    }
}
