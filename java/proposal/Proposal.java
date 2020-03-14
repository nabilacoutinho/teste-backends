package proposal;

public class Proposal {
	
	private String id;
	private double loanValue;
	private int monthlyInstallmentsQty;

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

}
