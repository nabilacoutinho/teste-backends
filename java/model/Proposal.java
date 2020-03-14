package model;

import java.util.ArrayList;
import java.util.List;

public class Proposal {
	
	private String id;
	private double loanValue;
	private int monthlyInstallmentsQty;
	private List<Warranty> warranties;

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
		if (this.warranties == null) {
			warranties = new ArrayList<Warranty>();
		}
		
		int warrantyIndex = this.warranties.lastIndexOf(warranty);
		if (warrantyIndex > -1) {
			this.warranties.add(warrantyIndex, warranty);
			return;
		}
		
		this.warranties.add(warranty);
	}

	public void removeWarranty(Warranty warranty) {
		if (this.warranties == null) {
			return;
		}
		
		this.warranties.remove(warranty);
	}
	
}
