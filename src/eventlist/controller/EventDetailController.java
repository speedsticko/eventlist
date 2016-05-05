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

@WebServlet("/eventdetails")
public class EventDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            response.getWriter().write(eventsData.GetEventDetails(Integer.parseInt(reqId)));
        } catch (SQLException ex) {
            Logger.getLogger(EventDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
