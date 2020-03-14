package proposal;

import java.util.Date;

import exceptions.InvalidEventException;

public class Event {

	private String id;
	private EventSchemaEnum schema;
	private EventActionEnum action;
	private Date time;
	private Proposal proposal;
	
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
		
		if (proposal == null || proposal.getId().isBlank()) {
			throw new InvalidEventException("Event Proposal is required");
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
	
	public Proposal getProposal() {
		return proposal;
	}
	
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Event event;
		
		private Builder() {
			event = new Event();
		}

		public Builder withId(String id) {
			event.id = id;
			
			return this;
		}
		
		public Builder withSchema(EventSchemaEnum schema) {
			event.schema = schema;
			
			return this;
		}
		
		public Builder withAction(EventActionEnum action) {
			event.action = action;
			
			return this;
		}
		
		public Builder withTime(Date time) {
			event.time = time;
			
			return this;
		}
		
		public Builder withProposal(Proposal proposal) {
			event.proposal = proposal;
			
			return this;
		}
		
		public Event build() {
			return event;
		}
		
	}
	
}
