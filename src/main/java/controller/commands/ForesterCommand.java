package controller.commands;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.authentication.AuthenticationDao;
import dao.jdbc.authentication.JDBCAuthenticationDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class ForesterCommand implements ActionCommand {
    private final String PAGE_OK = "/view/filterjsp/main_forester.jsp";
    private static final Logger logger = Logger.getLogger(ForesterCommand.class);


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        TransactionManager tx = new JDBCTransactionManager();

        String command = null;
        final Forester forester = (Forester)req.getSession().getAttribute("user");

        try {
            Callable<Park> parkUnit = new Callable<Park>() {
                @Override
                public Park call() throws Exception {
                    return DaoFactory.getInstance().createParkDao().getById(forester.getPark());
                }
            };
            Park park = tx.doInTransaction(parkUnit);

            Callable<List<WarehousePlant>> warehousePlantUnit = new Callable<List<WarehousePlant>>() {
                @Override
                public List<WarehousePlant> call() throws Exception {
                    return DaoFactory.getInstance().createWarehousePlantDao().getAllByWarehouseId(forester.getWarehouse());
                }
            };
            List<WarehousePlant> warehousePlants = tx.doInTransaction(warehousePlantUnit);

            Callable<List<Plant>> plantUnit = new Callable<List<Plant>>() {
                @Override
                public List<Plant> call() throws Exception {
                    return DaoFactory.getInstance().createPlantDao().getAll();
                }
            };
            List<Plant> plants = tx.doInTransaction(plantUnit);

            Warehouse warehouse = new Warehouse();
            for (WarehousePlant warehousePlant : warehousePlants) {
                for (Plant plant : plants) {
                    if(plant.getPlantId() == warehousePlant.getPlant()){
                        warehouse.put(plant, warehousePlant.getAmount());
                        break;
                    }
                }
            }

            Callable<List<Task>> taskUnit = new Callable<List<Task>>() {
                @Override
                public List<Task> call() throws Exception {
                    return DaoFactory.getInstance().createTaskDao().getAllByForesterId(forester.getForesterId());
                }
            };
            List<Task> tasks = tx.doInTransaction(taskUnit);
            if(tasks.isEmpty()) {
                req.getSession().setAttribute("tasks", "any");
            } else {
                req.getSession().setAttribute("tasks", tasks);
            }

            req.setAttribute("plants", plants);
            req.setAttribute("warehouse", warehouse);
            req.setAttribute("park", park);
            command = PAGE_OK;
        } catch (Exception e) {
            logger.error("Some problems forester controller");
            e.printStackTrace();
            System.out.println("Some problems forester controller");
            throw e;
        }

        return command;
    }
}
