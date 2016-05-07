package eventlist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import eventlist.data.EventsDAO;

import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesRequestDTO;
import eventlist.model.GetDataTablesResponseDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * EventController handles requests for a list of event details for a specific range.
 * 
 * @author klim
 */
@WebServlet("/events")
public class EventController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(EventController.class.getName());

    @Resource(name = "jdbc/EventsDB")
    private DataSource dataSource;

    public EventController() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();

        String reqDate = request.getParameter("date");
        String reqPeriod = request.getParameter("period");
        String reqDraw = request.getParameter("draw");
        String reqStart = request.getParameter("start");
        String reqLength = request.getParameter("length");

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDate dt = formatter.parseLocalDate(reqDate);

        GetDataTablesRequestDTO dtRequest = new GetDataTablesRequestDTO();

        try {
            Connection conn = dataSource.getConnection();
            EventsDAO eventsData = new EventsDAO(conn);

            dtRequest.setDraw(Integer.parseInt(reqDraw));
            dtRequest.setStart(Integer.parseInt(reqStart));
            dtRequest.setLength(Integer.parseInt(reqLength));
            dtRequest.setDate(dt);
            
            if ("week".equalsIgnoreCase(reqPeriod)) {
                dtRequest.setPeriod(EventPeriodType.WEEK);
            } else if ("month".equalsIgnoreCase(reqPeriod)) {
                dtRequest.setPeriod(EventPeriodType.MONTH);
            } else if ("quarter".equalsIgnoreCase(reqPeriod)) {
                dtRequest.setPeriod(EventPeriodType.QUARTER);
            } else {
                throw new IllegalArgumentException("Unrecognized period type: " + reqPeriod);
            }
            
            GetDataTablesResponseDTO eventsViewModel = eventsData.GetEventsForDataTable(dtRequest);
            String json = gson.toJson(eventsViewModel);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Server Exception handling GET /events", ex);
        }

    }

}
