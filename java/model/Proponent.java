package model;

public class Proponent {
	
	private String id;
	private String name;
	private int age;
	private double monthlyIncome;
	private boolean isMain = false;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getMonthlyIncome() {
		return monthlyIncome;
	}
	
	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	
	public boolean isMain() {
		return isMain;
	}
	
	public void setMain(boolean isMain) {
		this.isMain = isMain;
	}

}
