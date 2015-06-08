package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

/**
 *Base class for all registered users
 */
public abstract class User implements Serializable{
    protected Integer userId;
    protected String name;
    protected String surname;
    protected String login;
    protected String hashPassword;
    protected String role;

    public User(Integer userId, String name, String surname, String login, String hashPassword, String role) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hashPassword = hashPassword;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return hashPassword;
    }

    public String getRole() {
        return role;
    }

    public abstract Integer getPark();
}
