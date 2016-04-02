package tbd.gateway.web;

import static tbd.gateway.Utils.nullOrBlank;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tbd.gateway.model.domain.User;
import tbd.gateway.model.domain.dao.UserDAO;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LogManager.getLogger(RegisterServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if user already exists
        UserDAO userDAO = (UserDAO) this.getServletContext().getAttribute("userdao");
        User user = new User();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        PrintWriter out = resp.getWriter();
        if (nullOrBlank(email) || nullOrBlank(password) || nullOrBlank(name)) {
            LOGGER.info("Missing information");
            out.write("Missing Information");
        } else if (userDAO.findUserByEmail(email) != null) {
            LOGGER.info("User already exists!");
            out.write("User already exists!");
        } else {
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);

            userDAO.register(user);
            RegisterServlet.LOGGER.info("Registered User: " + user);
            out.write("Success");
        }
    }

}
