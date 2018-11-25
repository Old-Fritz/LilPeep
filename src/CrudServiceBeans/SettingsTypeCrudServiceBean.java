package CrudServiceBeans;

import CrudServices.SettingsTypeCrudService;
import Entities.SettingsType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SettingsTypeCrudServiceBean implements SettingsTypeCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public SettingsType findById(long id) {
        return em.find(SettingsType.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(SettingsType.class, id);
        em.remove(ref);
    }

    @Override
    public void save(SettingsType obj) {
        em.persist(obj);
    }

    @Override
    public SettingsType update(SettingsType obj) {
        return em.merge(obj);
    }

    @Override
    public List<SettingsType> findAll() {
        return em.createQuery("select t from SettingsType t", SettingsType.class).getResultList();
    }

    @Override
    public SettingsType getByName(String name) {
        return em.createQuery("select t from SettingsType t where t.name = " + name, SettingsType.class).getSingleResult();
    }
}
