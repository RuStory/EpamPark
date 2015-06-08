package dao.jdbc;

import dao.PlantDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Owner;
import entities.Plant;

import java.io.Serializable;
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
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class JDBCPlantDao implements PlantDao, Serializable {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();
    @Override
    public Plant getById(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Plant plant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Plant plant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Plant plant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Plant> getAll() throws SQLException{
        List<Plant> plants = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from plant");
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            plants.add(new Plant(resultSet.getInt("idPlant"), resultSet.getString("name"), resultSet.getDouble("price"),
                    resultSet.getDouble("square")));
        }
        return plants;
    }
}
