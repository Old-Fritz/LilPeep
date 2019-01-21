package DataBaseAcces.CrudServices;

import DataBaseAcces.Entities.SettingsType;

import java.util.List;

public interface SettingsTypeCrudService extends CrudService<SettingsType>{
    @Override
    SettingsType findById(long id);

    @Override
    void deleteById(long id);

    @Override
    SettingsType update(SettingsType obj);

    @Override
    void save(SettingsType obj);

    @Override
    List<SettingsType> findAll();

    /** Получение типа настройки по названию */
    SettingsType findByName(String name);
}
