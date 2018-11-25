package CrudServices;

import Entities.Complaint;
import Entities.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ComplaintCrudService extends CrudService<Complaint> {
    @Override
    Complaint findById(long id);

    @Override
    void deleteById(long id);

    @Override
    void save(Complaint obj);

    @Override
    Complaint update(Complaint obj);

    @Override
    List<Complaint> findAll();

    List<Complaint> findByUser(User user);
    List<Complaint> findBySender(User sender);
}
