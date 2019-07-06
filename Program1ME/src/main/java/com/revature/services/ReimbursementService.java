package com.revature.services;

import java.util.List;

import com.revature.pojos.Reimbursement;

public interface ReimbursementService {
	
	public double eventToPaybackPercentage(String eventType);
	public void createReimbursement(Reimbursement application);
	public void approveOrDenyReimbursement(int formId, String choice, String moreInfo, int departmentId);
	public List<Reimbursement> viewAllPendingReimbursements(int status);
	public List<Reimbursement> viewAllEmployeeReimbursements(int employeeId);
	public void updateFinalGrade(int formId, String finalGrade);
	public List<Reimbursement> viewFinalGrades();
	public void approveOrDenyFinalGrade(int formId, String choice);
	public void calculateReimbursementAmountsApprovalFirst(int formId);
	public void calculateReimbursementAmountsApprovalSecond(int formId);
	public void calculateReimbursementAmountsDenial(int formId);
	
}
