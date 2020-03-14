package processor;

import exceptions.InvalidEventException;
import model.Proposal;

public class ProposalsProcessor {

	public static Proposal createProposal(Proposal proposal, String[] inputData) throws InvalidEventException {
		validateRequiredFields(inputData);

		loadProposalValues(proposal, inputData);
		
		return proposal;
	}

	public static Proposal updateProposal(Proposal proposal, String[] inputData) throws InvalidEventException {
		validateRequiredFields(inputData);

		loadProposalValues(proposal, inputData);
		
		return proposal;
	}
	
	private static void validateRequiredFields(String[] inputData) throws InvalidEventException {
		if (inputData == null || inputData.length != 7) {
			throw new InvalidEventException("Proposal event are bad formatted");
		}
	}

	private static void loadProposalValues(Proposal proposal, String[] inputData) {
		proposal.setId(inputData[4]);
		proposal.setLoanValue(Double.valueOf(inputData[5]));
		proposal.setMonthlyInstallmentsQty(Integer.valueOf(inputData[6]));
	}
	
}
