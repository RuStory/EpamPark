package entities;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:22
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;

/**
 * Contains all information about plant
 */
public class Plant implements Serializable, Cloneable{
    private Integer plantId;
    private String name;
    private Double price;
    private Double square;

    public Plant(Integer plantId, String name, Double price, Double square) {
        this.plantId = plantId;
        this.name = name;
        this.price = price;
        this.square = square;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSquare() {
        return square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plant)) return false;

        Plant plant = (Plant) o;

        if (!name.equals(plant.name)) return false;
        if (!plantId.equals(plant.plantId)) return false;
        if (!price.equals(plant.price)) return false;
        if (!square.equals(plant.square)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = plantId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + square.hashCode();
        return result;
    }

    @Override
    public Plant clone() throws CloneNotSupportedException {
        return (Plant)super.clone();
    }
}
