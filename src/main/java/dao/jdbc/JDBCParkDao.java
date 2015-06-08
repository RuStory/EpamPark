package dao.jdbc;


import dao.ParkDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Owner;
import entities.Park;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class JDBCParkDao implements ParkDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public Park getById(Integer id) throws SQLException{
        Park park = null;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from park where idPark = (?)");
        prstm.setInt(1, id);
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            park = new Park(resultSet.getInt("idPark"), resultSet.getString("name"), resultSet.getDouble("empty_square"));
        }
        return park;
    }

    @Override
    public void update(Park park) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
