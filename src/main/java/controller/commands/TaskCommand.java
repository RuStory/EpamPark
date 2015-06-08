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
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class TaskCommand implements ActionCommand {
    private final String PAGE_OK = "/view/filterjsp/owner_command.jsp";
    private static final Logger logger = Logger.getLogger(TaskCommand.class);


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        TransactionManager tx = new JDBCTransactionManager();
        String command = null;
        Integer plantId = Integer.parseInt(req.getParameter("plant"));
        Integer foresterId = Integer.parseInt(req.getParameter("forester"));
        String work = req.getParameter("work");
        Integer count = 0;
        final Task task;

        if(work.equals("dig") || work.equals("seed")) {
            count = Integer.parseInt(req.getParameter("amount"));
            task = new Task(work, foresterId, plantId, count);
        } else {
            task = new Task(work, foresterId, plantId);
        }

        try {
            Callable<Boolean> taskUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createTaskDao().create(task);
                }
            };
            tx.doInTransaction(taskUnit);

            req.getSession().setAttribute("okTask", true);
            command = PAGE_OK;
        } catch (Exception e) {
            logger.error("Some problems in task controller");
            e.printStackTrace();
            System.out.println("Some problems in task controller");
            throw e;
        }
        return command;
    }
}
