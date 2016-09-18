package tbd.gateway.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tbd.gateway.Utils;
import tbd.gateway.login.LoginClient;
import tbd.gateway.model.domain.User;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    LoginClient loginClient = new LoginClient();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (!Utils.nullOrBlank(email) && !Utils.nullOrBlank(password)) {
            String token = this.loginClient.loginUser(email, password);
            if (!Utils.nullOrBlank(token)) {
                User user = new User();
                user.setName(email);
                user.setId(token);
                req.getSession().setAttribute("user", user);
                Cookie userTokenCookie = new Cookie("userToken", token);
                userTokenCookie.setPath("/");
                resp.addCookie(userTokenCookie);
                resp.sendRedirect("home.do");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
                dispatcher.forward(req, resp);
            }
        }

    }

}
