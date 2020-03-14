package model;

public class Warranty {

	private String id;
	private double value;
	private WarrantyProvinceEnum province;
	private Proposal proposal;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public WarrantyProvinceEnum getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = WarrantyProvinceEnum.getByCode(province);
	}
	
	public Proposal getProposal() {
		return proposal;
	}
	
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
		
}
