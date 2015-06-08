package controller.commands.authentication;

import controller.ActionCommand;
import entities.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class LogOutCommand implements ActionCommand {
    private final String PAGE_OK = "/view/sign_in.jsp";
    private static final Logger logger = Logger.getLogger(LogOutCommand.class);


    @Override
    public String execute(HttpServletRequest req) throws Exception{

        User user = (User)req.getSession().getAttribute("user");
        HttpSession session=req.getSession(false);
        if (req.isRequestedSessionIdValid() && session != null)
        {
            session.setAttribute("okAuthentication", false);
            session.invalidate();
            logger.info("Log out by " + user.getName() + " " + user.getSurname());
        }
        return PAGE_OK;
    }
}
