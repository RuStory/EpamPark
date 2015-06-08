package dao.jdbc;

import dao.WarehousePlantDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.WarehousePlant;

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
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public class JDBCWarehousePlantDao implements WarehousePlantDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public void create(WarehousePlant warehousePlant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(WarehousePlant warehousePlant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(WarehousePlant warehousePlant) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WarehousePlant getById(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<WarehousePlant> getAllByWarehouseId(Integer id) throws SQLException{
        List<WarehousePlant> warehousePlants = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from warehouseplant where warehouse=(?)");
        prstm.setInt(1, id);
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            warehousePlants.add(new WarehousePlant(resultSet.getInt("idWarehousePlant"), resultSet.getInt("amount"),
                    resultSet.getInt("plant")));
        }
        return warehousePlants;
    }
}
