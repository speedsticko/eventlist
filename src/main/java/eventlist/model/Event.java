package eventlist.model;

import java.util.Date;

/**
 * An Event represents a single event in our model.
 * 
 * @author klim
 */
public class Event {

    private Date eventDate;
    private String eventType;
    private String eventSummary;
    private int eventMetric;
    private String eventDetails;

    public Date get_Date() {
        return eventDate;
    }

    public void set_Date(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String get_Type() {
        return eventType;
    }

    public void set_Type(String eventType) {
        this.eventType = eventType;
    }

    public String get_Summary() {
        return eventSummary;
    }

    public void set_Summary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public int get_Metric() {
        return eventMetric;
    }

    public void set_Metric(int eventMetric) {
        this.eventMetric = eventMetric;
    }

    public String get_Details() {
        return eventDetails;
    }

    public void set_Details(String eventDetails) {
        this.eventDetails = eventDetails;
    }

}
