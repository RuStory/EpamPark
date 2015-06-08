package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */
/**
 * Contains information about warehouse
 */
public class Warehouse {
    private Map<Plant, Integer> plantAmount = new HashMap<>();

    public Warehouse(){}

    public Warehouse(Map<Plant, Integer> plantAmount) {
        this.plantAmount = plantAmount;
    }

    public void put(Plant plant, Integer amount) {
        this.plantAmount.put(plant, amount);
    }

    public Map<Plant, Integer> getPlantAmount() {
        Map<Plant, Integer> copyPlantAmount = new HashMap<>();
        for (Map.Entry<Plant, Integer> entry : this.plantAmount.entrySet()) {
            try {
                copyPlantAmount.put(entry.getKey().clone(), entry.getValue());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copyPlantAmount;
    }
}
