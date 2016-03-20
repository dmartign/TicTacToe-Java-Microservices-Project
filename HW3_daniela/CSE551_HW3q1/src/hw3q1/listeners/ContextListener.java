package hw3q1.listeners;

import sun.rmi.transport.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hw3q1.model.domain.dao.DBUserDAO;

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
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
    }

    @Override
    public void contextInitialized(ServletContextEvent context) {
// OLD CODE
    	//context.getServletContext().setAttribute("userdao", new CrappyUserDAO());
        //LOGGER.info("Context Intialized");
    	
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
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
    }

}
