package tbd.gateway.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import tbd.gateway.model.domain.dao.CrappyUserDAO;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent context) {
    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        ServletContext sc = context.getServletContext();
        sc.setAttribute("userdao", new CrappyUserDAO());
    }
}
