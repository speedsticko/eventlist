package eventlist.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * CreateDatabaseListener is responsible for creating the events database if it
 * doesn't already exist.
 * 
 * @author klim
 */
public class CreateDatabaseListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(CreateDatabaseListener.class.getName());
    private static final String JDBC_DB_NAME = "eventsDatabase";
    private static final String JDBC_CONN_STR = "jdbc:derby:" + JDBC_DB_NAME;

    private ServletContext servletContext = null;

    /**
     * 
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        
        try {
            logger.info("Loading Derby DB Driver...");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            
            initializeDatabase();
            
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Could not load Derby Embedded Driver!", e);
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Fatal Database Error!", sqle);
        }
    }

    private void initializeDatabase() throws SQLException {
        Connection connection = null;
        try {
            logger.info("Starting up Derby DB...");
            connection = DriverManager.getConnection(JDBC_CONN_STR + ";create=true");

            if (!schemaHasBeenInitialized(connection)) {
                initializeSchema(connection);
            }
        } catch (SQLException sqle) {
            logger.log(Level.SEVERE, "Could not connect to Derby Embedded DB!", sqle);
            throw sqle;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Determines if there is an "events" table in the current database.
     * 
     * @param con
     * @return
     * @throws SQLException 
     */
    private boolean schemaHasBeenInitialized(Connection con) throws SQLException {
        final DatabaseMetaData metaData = con.getMetaData();
        final ResultSet tablesResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});

        try {
            while (tablesResultSet.next()) {
                final String tableName = tablesResultSet.getString("TABLE_NAME");
                if (tableName != null && "events".equalsIgnoreCase(tableName)) {
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

        try {
            logger.info("initalizing Schema");
            String databaseSqlPath = servletContext.getRealPath("/WEB-INF/database.sql");
            String sql = new String(Files.readAllBytes(Paths.get(databaseSqlPath)));

            Statement statement = con.createStatement();
            statement.execute(sql);
            statement.execute("CREATE INDEX DateIndex ON Events(event_date)");
            logger.info("in-memory database created");
        } catch (SQLException asd) {
            logger.log(Level.SEVERE, "", asd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Servlet Context Destroyed");
        try {
            logger.info("Shutting down Derby DB...");
            DriverManager.getConnection(JDBC_CONN_STR + ";shutdown=true");
        } catch (SQLException sqle) {           
            if (sqle.getMessage().equals("Database '" + JDBC_DB_NAME + "' shutdown.")) {
                logger.info("Derby DB Shutdown successfully!");
            } else {
                throw new RuntimeException("An error occurred shutting down the Derby instance!", sqle);
            }
        }

    }
}
