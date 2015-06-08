package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

/**
 * Contains all information about forester
 */
public class Forester extends  User implements Serializable {
    private Integer foresterId;
    private Double salary;
    private Integer parkId;
    private Integer warehouseId;

    public Forester(Integer userId, String name, String surname, String login, String password,
                    String role, Integer foresterId, Double salary, Integer parkId, Integer warehouseId) {
        super(userId, name, surname, login, password, role);
        this.foresterId = foresterId;
        this.salary = salary;
        this.parkId = parkId;
        this.warehouseId = warehouseId;
    }

    public Integer getForesterId() {
        return foresterId;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getPark() {
        return parkId;
    }

    public Integer getWarehouse() {
        return warehouseId;
    }
}
