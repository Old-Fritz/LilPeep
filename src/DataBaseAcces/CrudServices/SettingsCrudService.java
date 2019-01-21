package CrudServices;

import Entities.Settings;
import Entities.User;

import java.util.List;

public interface SettingsCrudService extends CrudService<Settings>{
    @Override
    Settings findById(long id);

    @Override
    void deleteById(long id);

    @Override
    Settings update(Settings obj);

    @Override
    void save(Settings obj);

    @Override
    List<Settings> findAll();

    /** Список настроек пользователя */
    List<Settings> findByUser(User user);
}
