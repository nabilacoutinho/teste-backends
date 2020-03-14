package test.proposal;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import exceptions.InvalidEventException;
import proposal.Event;
import proposal.EventActionEnum;
import proposal.EventSchemaEnum;
import proposal.Proposal;

public class EventTest {
	
	@Test
	public void testValidate_WhenEmptyId_ThenFails() {
		Event event = Event.builder()
				.withSchema(EventSchemaEnum.PROPOSAL)
				.withAction(EventActionEnum.CREATED)
				.withTime(Calendar.getInstance().getTime())
				.withProposal(new Proposal())
				.build();
		
		assertThrows(InvalidEventException.class, () -> {
			event.validate();
		});
	}
	
	@Test
	public void testValidate_WhenEmptySchema_ThenFails() {
		Event event = Event.builder()
				.withId("1234")
				.withAction(EventActionEnum.CREATED)
				.withTime(Calendar.getInstance().getTime())
				.withProposal(new Proposal())
				.build();
		
		assertThrows(InvalidEventException.class, () -> {
			event.validate();
		});
	}
	
	@Test
	public void testValidate_WhenEmptyAction_ThenFails() {
		Event event = Event.builder()
				.withId("1234")
				.withSchema(EventSchemaEnum.PROPOSAL)
				.withTime(Calendar.getInstance().getTime())
				.withProposal(new Proposal())
				.build();
		
		assertThrows(InvalidEventException.class, () -> {
			event.validate();
		});
	}
	
	@Test
	public void testValidate_WhenEmptyTime_ThenFails() {
		Event event = Event.builder()
				.withId("1234")
				.withSchema(EventSchemaEnum.PROPOSAL)
				.withAction(EventActionEnum.CREATED)
				.withProposal(new Proposal())
				.build();
		
		assertThrows(InvalidEventException.class, () -> {
			event.validate();
		});
	}
	
	@Test
	public void testValidate_WhenEmptyProposal_ThenFails() {
		Event event = Event.builder()
				.withId("1234")
				.withSchema(EventSchemaEnum.PROPOSAL)
				.withAction(EventActionEnum.CREATED)
				.withTime(Calendar.getInstance().getTime())
				.build();
		
		assertThrows(InvalidEventException.class, () -> {
			event.validate();
		});
	}

}
