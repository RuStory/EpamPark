package dao.jdbc;

import dao.OwnerDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Owner;

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
public class JDBCOwnerDao implements OwnerDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public Owner getByUserId(Integer id) throws SQLException{
        Owner owner = null;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select *\n" +
                "from\n" +
                "(select \n" +
                "login, password, role, idUser, name, surname \n" +
                "from user \n" +
                "where idUser = (?)) as t1\n" +
                "join \n" +
                "(select * from owner) as t2 on t2.user = t1.idUser");
        prstm.setInt(1, id);
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            owner = new Owner(resultSet.getInt("idUser"), resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("role"),
                    resultSet.getInt("idOwner"), resultSet.getDouble("money"), resultSet.getInt("park"));
        }
        return owner;
    }

    @Override
    public void update(Owner owner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
