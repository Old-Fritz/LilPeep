package CrudServiceBeans;

import CrudServices.PictureCrudService;
import Entities.Picture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PictureCrudServiceBean implements PictureCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public Picture findById(long id) {
        return em.find(Picture.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(Picture.class, id);
        em.remove(ref);
    }

    @Override
    public void save(Picture obj) {
        em.persist(obj);
    }

    @Override
    public Picture update(Picture obj) {
        return em.merge(obj);
    }

    @Override
    public List<Picture> findAll() {
        return em.createQuery("select t from Picture t", Picture.class).getResultList();
    }

    @Override
    public Picture findByURL(String url) {
        return em.createQuery("select t from Picture t where t.url = " + url, Picture.class).getSingleResult();
    }
}
