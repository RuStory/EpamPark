package dao;

import entities.Park;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface ParkDao {
    Park getById(Integer id) throws SQLException;
    void update(Park park);
}
