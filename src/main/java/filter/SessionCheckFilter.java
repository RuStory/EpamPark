package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 26.03.15
 * Time: 0:07
 * To change this template use File | Settings | File Templates.
 */

/**
 * provides checking user activity
 */
@WebFilter("/*")
public class SessionCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = ((HttpServletRequest) request).getRequestURI();

        if ((!path.endsWith("page") && !path.startsWith("/view/filterjsp/")) || path.equals("/")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = req.getSession(false);
            if (session == null ) {
                req.getRequestDispatcher("/sign_in.jsp").forward(req, resp);
            }
            else {
                if (session.getAttribute("okAuthentication") == null) {
                    req.getRequestDispatcher("/sign_in.jsp").forward(req, resp);

                } else {

                    if (!(Boolean)session.getAttribute("okAuthentication")) {
                        req.getRequestDispatcher("/sign_in.jsp").forward(req, resp);

                    } else {
                        chain.doFilter(req, resp);
                    }
                }
            }
        }
        return;
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}