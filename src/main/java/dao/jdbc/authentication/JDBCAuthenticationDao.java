package dao.jdbc.authentication;

import dao.DaoFactory;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.User;

import javax.sql.DataSource;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 25.03.15
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class JDBCAuthenticationDao implements AuthenticationDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public User checkLoginAndPassword(String login, String password) throws SQLException {
        User user = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement prstm = connection.prepareStatement("select login, password, role, idUser, name, surname from user where login = (?)");
            prstm.setString(1,login);
            ResultSet resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                if(resultSet.getString("password") == null){
                    return null;
                } else {
                    if(!password.equals(resultSet.getString("password"))){
                        return null;
                    } else {
                        if(resultSet.getString("role").equals("owner")){
                            user = DaoFactory.getInstance().createOwnerDao().getByUserId(resultSet.getInt("idUser"));
                        } else {
                            user = DaoFactory.getInstance().createForesterDao().getByUserId(resultSet.getInt("idUser"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }

    @Override
    public Boolean changePassword(User user, String password) throws SQLException {
        try {
            connection = dataSource.getConnection();
            PreparedStatement prstm = connection.prepareStatement("update user set password = (?) where idUser = (?)");
            prstm.setString(1, password);
            prstm.setInt(2, user.getUserId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return true;
    }
}
