package test.processor;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.InvalidEventException;
import processor.EventsProcessor;
import proposal.EventActionEnum;
import proposal.EventSchemaEnum;

public class EventsProcessorTest {

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
		String id = "123abc";
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.CREATED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = "abc123";
		
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
		String input = getInput();
		
		EventsProcessor.processEvent(input);
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(input);
		});
	}
	
	@Test
	public void testProcessEvent_WhenDelayedInput_ThenFails() throws InvalidEventException {
		String input = getInput();
		String delayedInput = getDelayedInput();
		
		EventsProcessor.processEvent(input);
		
		assertThrows(InvalidEventException.class, () -> {			
			EventsProcessor.processEvent(delayedInput);
		});
	}

	private String getInput() {
		String id = "123abc";
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.CREATED.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		String proposalId = "abc123";

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
	}

	private String getDelayedInput() {
		String id = "234bcd";
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.CREATED.getCode();

		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Long timestamp = yesterday.getTimeInMillis();
		String proposalId = "abc123";

		return id + "," + schemaCode +  "," + actionCode + "," + timestamp + "," + proposalId;
	}

}
