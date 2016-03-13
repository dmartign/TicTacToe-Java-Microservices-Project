package hw3q1.listeners;

import hw3q1.model.domain.dao.CrappyUserDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent context) {

    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        System.out.println("Context Intialized");
        // Setup DB connection
        // Create userDAO w/ DB connection
        // Save DB enabled user dao
        context.getServletContext().setAttribute("userdao", new CrappyUserDAO());
    }

}
