/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventlist.data;

import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesRequestViewModel;
import eventlist.model.GetDataTablesViewModel;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.LocalDate;

/**
 *
 * @author Kwan
 */
public class EventsDAO {

    private final Connection conn;

    public EventsDAO(Connection conn) {
        this.conn = conn;
    }

    public GetDataTablesViewModel GetEventsForDataTable(GetDataTablesRequestViewModel request) {
        LocalDate startDate = request.getDate();
        EventPeriodType periodType = request.getPeriod();
        int draw = request.getDraw();
        int start = request.getStart();
        int length = request.getLength();

        //http://aravindamadusanka.blogspot.com/2013/07/apache-derby-querying.html
        ResultSet rs = null;

        String sql = "select event_date, event_type, event_summary, event_size, id "
                + "from events where event_date >= ? AND event_date <= ?"
                + "ORDER BY event_date offset ? rows fetch first ? rows only";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startDate.toDate().getTime()));

            LocalDate endDate = null;
            if (periodType == EventPeriodType.WEEK) {
                endDate = startDate.plusWeeks(1);
            } else if (periodType == EventPeriodType.MONTH) {
                endDate = startDate.plusMonths(1);
            } else if (periodType == EventPeriodType.QUARTER) {
                endDate = startDate.plusMonths(3);
            }
            ps.setDate(2, new java.sql.Date(endDate.toDate().getTime()));
            ps.setInt(3, start);
            ps.setInt(4, length);
            rs = ps.executeQuery();
            List<Object> list = new LinkedList<Object>();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getDate(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
                });
            }
            rs.close();

            // -- Total query
            sql = "select count(*) from events where event_date >= ? AND event_date <= ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startDate.toDate().getTime()));
            ps.setDate(2, new java.sql.Date(endDate.toDate().getTime()));
            rs = ps.executeQuery();

            int totalRows = 0;

            if (rs.next()) {
                totalRows = rs.getInt(1);
            }

            GetDataTablesViewModel eventsViewModel = new GetDataTablesViewModel();
            eventsViewModel.setData(list.toArray());
            eventsViewModel.setDraw(draw);
            eventsViewModel.setRecordsFiltered(totalRows);
            eventsViewModel.setRecordsTotal(totalRows);
            return eventsViewModel;
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

    public String GetEventDetails(int id) {
        ResultSet rs = null;
        String sql = "select event_detail "
                + "from events where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                Clob clob = rs.getClob(1);
                return clob.getSubString(1, (int) clob.length());
            }
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
