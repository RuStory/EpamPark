package dao.jdbc.authentication;

import entities.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 25.03.15
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticationDao {
    public User checkLoginAndPassword(String login, String password) throws SQLException;
    public Boolean changePassword(User user, String password) throws  SQLException;
}
