package eventlist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import eventlist.data.EventsDAO;

import eventlist.etl.EventRecord;
import eventlist.etl.JsonFileLoader;
import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesViewModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@WebServlet("/events")
public class EventController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
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
            String reqType = request.getParameter("type");
            String reqLimit = request.getParameter("limit");
            /*
            JsonFileLoader file = new JsonFileLoader();
            List<EventRecord> list = file.Load(getServletContext().getRealPath("/WEB-INF/assignment_data_full.json"));
            
            GetEventsViewModel eventsViewModel = new GetEventsViewModel();
            
            eventsViewModel.setNumEvents(101);
            eventsViewModel.setStartDate(new Date());
            Date startDate = new Date();
            EventPeriodType periodType = EventPeriodType.WEEK;
            eventsViewModel.setEventsList(eventRepository.GetEvents(startDate, periodType));
            
            System.out.println(list.size());
            */
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            LocalDate dt = formatter.parseLocalDate(reqDate);
            
            GetDataTablesViewModel eventsViewModel = new GetDataTablesViewModel();
            try {
                Connection conn = dataSource.getConnection();
                EventsDAO eventsData = new EventsDAO(conn);
                Object[] dataTableData = eventsData.GetEventsForDataTable(dt, EventPeriodType.MONTH);
                eventsViewModel.setiTotalDisplayRecords(dataTableData.length);
                eventsViewModel.setsEcho("2");
                eventsViewModel.setiTotalRecords(200);
                eventsViewModel.setAaData(dataTableData);           
            } catch (SQLException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            String json = gson.toJson(eventsViewModel);
            
            //List<MyObject> listData = ...; // however you get the data
            // set the attribute in the request to access it on the JSP
            //request.setAttribute("listData", listData);
            //RequestDispatcher rd = getServletContext().getRequestDispatcher("/events.jsp");
            //rd.forward(request, response);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        
    }

}
