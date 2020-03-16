package processor;

import exceptions.InvalidEventException;
import model.Proposal;
import model.Proponent;

public class ProponentProcessor {
	
	public static Proposal processProponent(Proposal proposal, String[] inputData) throws InvalidEventException {
		validateInputDataIsComplete(inputData);
		
		proposal = loadProponentInProposal(proposal, inputData);
		
		return proposal;
	}
	
	public static Proposal removeProponent(Proposal proposal, String[] inputData) throws InvalidEventException {
		if (inputData == null || inputData.length != 6) {
			throw new InvalidEventException("Proponent event are bad formatted");
		}
		
		String proponentId = getProponentId(inputData);

		Proponent proponent = getProponent(proposal, proponentId);
		proposal.removeProponent(proponent);
		
		return proposal;
	}

	private static Proposal loadProponentInProposal(Proposal proposal, String[] inputData) throws InvalidEventException {
		String proponentId = getProponentId(inputData);
		
		Proponent proponent = getProponent(proposal, proponentId);
		proponent.setName(inputData[6]);
		proponent.setAge(Integer.valueOf(inputData[7]));
		proponent.setMonthlyIncome(Double.valueOf(inputData[8]));
		proponent.setMain(inputData[9] == "true");
		
		proposal.addProponent(proponent);
		
		return proposal;
	}

	private static void validateInputDataIsComplete(String[] inputData) throws InvalidEventException {
		if (inputData == null || inputData.length != 10) {
			throw new InvalidEventException("Proponent event is bad formatted");
		}
	}
	
	private static String getProponentId(String[] inputData) throws InvalidEventException {
		String proponentId = inputData[5];
		
		if (proponentId.isBlank()) {
			throw new InvalidEventException("Proponent Id is required");
		}
		
		return proponentId;
	}

	private static Proponent getProponent(Proposal proposal, String proponentId) {
		for (Proponent proponent : proposal.getProponents()) {
			if (proponentId.equals(proponent.getId())) {
				return proponent;
			}
		}
		
		Proponent proponent = new Proponent();
		proponent.setId(proponentId);
		
		return proponent;
	}

}
