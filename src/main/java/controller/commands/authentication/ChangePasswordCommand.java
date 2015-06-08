package controller.commands.authentication;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.authentication.AuthenticationDao;
import dao.jdbc.authentication.AuthenticationUtils;
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
public class ChangePasswordCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ChangePasswordCommand.class);
    private final String PAGE_ERROR = "/view/filterjsp/repeat_change_password.jsp";


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        final AuthenticationDao authenticationDao = new JDBCAuthenticationDao();
        AuthenticationUtils authenticationUtils = new AuthenticationUtils();

        final String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String repeatedPassword = req.getParameter("repeatPassword");
        User user = (User)req.getSession().getAttribute("user");
        boolean okAuthentication = false;
        String okChange;
        String command = null;

        try {
            final String encryptedPassword = authenticationUtils.cryptWithMD5(oldPassword);
            if (user.getPassword().equals(encryptedPassword) && (newPassword.equals(repeatedPassword))) {
                final String password = authenticationUtils.cryptWithMD5(newPassword);
                final User finalUser = user;
                Callable<Boolean> changePasswordUnit = new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return authenticationDao.changePassword(finalUser, password);
                    }
                };
                tx.doInTransaction(changePasswordUnit);

                if(user.getRole().equals("owner")){
                    Callable<User> ownerUnit = new Callable<User>() {
                        @Override
                        public User call() throws Exception {
                            return DaoFactory.getInstance().createOwnerDao().getByUserId(finalUser.getUserId());
                        }
                    };
                    user = tx.doInTransaction(ownerUnit);
                    okChange = "/view/filterjsp/main_owner.jsp";
                } else {
                    Callable<User> ownerUnit = new Callable<User>() {
                        @Override
                        public User call() throws Exception {
                            return DaoFactory.getInstance().createForesterDao().getByUserId(finalUser.getUserId());
                        }
                    };
                    user = tx.doInTransaction(ownerUnit);
                    okChange = "/view/filterjsp/main_forester.jsp";
                }
                logger.info("Password changed by " + user.getName() + " " + user.getSurname());
                command = okChange;
                req.getSession().setAttribute("user", user);
            } else {
                logger.error("Some problems with changing password by " + user.getName() + " " + user.getSurname());
                command = PAGE_ERROR;
            }
        } catch (Exception e) {
            logger.error("Some problems with changing password by " + user.getName() + " " + user.getSurname());
            e.printStackTrace();
            System.out.println("Some problems with changing password");
            throw e;
        }
        return command;
    }
}
