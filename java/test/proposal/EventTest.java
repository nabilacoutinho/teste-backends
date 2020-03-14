package test.proposal;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import exceptions.InvalidEventException;
import proposal.Event;
import proposal.EventActionEnum;
import proposal.EventSchemaEnum;

public class EventTest {

	@Test
	public void testProcessEvent_WhenEmptyInput_ThenFails() throws InvalidEventException {
		String input = "";
		
		assertThrows(InvalidEventException.class, () -> {			
			Event.processEvent(input);
		});
	}

	@ParameterizedTest
	@MethodSource("getEmptyScenarios")
	public void testProcessEvent_WhenEmptyEventField_ThenFails(String id, String schemaCode, String actionCode, Long timestamp) 
			throws InvalidEventException {
		String input = id + "," + schemaCode +  "," + actionCode + "," + timestamp;
		
		assertThrows(InvalidEventException.class, () -> {			
			Event.processEvent(input);
		});
	}
	
	public static Stream<Arguments> getEmptyScenarios() {
		String id = "123abc";
		String schemaCode = EventSchemaEnum.PROPOSAL.getCode();
		String actionCode = EventActionEnum.CREATE.getCode();
		Long timestamp = Calendar.getInstance().getTimeInMillis();
		
		return Stream.of(
				Arguments.of("", "", "", null),
				Arguments.of("", schemaCode, actionCode, timestamp),
				Arguments.of(id, "", actionCode, timestamp),
				Arguments.of(id, schemaCode, "", timestamp),
				Arguments.of(id, schemaCode, actionCode, null)
			);
	}

}
