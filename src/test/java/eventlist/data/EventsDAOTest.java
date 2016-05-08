/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventlist.data;

import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesRequestDTO;
import eventlist.model.GetDataTablesResponseDTO;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.LocalDate;
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

            Statement insertEventsStmt = con.createStatement();
            int updateCount = insertEventsStmt.executeUpdate("INSERT INTO events(event_date, event_type, event_summary,event_size, event_detail) "
                    + "VALUES ('2015-01-01','type','summary',100,'details'), "
                    + "('2015-01-09','type','summary',100,'details'), "
                    + "('2015-02-01','type','summary',100,'details'), "
                    + "('2015-03-01','type','summary',100,'details'), "
                    + "('2015-04-01','type','summary',100,'details'), "
                    + "('2015-05-01','type','summary',100,'details'), "
                    + "('2015-05-08','type','summary',100,'details'), "
                    + "('2015-05-15','type','summary',100,'details'), "
                    + "('2015-05-18','type','summary',100,'details'), "
                    + "('2015-05-29','type','summary',100,'details')");
            if(updateCount != 10){
                logger.warning("Incorrect number of events inserted into database.");
            }
                    
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
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


    @Test
    public void testGetEventsForDataTable_EmptyRequest() {
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

    @Test
    public void testGetEventsForDataTable_OneWeek() {
        GetDataTablesRequestDTO request = new GetDataTablesRequestDTO();
        request.setDate(new LocalDate(2015, 1, 7));
        request.setDraw(1);
        request.setLength(25);
        request.setStart(0);
        request.setPeriod(EventPeriodType.WEEK);
        
        try {
            EventsDAO instance = new EventsDAO(DriverManager.getConnection("jdbc:derby:memory:TestingDB"));

            GetDataTablesResponseDTO expResult = new GetDataTablesResponseDTO();
            expResult.setDraw(1);
            expResult.setRecordsFiltered(1);
            expResult.setRecordsTotal(1);
            List<Object> list = new LinkedList<Object>();
            list.add(new Object[]{"2015-01-09","type","summary",100,"details"});
            expResult.setData(list.toArray());
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
        
        try {
            EventsDAO instance = new EventsDAO(DriverManager.getConnection("jdbc:derby:memory:TestingDB"));

            String expResult = null;
            String result = instance.GetEventDetails(id);
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            fail("The test case failed due to exception.");
        }

    }

}
