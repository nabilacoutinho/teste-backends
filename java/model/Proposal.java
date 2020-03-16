package model;

import java.util.ArrayList;
import java.util.List;

public class Proposal {
	
	private String id;
	private double loanValue;
	private int monthlyInstallmentsQty;
	private List<Warranty> warranties;
	private List<Proponent> proponents;

	public Proposal() {
		this.warranties = new ArrayList<Warranty>();
		this.proponents = new ArrayList<Proponent>();
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
	
}
