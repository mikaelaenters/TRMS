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
import com.revature.services.ReimbursementService;
import com.revature.services.ReimbursementServiceImpl;

public class FinalGradeServlet extends HttpServlet {
	
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
			
			if(employee.getDepartmentId() != 4) {     //BenCo can approve final grades
				resp.sendRedirect(req.getContextPath() + "/HTML/supervisorHome.html");
			}else {
				
				List<Reimbursement> finalGradesList = reimbursementService.viewFinalGrades();
				
				ObjectMapper om = new ObjectMapper();
				String finalGradesListString = om.writeValueAsString(finalGradesList);
				
				resp.getWriter().write(finalGradesListString);
			}
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
			
			if(employee.getDepartmentId() == 4) {     //BenCo can approve final grades
				
				
				String body = req.getReader().readLine();
				
				JsonNode parent = new ObjectMapper().readTree(body);
				
				Integer formId = parent.get("reimbursementId").asInt();
				String choice = parent.get("choice").asText();
				
				reimbursementService.approveOrDenyFinalGrade(formId, choice);
			} else {
				req.getRequestDispatcher("supervisorHome").forward(req, resp);
				return;
			}
			
		}
		
	}

}
