package dao.jdbc;

import dao.*;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class JDBCDaoFactory extends DaoFactory {
    @Override
    public ForesterDao createForesterDao() {
        return  new JDBCForesterDao();
    }

    @Override
    public OwnerDao createOwnerDao() {
        return new JDBCOwnerDao();
    }

    @Override
    public ParkDao createParkDao() {
        return new JDBCParkDao();
    }

    @Override
    public PlantDao createPlantDao() {
        return new JDBCPlantDao();
    }

    @Override
    public TaskDao createTaskDao() {
        return new JDBCTaskDao();
    }

    @Override
    public WarehousePlantDao createWarehousePlantDao() {
        return new JDBCWarehousePlantDao();
    }
}
