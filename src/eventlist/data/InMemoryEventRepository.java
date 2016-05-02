package eventlist.data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import eventlist.model.Event;
import eventlist.model.EventPeriodType;

public class InMemoryEventRepository implements IEventRepository {

	@Override
	public List<Event> GetEvents(Date startDate, EventPeriodType periodType) {
		List<Event> events = new LinkedList<Event>();
		events.add(new Event());
		events.add(new Event());
		events.add(new Event());
		return events;
	}

}
