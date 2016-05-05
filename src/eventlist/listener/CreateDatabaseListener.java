package eventlist.listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CreateDatabaseListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(CreateDatabaseListener.class.getName());
    private static final String JDBC_CONN_STR = "jdbc:derby:eventsDatabase";
    
    private ServletContext servletContext = null;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        servletContext = arg0.getServletContext();
        try {
            log.info("Loading Derby DB Driver...");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            initializeDatabase();
            Connection mainconnection = DriverManager.getConnection(JDBC_CONN_STR);
            servletContext.setAttribute("DBConnection", mainconnection);
            
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
            connection = DriverManager.getConnection(JDBC_CONN_STR + ";create=true");
                        
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
            log.info("initalizing Schema");
            String databaseSqlPath = servletContext.getRealPath("/WEB-INF/database.sql");
            String sql = new String(Files.readAllBytes(Paths.get(databaseSqlPath)));

            PreparedStatement ps = con.prepareStatement(sql);

            int recordCount = ps.executeUpdate();
            log.info("in-memory database created");
        } catch (SQLException asd) {
            log.log(Level.SEVERE, "", asd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        log.info("Servlet Context Destroyed");
        try {
            log.info("Shutting down Derby DB...");
            DriverManager.getConnection(JDBC_CONN_STR + ";shutdown=true");
        } catch (SQLException sqle) {
            if (sqle.getMessage().equals("Database '" + JDBC_CONN_STR + "' shutdown.")) {
                log.info("Derby DB Shutdown successfully!");
            } else {
                throw new RuntimeException(
                        "An error occurred shutting down the Derby instance!", sqle);
            }
        }

    }
}
