package com.revature.services;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.pojos.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	public Employee loginEmployee(String email, String password) {

		Employee employee = null;
		
		for (Employee check : employeeDao.getAllEmployees()) {
		
			if(check.getEmail().equals(email) && check.getPassword().equals(password)) {
				employee = check;
				break;
			}
		}
		
		if(employee != null) {
			employee = employeeDao.getEmployeeInfo(email);
		}
		
		return employee;
	}

	@Override
	public int getEmployeeDepartment(int employeeId) {
		return employeeDao.getDepartmentByEmployeeId(employeeId);
	}


}
