package tbd.gateway.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbd.gateway.login.LoginClient;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    LoginClient loginClient = new LoginClient();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String token = this.loginClient.loginUser(email, password);

        /*-
        UserDAO userDAO = (UserDAO) this.getServletContext().getAttribute("userdao");
        if (!Utils.nullOrBlank(email)) {
            User user = userDAO.findUserByEmail(email);
            if (user != null && user.getPassword().matches(password)) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("home.do");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
                dispatcher.forward(req, resp);
            }
        }
         */

    }

}
