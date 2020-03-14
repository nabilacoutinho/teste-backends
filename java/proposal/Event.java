package proposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.InvalidEventException;

public class Event {

	private String id;
	private EventSchemaEnum schema;
	private EventActionEnum action;
	private Date time;
	private Proposal proposal;
	
	private static List<Proposal> proposals;
	private static List<Event> oldEvents;
	
	public static void processEvent(String input) throws InvalidEventException {
		String[] inputData = getInputData(input);
		
		Event event = loadEventFromInputData(inputData);
		
		event.validate();
		event.validateEventWasNotProcessed();
		
		addHistoric(event);
	}

	private static void addHistoric(Event event) {
		if (oldEvents == null) {
			oldEvents = new ArrayList<Event>();
		}
		
		oldEvents.add(event);
	}

	private static String[] getInputData(String input) throws InvalidEventException {
		String[] inputData = input.split(",");
		
		if (inputData.length < 5) {
			throw new InvalidEventException();
		}
		
		return inputData;
	}

	private static Event loadEventFromInputData(String[] inputData) {
		Event event = new Event();
		
		event.setId(inputData[0]);
		event.setSchema(inputData[1]);
		event.setAction(inputData[2]);
		event.setTime(Long.valueOf(inputData[3]));

		loadProposalFromInputData(inputData);
		
		return event;
	}

	private static Proposal loadProposalFromInputData(String[] inputData) {
		String proposalId = inputData[4];
		
		for (Proposal proposal : proposals) {
			if (proposalId.equals(proposal.getId())) {
				return proposal;
			}
		}
		
		Proposal proposal = new Proposal();
		proposal.setId(proposalId);
		
		addProposal(proposal);
		
		return proposal;
	}

	private static void addProposal(Proposal proposal) {
		if (proposals == null) {
			proposals = new ArrayList<Proposal>();
		}
		
		proposals.add(proposal);
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
		
		if (proposal == null) {
			throw new InvalidEventException("Event Proposal is required");
		}
		
	}

	private void validateEventWasNotProcessed() throws InvalidEventException {
		if (oldEvents != null) {
			for (Event event : oldEvents) {
				if (event.getId().equals(id)) {
					throw new InvalidEventException("Event Duplicated");
				}
			}
			
			Event lastEvent = oldEvents.get(oldEvents.size() - 1);
			if (lastEvent.getTime().after(time)) {
				throw new InvalidEventException("Delayed Events are not allowed");
			}
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
}
