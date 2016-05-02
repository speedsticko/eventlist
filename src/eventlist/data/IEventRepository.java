package eventlist.data;

import java.util.Date;
import java.util.List;

import eventlist.model.Event;
import eventlist.model.EventPeriodType;

public interface IEventRepository {
	List<Event> GetEvents(Date startDate, EventPeriodType periodType);
}
