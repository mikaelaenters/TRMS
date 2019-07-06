package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.pojos.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService {

	ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	@Override
	public double eventToPaybackPercentage(String eventType) {
		
		double percentPayback;
		
			switch(eventType) {
				case "University Course": percentPayback = 0.80;
										  break;
				case "Seminar": percentPayback = 0.60;
								break;
				case "Certification Prep Class": percentPayback = 0.75;
												 break;
				case "Certification": percentPayback = 1.00;
									  break;
				case "Technical Training": percentPayback = 0.90;
										   break;
				case "Other": percentPayback = 0.30;
							  break;
				default: percentPayback = -1;
			}
		return percentPayback;
	}

	@Override
	public void createReimbursement(Reimbursement application) {
		
		reimbursementDao.createReimbursement(application);
		
	}
	
	@Override
	public void approveOrDenyReimbursement(int formId, String choice, String moreInfo, int departmentId) {
		
		int status;
		
		if(choice.equals("Accept")) {
			status = departmentId;
			if(departmentId == 4) {
				calculateReimbursementAmountsApprovalFirst(formId);
			}
			reimbursementDao.updateApplicationInformation(formId, moreInfo, status);
		} else if(choice.equals("Deny")) {
			status = 7;    //deny status 
			reimbursementDao.updateApplicationInformation(formId, moreInfo, status);
		} else {
			status = 8;    //request more information status.
			reimbursementDao.updateApplicationInformation(formId, moreInfo, status);
		}
		

	}


	@Override
	public List<Reimbursement> viewAllPendingReimbursements(int departmentId){
		int status = 0;
		
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		
		switch(departmentId) {
			case 2: status = 1;
					break;
			case 3: status = 2;
					break;
			case 4: status = 3;
					break;
		}
		
		if (status == 0) {
			return null;
		}
		
		pendingReimbursements = reimbursementDao.getAllPendingApplications(status);
		
		return pendingReimbursements;
	}

	@Override
	public List<Reimbursement> viewAllEmployeeReimbursements(int employeeId) {
		List<Reimbursement> allReimbursements = new ArrayList<Reimbursement>();
		
		allReimbursements = reimbursementDao.getEmployeeApplications(employeeId);
		
		return allReimbursements;
	}

	@Override
	public void updateFinalGrade(int formId, String finalGrade) {
		reimbursementDao.updateFinalGradeInformation(formId, finalGrade);	
	}

	@Override
	public List<Reimbursement> viewFinalGrades() {
		List<Reimbursement> finalGradesList = new ArrayList<Reimbursement>();
		finalGradesList = reimbursementDao.getFinalGrades();
		
		return finalGradesList;
	}

	@Override
	public void approveOrDenyFinalGrade(int formId, String choice) {
		int status;
		
		if(choice.equals("Accept")) {
			
			status = 6;  //Approval of final grade status
			reimbursementDao.updateFinalGradeApproveOrDeny(formId, status);
			calculateReimbursementAmountsApprovalSecond(formId);
			
			
				
		} else {
		
			status = 7;    //Deny status 
			reimbursementDao.updateFinalGradeApproveOrDeny(formId, status);	
			calculateReimbursementAmountsDenial(formId);
		}
	}

	@Override
	public void calculateReimbursementAmountsApprovalFirst(int formId) {
		
		Reimbursement thisReimbursement = reimbursementDao.getApplicationById(formId);
		int employeeId = employeeDao.getEmployeeIdByEmail(thisReimbursement.getEmail());
		double percentage = eventToPaybackPercentage(thisReimbursement.getEventType());
		double pendingAmount = thisReimbursement.getPendingAmount() + (thisReimbursement.getCost() * percentage);
		
		double availableAmount = 1000.00 - (pendingAmount + thisReimbursement.getAwardedAmount());
		
		employeeDao.updatePendingAmount(pendingAmount, employeeId);
		employeeDao.updateAvailableAmount(availableAmount, employeeId);	
		
	}

	@Override
	public void calculateReimbursementAmountsApprovalSecond(int formId) {
		Reimbursement thisReimbursement = reimbursementDao.getApplicationById(formId);
		int employeeId = employeeDao.getEmployeeIdByEmail(thisReimbursement.getEmail());
		double percentage = eventToPaybackPercentage(thisReimbursement.getEventType());
		double pendingAmount = thisReimbursement.getPendingAmount() - (thisReimbursement.getCost() * percentage);
		double awardedAmount = thisReimbursement.getCost() * percentage; 
		
		
		employeeDao.updatePendingAmount(pendingAmount, employeeId);
		employeeDao.updateAwardedAmount(awardedAmount, employeeId);		
	}

	@Override
	public void calculateReimbursementAmountsDenial(int formId) {
		Reimbursement thisReimbursement = reimbursementDao.getApplicationById(formId);
		int employeeId = employeeDao.getEmployeeIdByEmail(thisReimbursement.getEmail());
		double percentage = eventToPaybackPercentage(thisReimbursement.getEventType());
		double pendingAmount = thisReimbursement.getPendingAmount() - (thisReimbursement.getCost() * percentage);
		double availableAmount = 1000.00 - (pendingAmount + thisReimbursement.getAwardedAmount());
		
		employeeDao.updatePendingAmount(pendingAmount, employeeId);
		employeeDao.updateAvailableAmount(availableAmount, employeeId);
		
	}
	

}
