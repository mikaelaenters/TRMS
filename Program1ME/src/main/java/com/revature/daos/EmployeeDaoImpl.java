package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.connections.ConnectionFactory;
import com.revature.pojos.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private static Connection conn = ConnectionFactory.getConnection();
	
	public int getEmployeeIdByEmail(String email) {
		int key = -1;

		String sql = "select employee_id from employee where email = '" + email +"'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				key = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return key;
	}

	public int getDepartmentByEmployeeId(int employeeId) {
		int department = -1;

		String sql = "select department_id from employee where employee_id = " + employeeId;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				department = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return department;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		
		String sql = "select * from employee";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) { //Loops through every row 
				allEmployees.add(new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8))); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allEmployees;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAvailableReimbursement(int employeeId) {
		double amount = 0;
		
		String sql = "select available_reimbursement from employee where employee_id = " + employeeId;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				amount = rs.getDouble(1);
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return amount;
	}

	@Override
	public Employee getEmployeeInfo(String email) {
		Employee employee = null;
		
		String sql = "select * from employee where email = '" + email +"'";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8)); 
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;			
	}
	@Override
	public void updatePendingAmount(double pendingAmount, int employeeId) {
		String sql = "update employee set pending_reimbursement = ? where employee_id = ?";
		
	try {
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, pendingAmount);
		pstmt.setInt(2, employeeId);
		
		conn.setAutoCommit(false);  //needs to be done to run transactions
		
		pstmt.executeUpdate();

		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	}
	
	@Override
	public void updateAvailableAmount(double availableAmount, int employeeId) {
		String sql = "update employee set available_reimbursement = ? where employee_id = ?";
		
	try {
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, availableAmount);
		pstmt.setInt(2, employeeId);
		
		conn.setAutoCommit(false);  //needs to be done to run transactions
		Savepoint sp = conn.setSavepoint("Before Insert");
		
		pstmt.executeUpdate();

		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
		
	}
	
	@Override
	public void updateAwardedAmount(double awardedAmount, int employeeId) {
		String sql = "update employee set awarded_reimbursement = ? where employee_id = ?";
		
	try {
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setDouble(1, awardedAmount);
		pstmt.setInt(2, employeeId);
		
		conn.setAutoCommit(false);  //needs to be done to run transactions
		Savepoint sp = conn.setSavepoint("Before Insert");
		
		pstmt.executeUpdate();

		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException e) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
	}
	}

}
