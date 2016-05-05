/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventlist.data;

import eventlist.model.Event;
import eventlist.model.EventPeriodType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.joda.time.LocalDate;

/**
 *
 * @author Kwan
 */
public class EventsDAO {

    private Connection conn;

    public EventsDAO(Connection conn) {
        this.conn = conn;
    }


    public Object[] GetEventsForDataTable(LocalDate startDate, EventPeriodType periodType) {
        ResultSet rs = null;
        String sql = "select id, event_date, event_type, event_summary, event_size "
                + "from events where event_date >= ? AND event_date <= ?"
                + "fetch first 100 rows only";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startDate.toDate().getTime()));
            
            LocalDate endDate = null;
            if(periodType == EventPeriodType.WEEK){
                endDate = startDate.plusWeeks(1);
            }
            else if(periodType == EventPeriodType.MONTH){
                endDate = startDate.plusMonths(1);
            }
            else if(periodType == EventPeriodType.QUARTER){
                endDate = startDate.plusMonths(3);
            }
            ps.setDate(2, new java.sql.Date(endDate.toDate().getTime()));
            
            rs = ps.executeQuery();
            List<Object> list = new LinkedList<Object>();
            while (rs.next()) {
               list.add(new Object[]{
                   rs.getInt(1), 
                   rs.getDate(2), 
                   rs.getString(3), 
                   rs.getString(4), 
                   rs.getInt(5), 
                   "details"});
            }
            return list.toArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EventsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EventsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return null;
    }

    
}
