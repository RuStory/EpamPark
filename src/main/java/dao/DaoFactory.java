package dao;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class DaoFactory {
    public abstract ForesterDao createForesterDao();
    public abstract OwnerDao createOwnerDao();
    public abstract ParkDao createParkDao();
    public abstract PlantDao createPlantDao();
    public abstract TaskDao createTaskDao();
    public abstract WarehousePlantDao createWarehousePlantDao();

    public static DaoFactory getInstance() {
        try {
            Properties config = new Properties();
            config.load(new FileInputStream("e:Epam\\projects\\Park\\src\\main\\resources\\dao.properties"));
//            config.load(new FileInputStream("..\\..\\..\\resources\\dao.properties"));
            return (DaoFactory) Class.forName(config.getProperty("dao.factory")).newInstance();
        } catch (Exception ex) {
            return null;
        }
    }
}
