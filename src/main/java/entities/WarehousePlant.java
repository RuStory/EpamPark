package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */

/**
 * Contains all information about plants in warehouse
 */
public class WarehousePlant {
    private Integer warehousePlantId;
    private Integer amount;
    private Integer plantId;

    public WarehousePlant(Integer warehousePlantId, Integer amount, Integer plantId) {
        this.warehousePlantId = warehousePlantId;
        this.amount = amount;
        this.plantId = plantId;
    }

    public Integer getWarehousePlantId() {
        return warehousePlantId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getPlant() {
        return plantId;
    }
}
