package CrudServices;

import Entities.Picture;

import java.util.List;

public interface PictureCrudService extends CrudService<Picture>{
    @Override
    Picture findById(long id);

    @Override
    void deleteById(long id);

    @Override
    Picture update(Picture obj);

    @Override
    void save(Picture obj);

    @Override
    List<Picture> findAll();

    /** Поиск картинки по URL */
    Picture findByURL(String url);
}
