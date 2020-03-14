package processor;

import exceptions.InvalidEventException;
import model.Proposal;
import model.Warranty;

public class WarrantyProcessor {

	public static Proposal processWarranty(Proposal proposal, String[] inputData) throws InvalidEventException {
		validateInputDataIsComplete(inputData);
		
		proposal = loadWarrantyInProposal(proposal, inputData);
		
		return proposal;
	}
	
	public static Proposal removeWarranty(Proposal proposal, String[] inputData) throws InvalidEventException {
		if (inputData == null || inputData.length != 6) {
			throw new InvalidEventException("Warranty event are bad formatted");
		}
		
		String warrantyId = getWarrantyId(inputData);

		Warranty warranty = getWarranty(proposal, warrantyId);
		proposal.removeWarranty(warranty);
		
		return proposal;
	}

	private static Proposal loadWarrantyInProposal(Proposal proposal, String[] inputData) throws InvalidEventException {
		String warrantyId = getWarrantyId(inputData);
		
		Warranty warranty = getWarranty(proposal, warrantyId);
		warranty.setValue(Double.valueOf(inputData[6]));
		warranty.setProvince(inputData[7]);
		
		if (!warranty.getProvince().isAllowed()) {
			throw new InvalidEventException("Warranty Province not allowed");
		}
		
		proposal.addWarranty(warranty);
		
		return proposal;
	}

	private static void validateInputDataIsComplete(String[] inputData) throws InvalidEventException {
		if (inputData == null || inputData.length != 8) {
			throw new InvalidEventException("Warranty event is bad formatted");
		}
	}
	
	private static String getWarrantyId(String[] inputData) throws InvalidEventException {
		String warrantyId = inputData[5];
		
		if (warrantyId.isBlank()) {
			throw new InvalidEventException("Warranty Id is required");
		}
		
		return warrantyId;
	}

	private static Warranty getWarranty(Proposal proposal, String warrantyId) {
		for (Warranty warranty : proposal.getWarranties()) {
			if (warrantyId.equals(warranty.getId())) {
				
			}
		}
		Warranty warranty = new Warranty();
		
		return warranty;
	}

}
