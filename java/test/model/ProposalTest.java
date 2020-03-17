package test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import model.Proposal;

public class ProposalTest {

	private static final int VALID_LOAN_VALUE = 100000;
	private static final int VALID_INSTALLMENTS = 30;

//	@Test
//	public void testIsValid_WhenAllConditionsAreFine_ThenReturnTrue() {
//		Proposal proposal = Proposal.builder()
//				.loanValue(VALID_LOAN_VALUE)
//				.monthlyInstallmentsQty(VALID_INSTALLMENTS)
//				.build();
//		
//		boolean response = proposal.isValid();
//		
//		assertTrue(response);
//	}

	@Test
	public void testIsValid_WhenProposalLoanValueAboveLimit_ThenReturnFalse() {
		Proposal proposal = Proposal.builder()
				.loanValue(9999999)
				.monthlyInstallmentsQty(VALID_INSTALLMENTS)
				.build();
		
		boolean response = proposal.isValid();
		
		assertFalse(response);
	}

	@Test
	public void testIsValid_WhenProposalLoanValueBelowLimit_ThenReturnFalse() {
		Proposal proposal = Proposal.builder()
				.loanValue(1)
				.monthlyInstallmentsQty(VALID_INSTALLMENTS)
				.build();
		
		boolean response = proposal.isValid();
		
		assertFalse(response);
	}

	@Test
	public void testIsValid_WhenProposalInstallmentIsAboveLimit_ThenReturnFalse() {
		Proposal proposal = Proposal.builder()
				.loanValue(VALID_LOAN_VALUE)
				.monthlyInstallmentsQty(200)
				.build();
		
		boolean response = proposal.isValid();
		
		assertFalse(response);
	}

	@Test
	public void testIsValid_WhenProposalInstallmentIsBelowLimit_ThenReturnFalse() {
		Proposal proposal = Proposal.builder()
				.loanValue(VALID_LOAN_VALUE)
				.monthlyInstallmentsQty(2)
				.build();
		
		boolean response = proposal.isValid();
		
		assertFalse(response);
	}
	
	// TODO: add other validation tests
	
}
