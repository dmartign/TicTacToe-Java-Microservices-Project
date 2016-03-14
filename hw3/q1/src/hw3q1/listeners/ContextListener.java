package hw3q1.listeners;

import hw3q1.model.domain.dao.CrappyUserDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent context) {

    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        // Setup DB connection
        // Create userDAO w/ DB connection
        // Save DB enabled user dao
        context.getServletContext().setAttribute("userdao", new CrappyUserDAO());
        LOGGER.info("Context Intialized");
    }

}
