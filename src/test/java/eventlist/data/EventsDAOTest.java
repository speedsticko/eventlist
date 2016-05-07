/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventlist.data;

import eventlist.model.GetDataTablesRequestDTO;
import eventlist.model.GetDataTablesResponseDTO;
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
    
    public EventsDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        GetDataTablesRequestDTO request = null;
        EventsDAO instance = null;
        GetDataTablesResponseDTO expResult = null;
        GetDataTablesResponseDTO result = instance.GetEventsForDataTable(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetEventDetails method, of class EventsDAO.
     */
    @Test
    public void testGetEventDetails() {
        System.out.println("GetEventDetails");
        int id = 0;
        EventsDAO instance = null;
        String expResult = "";
        String result = instance.GetEventDetails(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
