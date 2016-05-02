package eventlist.model;

import java.util.Date;
import java.util.List;

public class GetEventsViewModel {

	private int numEvents;
	private int numEventsReturned;
	private Date startDate;
	private Date endDate;
	
	private List<Event> eventsList;

	public List<Event> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<Event> eventsList) {
		this.eventsList = eventsList;
	}

	public int getNumEvents() {
		return numEvents;
	}

	public void setNumEvents(int numEvents) {
		this.numEvents = numEvents;
	}

	public int getNumEventsReturned() {
		return numEventsReturned;
	}

	public void setNumEventsReturned(int numEventsReturned) {
		this.numEventsReturned = numEventsReturned;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
