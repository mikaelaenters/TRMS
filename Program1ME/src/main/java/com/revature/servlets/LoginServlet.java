package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;

public class LoginServlet extends HttpServlet {

	private static EmployeeService employeeService = new EmployeeServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Employee employee = employeeService.loginEmployee(username, password);
		
		if (employee == null) {
			
			resp.setStatus(401);  
			resp.getWriter().write("Failed Login");
			resp.sendRedirect("login.html");

		} else {
		
			HttpSession sess = req.getSession(true);
			
			sess.setAttribute("employee", employee);
			
			if(employee.getDepartmentId() != 1) {
				resp.sendRedirect(req.getContextPath() + "/HTML/supervisorHome.html");
			} else {
				resp.sendRedirect(req.getContextPath() + "/HTML/associateHome.html");
			}
	}
}
}
