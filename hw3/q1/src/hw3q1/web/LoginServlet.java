package hw3q1.web;

import static hw3q1.Utils.nullOrBlank;
import hw3q1.model.domain.User;
import hw3q1.model.domain.dao.UserDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDAO userDAO = (UserDAO) this.getServletContext().getAttribute("userdao");
        if (!nullOrBlank(email)) {
            User user = userDAO.findUserByEmail(email);
            if (user != null && user.getPassword().matches(password)) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("home.do");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
                dispatcher.forward(req, resp);
            }
        }

    }

}
