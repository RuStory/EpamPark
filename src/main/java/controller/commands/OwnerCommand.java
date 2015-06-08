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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class OwnerCommand implements ActionCommand {
    private final String PAGE_OK = "/view/filterjsp/main_owner.jsp";
    private static final Logger logger = Logger.getLogger(ActionCommand.class);


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        TransactionManager tx = new JDBCTransactionManager();

        String command = null;
        final Owner owner = (Owner)req.getSession().getAttribute("user");
        List<Plant> plants = new ArrayList<>();
        List<Forester> foresters = new ArrayList<>();

        try {

            Callable<Park> parkUnit = new Callable<Park>() {
                @Override
                public Park call() throws Exception {
                    return DaoFactory.getInstance().createParkDao().getById(owner.getPark());
                }
            };
            Park park = tx.doInTransaction(parkUnit);

            Callable<List<Plant>> plantUnit = new Callable<List<Plant>>() {
                @Override
                public List<Plant> call() throws Exception {
                    return DaoFactory.getInstance().createPlantDao().getAll();
                }
            };
            plants = tx.doInTransaction(plantUnit);

            Callable<List<Forester>> foresterUnit = new Callable<List<Forester>>() {
                @Override
                public List<Forester> call() throws Exception {
                    return DaoFactory.getInstance().createForesterDao().getAll();
                }
            };
            foresters = tx.doInTransaction(foresterUnit);

            req.setAttribute("park", park);
            req.setAttribute("plants", plants);
            req.setAttribute("foresters", foresters);
            command = PAGE_OK;
        } catch (Exception e) {
            logger.error("Some problems in owner controller");
            e.printStackTrace();
            System.out.println("Some problems in owner controller");
            throw e;
        }
        return command;
    }
}
