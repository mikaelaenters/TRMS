package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class ReimbursementServlet extends HttpServlet {

	private ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession sess = req.getSession(false);
		
		if (sess == null || sess.getAttribute("employee") == null) {
			resp.sendRedirect("login.html");
			return;
		}
		
		Employee employee = (Employee) sess.getAttribute("employee");
			
		if(employee.getDepartmentId() != 1) {
			resp.sendRedirect(req.getContextPath() + "/HTML/supervisorHome.html");
		} else {
			resp.sendRedirect(req.getContextPath() + "/HTML/associateHome.html");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sess = req.getSession(false);
		
		if (sess == null || sess.getAttribute("employee") == null) {
			resp.sendRedirect("login.html");
			return;
		}
		
		Employee employee = (Employee) sess.getAttribute("employee");
		
		String location = req.getParameter("location");
		String eventType = req.getParameter("eventPayback");
		String time = req.getParameter("eventTime");
		String AMPM = req.getParameter("AMPM");
		String date = req.getParameter("eventDate");
		double cost = Double.parseDouble(req.getParameter("cost"));
		String gradingFormat = req.getParameter("grading");
		String passingGrade = req.getParameter("passing");
		String description = req.getParameter("description");
		
		double percentPayback = reimbursementService.eventToPaybackPercentage(eventType);
		
		time = time + AMPM;
		
		Reimbursement newApplication = new Reimbursement(employee.getEmployeeId(), eventType, date, time, location, cost, description, percentPayback, gradingFormat, passingGrade);
		reimbursementService.createReimbursement(newApplication);
		
		
		if(employee.getDepartmentId() != 1) {
			resp.sendRedirect(req.getContextPath() + "/HTML/supervisorHome.html");
		} else {
			resp.sendRedirect(req.getContextPath() + "/HTML/associateHome.html");
		}
			
	}
}
