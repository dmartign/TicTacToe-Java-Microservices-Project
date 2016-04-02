package tbd.gateway.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tbd.gateway.model.domain.dao.CrappyUserDAO;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent context) {
    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        ServletContext sc = context.getServletContext();
        sc.setAttribute("userdao", new CrappyUserDAO());
        LOGGER.info("Context Initialized");
        LOGGER.info("Context Intialized");
    }
}
