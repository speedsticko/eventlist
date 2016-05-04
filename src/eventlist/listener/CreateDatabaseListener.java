package eventlist.listener;

import java.sql.*;
import java.util.logging.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CreateDatabaseListener implements ServletContextListener {

	 private static final Logger log = Logger
	            .getLogger(CreateDatabaseListener.class.getName());
	  
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		 log.info("Servlet Context Destroyed");
	        try {
	            log.info("Shutting down Derby DB...");
	            DriverManager.getConnection("jdbc:derby:databaseName;shutdown=true");
	        } catch (SQLException sqle) {
	            if (sqle.getMessage().equals("Database 'databaseName' shutdown.")) {
	                log.info("Derby DB Shutdown successfully!");
	            } else {
	                throw new RuntimeException(
	                    "An error occurred shutting down the Derby instance!"
	                    , sqle);
	            }
	        }

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
            log.info("Loading Derby DB Driver...");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Could not load Derby Embedded Driver!", e);
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Fatal Database Error!", sqle);
        }
	}

	private void initializeDatabase() throws SQLException {        
        Connection connection = null;
        try {
            log.info("Starting up Derby DB...");
            connection = DriverManager.getConnection("jdbc:derby:databaseName;create=true");
            if (!schemaHasBeenInitialized(connection)) {
                initializeSchema(connection);
            }
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Could not connect to Derby Embedded DB!", sqle);
            throw sqle;
        } finally {
            if (connection != null) {                
                connection.close();
            }
        }
    }
	

    private boolean schemaHasBeenInitialized(Connection con) throws SQLException {
        final DatabaseMetaData metaData = con.getMetaData();
        final ResultSet tablesResultSet = 
                metaData.getTables(
                        null, null, null,
                        new String[] { "TABLE" });

        try {
            while (tablesResultSet.next()) {
                final String tableName = tablesResultSet.getString("TABLE_NAME");
                if (tableName != null 
                        && "MY_TABLE_NAME".equalsIgnoreCase(tableName)) {
                    return true;
                }
            }
        } finally {
            if (tablesResultSet != null) {
                tablesResultSet.close();
            }
        }
        return false;
    }

    private void initializeSchema(Connection con) {
        // Execute whatever SQL is necessary to 
        // create the schema tables and seed data
    }

	
}
