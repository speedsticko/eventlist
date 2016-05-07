package eventlist.model;

import org.joda.time.LocalDate;

/**
 * GetDataTablesRequestDTP encapsulates a request from the client for paged events
 * to display in a jQuery DataTables.
 * 
 * @author Kwan
 */
public class GetDataTablesRequestDTO {

    private int draw;
    private int start;
    private int length;
    private LocalDate date;
    private EventPeriodType period;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EventPeriodType getPeriod() {
        return period;
    }

    public void setPeriod(EventPeriodType period) {
        this.period = period;
    }
}
