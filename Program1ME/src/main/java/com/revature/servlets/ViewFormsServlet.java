package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class ViewFormsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6986855565297487616L;
	private static EmployeeService employeeService = new EmployeeServiceImpl();
	private static ReimbursementService reimbursementService = new ReimbursementServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Employee employee;
		HttpSession sess = req.getSession(false);
		if (sess == null || sess.getAttribute("employee") == null) {
			req.getRequestDispatcher("login").forward(req, resp);
			return;
		} else {
			sess = req.getSession();
			employee = (Employee) sess.getAttribute("employee");
		
		List<Reimbursement> pendingReimbursements = reimbursementService.viewAllPendingReimbursements(employee.getDepartmentId());
		
		ObjectMapper om = new ObjectMapper();
		String pendingReimbursementsString = om.writeValueAsString(pendingReimbursements);
		
		resp.getWriter().write(pendingReimbursementsString);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Employee employee;
		HttpSession sess = req.getSession(false);
		if (sess == null || sess.getAttribute("employee") == null) {
			req.getRequestDispatcher("login").forward(req, resp);
			return;
		} else {
			sess = req.getSession();
			employee = (Employee) sess.getAttribute("employee");
			
			String body = req.getReader().readLine();
			
			JsonNode parent = new ObjectMapper().readTree(body);
			
			Integer formId = parent.get("reimbursementId").asInt();
			String choice = parent.get("choice").asText();
			String moreInfo = parent.get("moreInfo").asText();
			
			reimbursementService.approveOrDenyReimbursement(formId, choice, moreInfo, employee.getDepartmentId());
		}
		
	}
}
	
