package com.java.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.employeeDao;
import com.java.model.employee;




@WebServlet(name ="employeeServlet",urlPatterns = "/")
//@WebServlet("/employeeServlet")
public class employeeServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
    
	private employeeDao empDao;
	
	public void init() {
		empDao=new employeeDao();
	}
	
	
    public employeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertEmp(request, response);
				break;
			case "/delete":
				deleteEmp(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateEmp(request, response);
				break;
			default:
				listEmp(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	
	private void listEmp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<employee> listEmp = empDao.selectAllEmp();
		request.setAttribute("emp", listEmp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		employee existingEmp = empDao.selectEmp(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("emp", existingEmp);
		dispatcher.forward(request, response);

	}
	private void insertEmp(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		employee newEmp = new employee(name, department, position);
		empDao.insertEmp(newEmp);
		response.sendRedirect("list");
	}

	private void updateEmp(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String department = request.getParameter("department");

		employee emp = new employee(id, name, position, department);
		
		empDao.updateEmp(emp);
		response.sendRedirect("list");
	}
	
	
	private void deleteEmp(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		empDao.deleteEmp(id);
		response.sendRedirect("list");

	}
	}

	
	

