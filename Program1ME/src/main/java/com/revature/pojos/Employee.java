package com.revature.pojos;

public class Employee {
	
	//Fields;
	protected String fullname,
					 email,
					 password;
	protected double availableReimbursement,
					 awardedReimbursement,
					 pendingReimbursement;
	protected int employeeId,
				  departmentId;

	//Constructors;
	public Employee( int employeeId, int departmentId, String fullname, String email, String password, double availableReimbursement,
			double awardedReimbursement, double pendingReimbursement) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.availableReimbursement = availableReimbursement;
		this.awardedReimbursement = awardedReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}

	public Employee() {
	
	}

	//Getters &Setters;
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public double getAwardedReimbursement() {
		return awardedReimbursement;
	}

	public void setAwardedReimbursement(double awardedReimbursement) {
		this.awardedReimbursement = awardedReimbursement;
	}

	public double getPendingReimbursement() {
		return pendingReimbursement;
	}

	public void setPendingReimbursement(double pendingReimbursement) {
		this.pendingReimbursement = pendingReimbursement;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

}
