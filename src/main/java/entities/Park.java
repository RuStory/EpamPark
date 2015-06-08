package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

/**
 * Contains all information about park
 */
public class Park {
    private Integer parkId;
    private String name;
    private Double emptySquare;

    public Park(Integer parkId, String name, Double emptySquare) {
        this.parkId = parkId;
        this.name = name;
        this.emptySquare = emptySquare;
    }

    public Integer getParkId() {
        return parkId;
    }

    public String getName() {
        return name;
    }

    public Double getEmptySquare() {
        return emptySquare;
    }
}
