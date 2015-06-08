package dao;

import entities.WarehousePlant;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public interface WarehousePlantDao {
    void create(WarehousePlant warehousePlant);
    void update(WarehousePlant warehousePlant);
    void delete(WarehousePlant warehousePlant);
    WarehousePlant getById(Integer id);
    List<WarehousePlant> getAllByWarehouseId(Integer id) throws SQLException;
}
