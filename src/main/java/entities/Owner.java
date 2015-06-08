package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */

/**
 * Contains all information about owner
 */
public class Owner extends User{
    private Integer ownerId;
    private Double money;
    private Integer parkId;

    public Owner(Integer userId, String name, String surname,
                 String login, String password, String role, Integer ownerId, Double money, Integer parkId) {
        super(userId, name, surname, login, password, role);
        this.ownerId = ownerId;
        this.money = money;
        this.parkId = parkId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Double getMoney() {
        return money;
    }

    public Integer getPark() {
        return parkId;
    }
}
