package eventlist.data;

import eventlist.model.EventPeriodType;
import eventlist.model.GetDataTablesRequestDTO;
import eventlist.model.GetDataTablesResponseDTO;
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
 * EventsDAO retrieves DTOs from the events database.
 * @author Kwan
 */
public class EventsDAO {

    private final Connection conn;

    public EventsDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Retrieve the paged event list data.
     * 
     * @param request
     * @return 
     */
    public GetDataTablesResponseDTO GetEventsForDataTable(GetDataTablesRequestDTO request) {
        LocalDate startDate = request.getDate();
        EventPeriodType periodType = request.getPeriod();
        int draw = request.getDraw();
        int start = request.getStart();
        int length = request.getLength();

        ResultSet rs = null;

        String sql = "select event_date, event_type, event_summary, event_size, id "
                + "from events where event_date >= ? AND event_date <= ?"
                + "ORDER BY event_date offset ? rows fetch first ? rows only";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(startDate.toDate().getTime()));

            LocalDate endDate = getEndDate(periodType, startDate);
            
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

            GetDataTablesResponseDTO eventsViewModel = new GetDataTablesResponseDTO();
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

    private LocalDate getEndDate(EventPeriodType periodType, LocalDate startDate) {
        LocalDate endDate = null;
        if (null != periodType) switch (periodType) {
            case WEEK:
                endDate = startDate.plusWeeks(1);
                break;
            case MONTH:
                endDate = startDate.plusMonths(1);
                break;
            case QUARTER:
                endDate = startDate.plusMonths(3);
                break;
            default:
                break;
        }
        if(endDate != null) {
            endDate = endDate.plusDays(-1);
        }
        return endDate;
    }

    /**
     * Retrieve the event details for the given event id.
     * @param id
     * @return 
     */
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
