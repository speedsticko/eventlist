package eventlist.etl;

public class EventRecord {
	
	private String event_date;
	private String event_type;
	private String event_summary;
	private String event_size;
	private String event_details;

	public EventRecord(String event_date, String event_type, String event_summary, String event_size, String event_details){
		this.event_date = event_date;
		this.event_type = event_type;
		this.event_summary = event_summary;
		this.event_size = event_size;
		this.event_details = event_details;
	}
	
}
