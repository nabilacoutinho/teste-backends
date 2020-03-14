package proposal;

import java.util.Date;

import exceptions.InvalidEventException;

public class Event {

	private String id;
	private EventSchemaEnum schema;
	private EventActionEnum action;
	private Date time;
	
	public static void processEvent(String input) throws InvalidEventException {
		String[] inputData = input.split(",");
		
		if (inputData.length < 4) {
			throw new InvalidEventException();
		}
		
		Event event = new Event();
		event.setId(inputData[0]);
		event.setSchema(inputData[1]);
		event.setAction(inputData[2]);
		event.setTime(Long.valueOf(inputData[3]));
		
		event.validate();
	}
	
	public void validate() throws InvalidEventException {
		if (id.isBlank()) {
			throw new InvalidEventException("Event Id is required");
		}

		if (schema == null) {
			throw new InvalidEventException("Event Schema is required");
		}
		
		if (action == null) {
			throw new InvalidEventException("Event Action is required");
		}
		
		if (time == null) {
			throw new InvalidEventException("Event Time is required");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EventSchemaEnum getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = EventSchemaEnum.getByCode(schema);
	}

	public EventActionEnum getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = EventActionEnum.getByCode(action);
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public void setTime(Long timestamp) {
		Date time = new Date(timestamp);

		this.setTime(time);
	}
	
}
