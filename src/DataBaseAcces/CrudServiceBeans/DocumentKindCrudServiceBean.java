package DataBaseAcces.CrudServiceBeans;

import DataBaseAcces.CrudServices.DocumentKindCrudService;
import DataBaseAcces.Entities.DocumentKind;
import DataBaseAcces.Entities.User;
import DataBaseAcces.Entities.UserForm;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
        return em.createQuery("select t from DocumentKind t where LOWER(t.name) like '%"+name+"%'", DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findNotUsedByUserAndName(User user, String name) {
        List<DocumentKind> list = em.createQuery("select t from DocumentKind t" +
                " where LOWER(t.name) like '%" + name.toLowerCase() +"%'", DocumentKind.class).getResultList();

        list.removeAll(em.createQuery("select t from UserDocument d join d.documentKind t" +
                        " where d.user.id ="+ user.getId()+
                        " and LOWER(t.name) like '%" + name.toLowerCase() +"%'", DocumentKind.class).getResultList());
        return list;
    }

    @Override
    public List<DocumentKind> findUsedByUser(User user) {
        return em.createQuery("select t from UserDocument d  inner join d.documentKind t" +
                        " where d.user.id = " + user.getId()
                , DocumentKind.class).getResultList();
    }

    @Override
    public List<DocumentKind> findNotUsedByUserFormAndName(UserForm userForm, String name) {
        List<DocumentKind> list = em.createQuery("select t from DocumentKind t" +
                " where LOWER(t.name) like '%" + name.toLowerCase() +"%'", DocumentKind.class).getResultList();

        list.removeAll(em.createQuery("select t from FormDocument d join d.documentKind t" +
                " where d.userForm.id ="+ userForm.getId()+
                " and LOWER(t.name) like '%" + name.toLowerCase() +"%'", DocumentKind.class).getResultList());

        return list;
    }

    @Override
    public List<DocumentKind> findUsedByUserForm(UserForm userForm) {
        return em.createQuery(
                "select t from FormDocument d inner join d.documentKind t where" +
                        " d.userForm.id = " + userForm.getId()
                , DocumentKind.class).getResultList();
    }
}
