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
public class ExecuteTaskCommand implements ActionCommand {
    private final String PAGE_OK = "/view/filterjsp/forester_command.jsp";
    private static final Logger logger = Logger.getLogger(ExecuteTaskCommand.class);

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        TransactionManager tx = new JDBCTransactionManager();

        String command = null;
        final Integer taskId = Integer.parseInt(req.getParameter("executedTask"));
        List<Task> tasks = (ArrayList<Task>)req.getSession().getAttribute("tasks");
        for (Task task : tasks) {
            if(task.getTaskId() == taskId) {
                tasks.remove(task);
                break;
            }
        }

        try {
            Callable<Boolean> taskUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createTaskDao().delete(taskId);
                }
            };
            tx.doInTransaction(taskUnit);

            if(tasks.isEmpty()) {
                req.getSession().setAttribute("tasks", "any");
            } else {
                req.getSession().setAttribute("tasks", tasks);
            }
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
