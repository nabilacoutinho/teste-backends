package test.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.InvalidEventException;
import model.EventActionEnum;
import model.EventSchemaEnum;
import model.Proposal;
import processor.EventsProcessor;

public class EventsProcessorTest {

	private static final String PROPOSAL_DELETED_EVENT_ID = "123abc";
	private static final String PROPOSAL_ID = "abc123";
	private static final String PROPOSAL_CREATED_EVENT_ID = "345cde";
	private static final String PROPOSAL_UPDATED_EVENT_ID = "456def";

	@Test
	public void testProcessEvent_WhenEmptyInput_ThenFails() {
		String input = "";
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(input);
		});
	}

	@ParameterizedTest
	@MethodSource("getEmptyScenarios")
	public void testProcessEvent_WhenEmptyEventField_ThenFails(String id, String schemaCode, String actionCode, Long timestamp, String proposalId) {
		String input = id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(input);
		});
	}
	
	public static Stream<Arguments> getEmptyScenarios() {
		String id = PROPOSAL_DELETED_EVENT_ID;
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.DELETED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = PROPOSAL_ID;
		
		return Stream.of(
				Arguments.of("", "", "", null, ""),
				Arguments.of("", schemaCode, actionCode, timestamp, proposalId),
				Arguments.of(id, "", actionCode, timestamp, proposalId),
				Arguments.of(id, schemaCode, "", timestamp, proposalId),
				Arguments.of(id, schemaCode, actionCode, null, proposalId),
				Arguments.of(id, schemaCode, actionCode, timestamp, null)
			);
	}
	
	@Test
	public void testProcessEvent_WhenDuplicatedInput_ThenFails() throws InvalidEventException {
		String input = getProposalDeletedInput();
		
		EventsProcessor.processEvent(input);
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(input);
		});
	}

	@Test
	public void testProcessEvent_WhenDelayedInput_ThenFails() throws InvalidEventException {
		String input = getProposalDeletedInput();
		String delayedInput = getProposalDeletedDelayedInput();
		
		EventsProcessor.processEvent(input);
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(delayedInput);
		});
	}
	
	@Test
	public void testProcessEvent_WhenUnavailableProposalAction_ThenFails() throws InvalidEventException {
		String id = PROPOSAL_DELETED_EVENT_ID;
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.ADDED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = PROPOSAL_ID;

		String input = id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(input);
		});
	}

	@Test
	public void testProcessEvent_WhenProposalCreatedEvent_ThenAddProposal() throws InvalidEventException {
		double loanValue = 150000;
		int installments = 24;
		
		String createdInput = getProposalCreatedInput(loanValue, installments);
		
		EventsProcessor.processEvent(createdInput);
		
		assertProposal(loanValue, installments);
	}

	@Test
	public void testProcessEvent_WhenProposalUpdatedEvent_ThenUpdateProposal() throws InvalidEventException {
		double loanValue = 150000;
		int installments = 24;
		
		String createdInput = getProposalCreatedInput(0.0, 1);
		String updatedInput = getProposalUpdatedInput(loanValue, installments);
		
		EventsProcessor.processEvent(createdInput);
		EventsProcessor.processEvent(updatedInput);
		
		assertProposal(loanValue, installments);
	}

	@Test
	public void testProcessEvent_WhenProposalDeletedEvent_ThenDeleteProposal() throws InvalidEventException {
		String createdInput = getProposalCreatedInput(0.0, 1);
		String deletedInput = getProposalDeletedInput();
		
		EventsProcessor.processEvent(createdInput);
		EventsProcessor.processEvent(deletedInput);
		
		assertEquals(0, EventsProcessor.getProposals().size());
	}
	
	private String getProposalCreatedInput(double loanValue, int installments) {
		String id = PROPOSAL_CREATED_EVENT_ID;
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.CREATED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = PROPOSAL_ID;

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId + "," + loanValue + "," + installments;
	}
	
	private String getProposalUpdatedInput(double loanValue, int installments) {
		String id = PROPOSAL_UPDATED_EVENT_ID;
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.UPDATED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = PROPOSAL_ID;

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId + "," + loanValue + "," + installments;
	}
	
	private String getProposalDeletedInput() {
		String id = PROPOSAL_DELETED_EVENT_ID;
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.DELETED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = PROPOSAL_ID;

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
	}

	private String getProposalDeletedDelayedInput() {
		String id = "234bcd";
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.DELETED.getCode();

		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Long timestamp = yesterday.getTimeInMillis();
		String proposalId = PROPOSAL_ID;

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
	}

	private static void assertProposal(double loanValue, int installments) {
		List<Proposal> processedProposals = EventsProcessor.getProposals();
		assertEquals(1, processedProposals.size());
		
		Proposal proposal = processedProposals.get(0);
		assertEquals(PROPOSAL_ID, proposal.getId());
		assertEquals(loanValue, proposal.getLoanValue());
		assertEquals(installments, proposal.getMonthlyInstallmentsQty());
	}

}
