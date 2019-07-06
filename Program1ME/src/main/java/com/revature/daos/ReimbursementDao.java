package com.revature.daos;

import java.util.List;

import com.revature.pojos.Reimbursement;

public interface ReimbursementDao {

	public void createReimbursement(Reimbursement application);
	public Reimbursement getApplicationById(int reimbursementId);
	public List<Reimbursement> getAllPendingApplications(int status);
	public List<Reimbursement> getEmployeeApplications(int employeeId);
	public void updateApplicationInformation(int formId, String moreInfo, int status);
	public void updateFinalGradeInformation(int formId, String finalGrade);
	public List<Reimbursement> getFinalGrades();
	public void updateFinalGradeApproveOrDeny(int formId, int status);

}
