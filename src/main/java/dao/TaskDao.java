package dao;

import entities.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public interface TaskDao {
    Boolean create(Task task) throws SQLException;
    void update(Task task);
    Boolean delete(Integer id) throws SQLException;
    Task getById(Integer id);
    List<Task> getAllByForesterId(Integer id) throws SQLException;
}
