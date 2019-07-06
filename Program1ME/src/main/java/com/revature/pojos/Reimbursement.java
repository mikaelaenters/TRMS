package com.revature.pojos;

public class Reimbursement {
	
	//Fields;
	private String location,
				   description,
				   completedGrade,
				   passingGrade,
				   submissionDate,
				   eventDate,
			       eventTime,
			       eventType,
			       gradingFormat,
			       email,
			       fullName;
	private double cost,
	               percentagePayback,
	               awardedAmount,       //Used for display purposes. 
	               pendingAmount,
	               availableAmount;
	private int status,
				reimbursementId,
				employeeId;
	
	//Constructors;
	public Reimbursement(int reimbursementId, int employeeId, int status, String eventType, String eventDate, String location, String description,
			String eventTime, double cost, double percentagePayback, String gradingFormat, String passingGrade, String completedGrade) {
		//This one is used for the ReimbursementDao get back
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.status = status;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.location = location;
		this.description = description;
		this.eventTime = eventTime;
		this.cost = cost;
		this.percentagePayback = percentagePayback;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
		this.completedGrade = completedGrade;
	}
	
	public Reimbursement(int employeeId, String eventType, String eventDate, String eventTime, String location, double cost, String description,
			 double percentagePayback, String gradingFormat, String passingGrade) {
		//This is used to put application in database
		super();
		this.employeeId = employeeId;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.location = location;
		this.description = description;
		this.eventTime = eventTime;
		this.cost = cost;
		this.percentagePayback = percentagePayback;
		this.gradingFormat = gradingFormat;
		this.passingGrade = passingGrade;
	}
	
	public Reimbursement(int reimbursementId, String email, String fullName, String eventType, String eventDate,
			double cost, String description, double availableAmount, double pendingAmount, double awardedAmount) {
		//This is used for displaying on the web through AJAX
		this.reimbursementId = reimbursementId;
		this.email = email;
		this.fullName = fullName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.description = description;
		this.cost = cost;
		this.availableAmount = availableAmount;
		this.pendingAmount = pendingAmount;
		this.awardedAmount = awardedAmount;	
	}
	
	public Reimbursement(int reimbursementId, String fullName, String eventType, String eventDate,
			double cost, String description, String passingGrade, String completedGrade, double availableAmount, double pendingAmount, double awardedAmount) {
		//This is used for displaying on the web through AJAX for THEIR FORMS!
		this.reimbursementId = reimbursementId;
		this.fullName = fullName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.description = description;
		this.cost = cost;
		this.passingGrade = passingGrade;
		this.completedGrade = completedGrade;
		this.availableAmount = availableAmount;
		this.pendingAmount = pendingAmount;
		this.awardedAmount = awardedAmount;	
	}
	
	
	//Getters &Setters;
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompletedGrade() {
		return completedGrade;
	}

	public void setCompletedGrade(String completedGrade) {
		this.completedGrade = completedGrade;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(String submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPercentagePayback() {
		return percentagePayback;
	}

	public void setPercentagePayback(double percentagePayback) {
		this.percentagePayback = percentagePayback;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAwardedAmount() {
		return awardedAmount;
	}

	public void setAwardedAmount(double awardedAmount) {
		this.awardedAmount = awardedAmount;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public double getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}	
}
