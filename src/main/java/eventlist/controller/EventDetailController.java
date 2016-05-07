package eventlist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventlist.data.EventsDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * EventDetailController returns the details for a specific event.
 * 
 * @author klim
 */
@WebServlet("/eventdetails")
public class EventDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EventDetailController.class.getName());

    @Resource(name = "jdbc/EventsDB")
    private DataSource dataSource;

    public EventDetailController() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reqId = request.getParameter("id");
        
        try {
            
            Connection conn = dataSource.getConnection();
            EventsDAO eventsData = new EventsDAO(conn);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            
            String details = eventsData.GetEventDetails(Integer.parseInt(reqId));
            
            if(details == null) {
                response.getWriter().write("Not found");
            } else {
                response.getWriter().write(details);    
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Server Exception handling GET /eventdetails", ex);
        }

    }

}
