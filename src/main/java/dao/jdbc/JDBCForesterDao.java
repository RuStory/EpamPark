package dao.jdbc;

import dao.ForesterDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Forester;
import entities.Owner;

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
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class JDBCForesterDao implements ForesterDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public Forester getByUserId(Integer id) throws SQLException{
        Forester forester = null;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select *\n" +
                "from\n" +
                "(select \n" +
                "login, password, role, idUser, name, surname \n" +
                "from user \n" +
                "where idUser = (?)) as t1\n" +
                "join \n" +
                "(select * from forester) as t2 on t2.user = t1.idUser");
        prstm.setInt(1, id);
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            forester = new Forester(resultSet.getInt("idUser"), resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("role"),
                    resultSet.getInt("idForester"), resultSet.getDouble("salary"),
                    resultSet.getInt("park"), resultSet.getInt("warehouse"));
        }
        return forester;
    }

    @Override
    public void update(Forester forester) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Forester> getAll() throws SQLException {
        List<Forester> foresters = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select *\n" +
                "from\n" +
                "(select\n" +
                "login, password, role, idUser, name, surname\n" +
                "from user\n" +
                ") as t1\n" +
                "join\n" +
                "(select * from forester) as t2 on t2.user = t1.idUser");
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            foresters.add(new Forester(resultSet.getInt("idUser"), resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("role"),
                    resultSet.getInt("idForester"), resultSet.getDouble("salary"),
                    resultSet.getInt("park"), resultSet.getInt("warehouse")));
        }
        return foresters;
    }
}
