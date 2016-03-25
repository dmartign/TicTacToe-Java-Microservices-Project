package hw3q1.listeners;

import hw3q1.Utils;
import hw3q1.model.domain.dao.DBUserDAO;
import hw3q1.model.domain.dao.ProjectDAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    java.sql.Connection conn = null;

    @Override
    public void contextDestroyed(ServletContextEvent context) {
        try {
            this.conn.close();
            this.conn = null;
        } catch (SQLException ex) {
            // handle any errors
            LOGGER.error("SQLException: " + ex.getMessage());
            LOGGER.error("SQLState: " + ex.getSQLState());
            LOGGER.error("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
        // Setup DB connection
        // connecting to mysql database, schema webuser
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception x) {
            LOGGER.error("Driver could not be loaded", x);
        }

        try {
            String username = context.getServletContext().getInitParameter("dbusername");
            String password = context.getServletContext().getInitParameter("dbpassword");
            if (Utils.nullOrBlank(username) || Utils.nullOrBlank(password)) {
                throw new RuntimeException("Database credentials not provided");
            }
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/webuser?", username, password);
            ServletContext sc = context.getServletContext();
            // Create userDAO w/ DB connection
            // Save DB enabled user dao
            sc.setAttribute("userdao", new DBUserDAO(this.conn));
            LOGGER.info("Context Initialized");
        } catch (SQLException ex) {
            // handle any errors
            LOGGER.error("SQLException: " + ex.getMessage());
            LOGGER.error("SQLState: " + ex.getSQLState());
            LOGGER.error("VendorError: " + ex.getErrorCode());
            throw new RuntimeException("Unable to configure database connection", ex);
        }
        context.getServletContext().setAttribute("projectdao", new ProjectDAO());
        LOGGER.info("Context Intialized");
    }
}
