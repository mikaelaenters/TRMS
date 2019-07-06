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
import com.revature.pojos.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
	private static Connection conn = ConnectionFactory.getConnection();
	
	@Override
	public void createReimbursement(Reimbursement application) {
	
		String sql = "insert into reimbursement_application(employee_id, event_type, event_date, event_time, event_location,"
		+ "event_cost, description, percentage_payback, grading_format, passing_grade) values (?, ?, to_date(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, application.getEmployeeId());
			pstmt.setString(2, application.getEventType());
			pstmt.setString(3, application.getEventDate());
			pstmt.setString(4, application.getEventTime());
			pstmt.setString(5, application.getLocation());
			pstmt.setDouble(6, application.getCost());
			pstmt.setString(7, application.getDescription());
			pstmt.setDouble(8, application.getPercentagePayback());
			pstmt.setString(9, application.getGradingFormat());
			pstmt.setString(10, application.getPassingGrade());
			
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
	public Reimbursement getApplicationById(int reimbursementId) {
		Reimbursement newReimbursement = null;
		
		String sql = "select r.reimbursement_id, e.email, e.fullname, r.event_type, r.event_date, r.event_cost, " + 
				"r.description, e.available_reimbursement, e.pending_reimbursement, e.awarded_reimbursement from " + 
				"reimbursement_application r inner join employee e on r.employee_id = e.employee_id where r.reimbursement_id = " + reimbursementId;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) { 
				newReimbursement = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newReimbursement;	
		
		
	}

	@Override
	public List<Reimbursement> getAllPendingApplications(int status) {
		
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		String sql = "select r.reimbursement_id, e.email, e.fullname, r.event_type, r.event_date, r.event_cost, " + 
				"r.description, e.available_reimbursement, e.pending_reimbursement, e.awarded_reimbursement from " + 
				"reimbursement_application r inner join employee e on r.employee_id = e.employee_id where status = " + status;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) { //Loops through every row 
				pendingReimbursements.add(new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pendingReimbursements;	
	}

	@Override
	public List<Reimbursement> getEmployeeApplications(int employeeId) {
		List<Reimbursement> employeeReimbursements = new ArrayList<Reimbursement>();
		String sql = "select r.reimbursement_id, e.fullname, r.event_type, r.event_date, r.event_cost, " + 
				"r.description, r.passing_grade, r.completed_grade, e.available_reimbursement, e.pending_reimbursement, e.awarded_reimbursement from " + 
				"reimbursement_application r inner join employee e on r.employee_id = e.employee_id where e.employee_id = " + employeeId;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) { //Loops through every row 
				employeeReimbursements.add(new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeReimbursements;
	}


	@Override
	public void updateApplicationInformation(int formId, String moreInfo, int status) {
		
		String sql = "update reimbursement_application set status = ?, additional_information = ? where reimbursement_id = ?";
			
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, status);
			pstmt.setString(2, moreInfo);
			pstmt.setInt(3, formId);
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
	public void updateFinalGradeInformation(int formId, String finalGrade) {
		String sql = "update reimbursement_application set status = ?, completed_grade = ? where reimbursement_id = ?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, 5);
			pstmt.setString(2, finalGrade);
			pstmt.setInt(3, formId);
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
	public List<Reimbursement> getFinalGrades() {
		List<Reimbursement> finalGradesList = new ArrayList<Reimbursement>();
		String sql = "select r.reimbursement_id, e.fullname, r.event_type, r.event_date, r.event_cost, " + 
				"r.description, r.passing_grade, r.completed_grade, e.available_reimbursement, e.pending_reimbursement, e.awarded_reimbursement from " + 
				"reimbursement_application r inner join employee e on r.employee_id = e.employee_id where status = 5";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) { //Loops through every row 
				finalGradesList.add(new Reimbursement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getDouble(10), rs.getDouble(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalGradesList;
	}


	@Override
	public void updateFinalGradeApproveOrDeny(int formId, int status) {
		String sql = "update reimbursement_application set status = ? where reimbursement_id = ?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, status);
			pstmt.setInt(2, formId);
			
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
