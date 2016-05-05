package eventlist.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import eventlist.etl.EventRecord;
import eventlist.etl.JsonStreamLoader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

//import eventlist.etl.EventRecord;
@WebServlet("/upload")
@MultipartConfig
public class UploadController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final Logger logger = Logger.getLogger(UploadController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Make this threadsafe */
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        JsonStreamLoader loader = new JsonStreamLoader();
        List<EventRecord> list = loader.Load(fileContent);

        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        PreparedStatement ps = null;
        int[] results = null;
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            ps = con.prepareStatement("insert into events(event_date, event_type, event_summary, event_size, event_detail) values (?,?,?,?,?)");

            int count = 0;
            for (EventRecord event : list) {

                LocalDate dt = formatter.parseLocalDate(event.getEvent_date());
                java.util.Date utilDate = dt.toDate();
                ps.setDate(1, new java.sql.Date(utilDate.getTime()));
                ps.setString(2, event.getEvent_type());
                ps.setString(3, event.getEvent_summary());
                ps.setInt(4, Integer.parseInt(event.getEvent_size()));
                Clob clob = con.createClob();
                clob.setString(1, event.getEvent_details());
                ps.setClob(5, clob);
                ps.addBatch();
                count += 1;
                if(count % 50 == 0){
                    results = ps.executeBatch();
                    ps.clearParameters();
                    response.getWriter().write(Integer.toString(count) + " ... ");
                }
            }
            results = ps.executeBatch();

        } catch (SQLException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, "SQL Exception", ex);
        } finally {
            try {
                if(ps != null){                
                    ps.close();
                }
            } catch (SQLException e) {
                logger.info("SQLException in closing PreparedStatement");
            }
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("List size:");
        response.getWriter().write(Integer.toString(list.size()));
        response.getWriter().write(" content length:");
        response.getWriter().write(Integer.toString(request.getContentLength()));
        if(results != null) {
            for(int i = 0; i < results.length; i += 1){
                response.getWriter().write(Integer.toString(results[i]) + ",");
            }
        }
            
    }

}
