package model;

import java.util.ArrayList;
import java.util.List;

public class Proposal {

	private final static int LOAN_VALUE_MIN_LIMIT = 30000;
	private final static int LOAN_VALUE_MAX_LIMIT = 3000000;
	private final static int INSTALLMENTS_MIN_LIMIT = 24;
	private final static int INSTALLMENTS_MAX_LIMIT = 180;
	
	private String id;
	private double loanValue;
	private int monthlyInstallmentsQty;
	private List<Warranty> warranties;
	private List<Proponent> proponents;

	public Proposal() {
		this.warranties = new ArrayList<Warranty>();
		this.proponents = new ArrayList<Proponent>();
	}
	
	public boolean isValid() {
		return isValidLoanValue() && isValidInstallmentsQty();
	}

	private boolean isValidLoanValue() {
		return this.loanValue >= LOAN_VALUE_MIN_LIMIT && this.loanValue <= LOAN_VALUE_MAX_LIMIT;
	}
	
	private boolean isValidInstallmentsQty() {
		return this.monthlyInstallmentsQty >= INSTALLMENTS_MIN_LIMIT && this.monthlyInstallmentsQty <= INSTALLMENTS_MAX_LIMIT;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(double loanValue) {
		this.loanValue = loanValue;
	}

	public int getMonthlyInstallmentsQty() {
		return monthlyInstallmentsQty;
	}

	public void setMonthlyInstallmentsQty(int monthlyInstallmentsQty) {
		this.monthlyInstallmentsQty = monthlyInstallmentsQty;
	}

	public List<Warranty> getWarranties() {
		return warranties;
	}

	public void addWarranty(Warranty warranty) {
		int warrantyIndex = this.warranties.lastIndexOf(warranty);
		if (warrantyIndex > -1) {
			this.warranties.add(warrantyIndex, warranty);
			return;
		}
		
		this.warranties.add(warranty);
	}

	public void removeWarranty(Warranty warranty) {		
		this.warranties.remove(warranty);
	}

	public List<Proponent> getProponents() {
		return proponents;
	}

	public void addProponent(Proponent proponent) {
		int proponentIndex = this.proponents.lastIndexOf(proponent);
		if (proponentIndex > -1) {
			this.proponents.add(proponentIndex, proponent);
			return;
		}
		
		this.proponents.add(proponent);
	}

	public void removeProponent(Proponent proponent) {
		this.proponents.remove(proponent);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Proposal proposal;
		
		private Builder() {
			this.proposal = new Proposal();
		}
		
		public Builder id(String id) {
			this.proposal.id = id;
			
			return this;
		}
		
		public Builder loanValue(double loanValue) {
			this.proposal.loanValue = loanValue;
			
			return this;
		}
		
		public Builder monthlyInstallmentsQty(int monthlyInstallmentsQty) {
			this.proposal.monthlyInstallmentsQty = monthlyInstallmentsQty;
			
			return this;
		}
		
		public Builder warranties(List<Warranty> warranties) {
			this.proposal.warranties = warranties;
			
			return this;
		}
		
		public Builder proponents(List<Proponent> proponents) {
			this.proposal.proponents = proponents;
			
			return this;
		}
		
		public Proposal build() {
			return this.proposal;
		}

	}
}
