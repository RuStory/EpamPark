package dao;

import entities.Owner;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public interface OwnerDao {
    Owner getByUserId(Integer id) throws SQLException;
    void update(Owner owner);
}
