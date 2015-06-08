package dao;

import entities.Plant;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public interface PlantDao {
    Plant getById(Integer id);
    void update(Plant plant);
    void create(Plant plant);
    void delete(Plant plant);
    List<Plant> getAll() throws SQLException;
}
