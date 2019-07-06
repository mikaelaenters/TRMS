package com.revature.services;

import com.revature.pojos.Employee;

public interface EmployeeService {
	
	public Employee loginEmployee(String email, String password);
	public int getEmployeeDepartment(int employeeId);
}
