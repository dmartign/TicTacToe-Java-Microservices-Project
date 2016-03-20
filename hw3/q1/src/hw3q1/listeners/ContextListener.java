package hw3q1.listeners;


import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hw3q1.model.domain.dao.CrappyUserDAO;
import hw3q1.model.domain.dao.DBUserDAO;
import hw3q1.model.domain.dao.ProjectDAO;

public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

	java.sql.Connection conn = null;

    @Override
    public void contextDestroyed(ServletContextEvent context) {
    	try {
			conn.close();
			conn = null;
		} catch(SQLException ex) {
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
		try { Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception x) {
			System.out.println("Driver could not be loaded");
		}
		
		try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost/webuser?" +
                "user=root&password=danimaster15");
		ServletContext sc = context.getServletContext();
		// Create userDAO w/ DB connection
		// Save DB enabled user dao
		sc.setAttribute("userdao", new DBUserDAO(conn));
		LOGGER.info("Context Initialized");
		System.out.println("Set connection attibute");
		} catch (SQLException ex) {
		    // handle any errors
		    LOGGER.error("SQLException: " + ex.getMessage());
		    LOGGER.error("SQLState: " + ex.getSQLState());
		    LOGGER.error("VendorError: " + ex.getErrorCode());
		}
        // Setup DB connection
        // Create userDAO w/ DB connection
        // Save DB enabled user dao
        context.getServletContext().setAttribute("userdao", new CrappyUserDAO());
        context.getServletContext().setAttribute("projectdao", new ProjectDAO());
        LOGGER.info("Context Intialized");
    }
}
