package CrudServiceBeans;

import CrudServices.DocumentKindCrudService;
import Entities.DocumentKind;
import Entities.User;
import Entities.UserForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DocumentKindCrudServiceBean implements DocumentKindCrudService {
    @PersistenceContext
    EntityManager em;

    @Override
    public DocumentKind findById(long id) {
        return em.find(DocumentKind.class, id);
    }

    @Override
    public void deleteById(long id) {
        Object ref = em.getReference(DocumentKind.class, id);
        em.remove(ref);
    }

    @Override
    public void save(DocumentKind obj) {
        em.persist(obj);
    }

    @Override
    public DocumentKind update(DocumentKind obj) {
        return em.merge(obj);
    }

    @Override
    public List<DocumentKind> findAll() {
        return em.createQuery("select t from DocumentKind t", DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findByName(String name) {
        return em.createQuery("select t from DocumentKind t where t.name like %'"+name+"%'", DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findNotUsedByUserAndName(User user, String name) {
        return em.createQuery(
                "select distinct t from DocumentKind t left outer join UserDocument d where d.documentKind = t and d.user.id != " + user.getId() +
                        " and t.name like '%" + name +"%'"
                , DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findUsedByUser(User user) {
        return em.createQuery(
                "select t from DocumentKind t inner join UserDocument d where d.documentKind = t and d.user.id = " + user.getId()
                , DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findNotUsedByUserFormAndName(UserForm userForm, String name) {
        return em.createQuery(
                "select distinct t from DocumentKind t left outer join FormDocument d where d.documentKind = t and d.userForm.id != " + userForm.getId() +
                        " and t.name like '%" + name +"%'"
                , DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findUsedByUserForm(UserForm userForm) {
        return em.createQuery(
                "select t from DocumentKind t inner join FormDocument d where d.documentKind = t and d.userForm.id = " + userForm.getId()
                , DocumentKind.class).getResultList();
    }
}
