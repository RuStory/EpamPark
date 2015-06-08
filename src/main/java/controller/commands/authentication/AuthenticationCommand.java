package controller.commands.authentication;

import controller.ActionCommand;
import dao.jdbc.authentication.AuthenticationDao;
import dao.jdbc.authentication.AuthenticationUtils;
import dao.jdbc.authentication.JDBCAuthenticationDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class AuthenticationCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AuthenticationCommand.class);
    private final String PAGE_ERROR = "/view/repeat_sign_in.jsp";
    private final String PAGE_OWNER = "/view/filterjsp/owner_command.jsp";
    private final String PAGE_FORESTER = "/view/filterjsp/forester_command.jsp";


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        AuthenticationUtils authenticationUtils = new AuthenticationUtils();
        TransactionManager tx = new JDBCTransactionManager();
        String command = null;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        boolean okAuthentication = false;

        try {
            final String encryptedPassword = authenticationUtils.cryptWithMD5(password);
            Callable<User> authenticationLoginUnit = new Callable<User>() {
                @Override
                public User call() throws Exception {
                    return authenticationDao.checkLoginAndPassword(login, encryptedPassword);
                }
            };
            final User user = tx.doInTransaction(authenticationLoginUnit);

            if (user != null){
                req.setAttribute("repeat", true);
                if(user.getRole().equals("owner")){
                    command = PAGE_OWNER;
                } else {
                    command = PAGE_FORESTER;
                }
                okAuthentication = true;

                if (okAuthentication){
                    logger.info("Success authentication by " + user.getName() + " " + user.getSurname());
                    req.getSession().setAttribute("okAuthentication", okAuthentication);
                    req.getSession().setAttribute("user", user);
                } else {
                    logger.error("Some problems with authentication");
                    command = PAGE_ERROR;
                }
            } else {
                logger.error("Some problems with authentication");
                command = PAGE_ERROR;
            }

        } catch (Exception e) {
            logger.error("Some problems with authentication");
            e.printStackTrace();
            System.out.println("Some problems with authentication");
            throw e;
        }
        return command;
    }
}
