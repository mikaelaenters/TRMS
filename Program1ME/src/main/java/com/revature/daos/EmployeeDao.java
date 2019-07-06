package com.revature.daos;

import java.util.List;

import com.revature.pojos.Employee;

public interface EmployeeDao {
	
	public int getEmployeeIdByEmail(String email);
	public Employee getEmployeeInfo(String email);
	public int getDepartmentByEmployeeId(int employeeId);
	public List<Employee> getAllEmployees();
	public String getPassword();
	public double getAvailableReimbursement(int employeeId);
	public void updatePendingAmount(double pendingAmount, int employeeId);
	public void updateAvailableAmount(double availableAmount, int employeeId);
	public void updateAwardedAmount(double awardedAmount, int employeeId);
}
