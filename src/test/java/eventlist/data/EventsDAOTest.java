/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventlist.data;

import eventlist.model.GetDataTablesRequestDTO;
import eventlist.model.GetDataTablesResponseDTO;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kwan
 */
public class EventsDAOTest {

    private static Logger logger = Logger.getLogger(EventsDAOTest.class.getName());

    public EventsDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger.info("Starting in-memory database for unit tests");

        try {

            //Creating testDB database
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby:memory:TestingDB;create=true");
            File sqlFile = Paths.get("WebContent/WEB-INF/database.sql").toFile();

            EventsDbManager.CreateIfNotExists(con, sqlFile.getAbsolutePath());

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(EventsDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            logger.info("Shutting down Derby DB...");
            DriverManager.getConnection("jdbc:derby:memory:TestingDB;shutdown=true");
        } catch (SQLException sqle) {
            if (sqle.getMessage().equals("Database 'memory:TestingDB' shutdown.")) {
                logger.info("Derby DB Shutdown successfully!");
            } else {
                System.out.println(sqle.getMessage());
                throw new RuntimeException("An error occurred shutting down the Derby instance!", sqle);
            }
        }
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of GetEventsForDataTable method, of class EventsDAO.
     */
    @Test
    public void testGetEventsForDataTable() {
        System.out.println("GetEventsForDataTable");
        GetDataTablesRequestDTO request = new GetDataTablesRequestDTO();
        EventsDAO instance = null;
        try {
            instance = new EventsDAO(DriverManager.getConnection("jdbc:derby:memory:TestingDB"));

            GetDataTablesResponseDTO expResult = null;
            GetDataTablesResponseDTO result = instance.GetEventsForDataTable(request);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            fail("The test case failed due to exception.");
        }

    }

    /**
     * Test of GetEventDetails method, of class EventsDAO.
     */
    @Test
    public void testGetEventDetails() {
        System.out.println("GetEventDetails");
        int id = 0;
        EventsDAO instance = null;
        try {
            instance = new EventsDAO(DriverManager.getConnection("jdbc:derby:memory:TestingDB"));

            String expResult = null;
            String result = instance.GetEventDetails(id);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            fail("The test case failed due to exception.");
        }

    }

}
