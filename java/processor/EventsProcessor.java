package processor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidEventException;
import model.Event;
import model.Proposal;

public class EventsProcessor {

	private List<Proposal> proposals;
	private List<Event> oldEvents;
	
	public EventsProcessor() {
		this.proposals = new ArrayList<Proposal>();
		this.oldEvents = new ArrayList<Event>();
	}
	
	public void processEvent(String input) throws InvalidEventException, ParseException {
		String[] inputData = getInputData(input);
		
		Event event = loadEventFromInputData(inputData);
		
		event.validate();
		validateEventWasNotDuplicated(event);
		validateEventWasNotProcessed(event);
		
		process(inputData, event);
		
		addHistoric(event);
	}

	private String[] getInputData(String input) throws InvalidEventException {
		String[] inputData = input.split(",");
		
		if (inputData == null || inputData.length < 5) {
			throw new InvalidEventException();
		}
		
		return inputData;
	}

	private Event loadEventFromInputData(String[] inputData) throws ParseException {
		Event event = new Event();
		
		event.setId(inputData[0]);
		event.setSchema(inputData[1]);
		event.setAction(inputData[2]);
		event.setTime(inputData[3]);
		event.setProposal(loadProposalFromInputData(inputData));
		
		return event;
	}

	private Proposal loadProposalFromInputData(String[] inputData) {
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

	private void addProposal(Proposal proposal) {
		if (proposals == null) {
			proposals = new ArrayList<Proposal>();
		}
		
		proposals.add(proposal);
	}

	private void validateEventWasNotDuplicated(Event event) throws InvalidEventException {
		if (oldEvents != null) {
			for (Event oldEvent : oldEvents) {
				if (oldEvent.getId().equals(event.getId())) {
					throw new InvalidEventException("Event Duplicated");
				}
			}
		}
	}

	private void validateEventWasNotProcessed(Event event) throws InvalidEventException {
		if (!oldEvents.isEmpty()) {
			for (Event oldEvent : oldEvents) {
				if (oldEvent.getId().equals(event.getId())) {
					throw new InvalidEventException("Event Duplicated");
				}
			}
			
			Event lastEvent = oldEvents.get(oldEvents.size() - 1);
			if (lastEvent.getTime().after(event.getTime())) {
				throw new InvalidEventException("Delayed Events are not allowed");
			}
		}
	}

	private void process(String[] inputData, Event event) throws InvalidEventException {
		switch (event.getSchema()) {
		case PROPOSAL:
			processProposal(inputData, event);
			break;

		case WARRANTY:
			processWarranty(inputData, event);
			break;

		case PROPONENT:
			processProponent(inputData, event);
			break;

		default:
			throw new InvalidEventException("Event Schema not available");
		}
	}

	private void processProposal(String[] inputData, Event event) throws InvalidEventException {
		switch (event.getAction()) {
		case CREATED:
		case UPDATED:
			ProposalsProcessor.processProposal(event.getProposal(), inputData);
			break;
		case DELETED:
			if (inputData.length != 5) {
				throw new InvalidEventException("Proposal event are bad formatted");
			}
			
			proposals.remove(event.getProposal());
			break;
			
		default:
			throw new InvalidEventException("Event Action not available");
		}
	}

	private void processWarranty(String[] inputData, Event event) throws InvalidEventException {
		switch (event.getAction()) {
		case ADDED:
		case UPDATED:
			WarrantyProcessor.processWarranty(event.getProposal(), inputData);
			break;
		case REMOVED:
			WarrantyProcessor.removeWarranty(event.getProposal(), inputData);
			break;
			
		default:
			throw new InvalidEventException("Event Action not available");
		}
	}

	private void processProponent(String[] inputData, Event event) throws InvalidEventException {
		switch (event.getAction()) {
		case ADDED:
		case UPDATED:
			ProponentProcessor.processProponent(event.getProposal(), inputData);
			break;
		case REMOVED:
			ProponentProcessor.removeProponent(event.getProposal(), inputData);
			break;
			
		default:
			throw new InvalidEventException("Event Action not available");
		}
	}
	
	private void addHistoric(Event event) {
		oldEvents.add(event);
	}

	public List<Proposal> getProposals() {
		return proposals;
	}
	
}
