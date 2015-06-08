package dao.jdbc;

import dao.TaskDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class JDBCTaskDao implements TaskDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public Boolean create(Task task) throws SQLException{
        connection = dataSource.getConnection();
        PreparedStatement prstm;
        if(task.getCount() == null) {
            prstm = connection.prepareStatement("insert into task (description, forester, plant) " +
                    "values (?, ?, ?)");
        } else {
            prstm = connection.prepareStatement("insert into task (description, forester, plant, count) " +
                    "values (?, ?, ?, ?)");
            prstm.setInt(4, task.getCount());
        }
        prstm.setString(1, task.getDescription());
        prstm.setInt(2, task.getForesterId());
        prstm.setInt(3, task.getPlantId());
        prstm.executeUpdate();

        return true;
    }

    @Override
    public void update(Task task) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delete(Integer id) throws SQLException{
        connection = dataSource.getConnection();
        PreparedStatement prstm;
        prstm = connection.prepareStatement("delete from task where idTask = (?)");
        prstm.setInt(1, id);
        prstm.executeUpdate();

        return true;
    }

    @Override
    public Task getById(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Task> getAllByForesterId(Integer id) throws SQLException{
        List<Task> tasks = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm;
        prstm = connection.prepareStatement("select * from task where forester = (?)");
        prstm.setInt(1, id);

        ResultSet resultSet = prstm.executeQuery();
        while(resultSet.next()) {
            tasks.add(new Task(resultSet.getInt("idTask"), resultSet.getString("description"), resultSet.getInt("forester"),
                      resultSet.getInt("plant"), resultSet.getInt("count")));
        }

        return tasks;
    }
}
