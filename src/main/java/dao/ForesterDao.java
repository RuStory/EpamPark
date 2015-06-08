package dao;

import entities.Forester;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public interface ForesterDao {
    Forester getByUserId(Integer id) throws SQLException;
    void update(Forester forester) throws SQLException;
    List<Forester> getAll() throws SQLException;
}
