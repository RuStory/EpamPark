package entities;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class Task implements Serializable{
    private Integer taskId;
    private String description;
    private Integer foresterId;
    private Integer plantId;
    private Integer count;

    public Task(Integer taskId, String description, Integer foresterId, Integer plantId, Integer count) {
        this.taskId = taskId;
        this.description = description;
        this.foresterId = foresterId;
        this.plantId = plantId;
        this.count = count;
    }

    public Task(Integer taskId, String description, Integer foresterId, Integer plantId) {
        this.taskId = taskId;
        this.description = description;
        this.foresterId = foresterId;
        this.plantId = plantId;
    }

    public Task(String description, Integer foresterId, Integer plantId, Integer count) {
        this.description = description;
        this.foresterId = foresterId;
        this.plantId = plantId;
        this.count = count;
    }

    public Task(String description, Integer foresterId, Integer plantId) {
        this.description = description;
        this.foresterId = foresterId;
        this.plantId = plantId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getForesterId() {
        return foresterId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public Integer getCount() {
        return count;
    }
}
