package eventlist.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import eventlist.data.IEventRepository;
import eventlist.data.InMemoryEventRepository;
import eventlist.etl.EventRecord;
import eventlist.etl.JsonFileLoader;
import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesViewModel;
import eventlist.model.GetEventsViewModel;

@WebServlet("/events")
public class EventController extends HttpServlet {

	private IEventRepository eventRepository;
	
	public EventController()	{
		super();
		eventRepository = new InMemoryEventRepository();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)             
            throws ServletException, IOException {
	     Gson gson = new Gson();
	     
	     String reqDate = request.getParameter("date");
	     String reqPeriod = request.getParameter("period");
	     String reqType = request.getParameter("type");
	     String reqLimit = request.getParameter("limit");
	     
	     
	     JsonFileLoader file = new JsonFileLoader(); 
	     List<EventRecord> list = file.Load(getServletContext().getRealPath("/WEB-INF/assignment_data_full.json"));
	     /*
	       GetEventsViewModel eventsViewModel = new GetEventsViewModel();
	      
	     eventsViewModel.setNumEvents(101);
	     eventsViewModel.setStartDate(new Date());
	     Date startDate = new Date();
	     EventPeriodType periodType = EventPeriodType.WEEK;
	     eventsViewModel.setEventsList(eventRepository.GetEvents(startDate, periodType));
	     */
	     System.out.println(list.size());
	     
	     GetDataTablesViewModel eventsViewModel = new GetDataTablesViewModel();
	     eventsViewModel.setiTotalDisplayRecords(0);
	     eventsViewModel.setsEcho("2");
	     eventsViewModel.setiTotalRecords(0);
	     eventsViewModel.setAaData(new Object[]{new Object[]{1,2,3,4,5}, new Object[]{"3",reqDate == null ? 999 : 0,3,4,5}});
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
