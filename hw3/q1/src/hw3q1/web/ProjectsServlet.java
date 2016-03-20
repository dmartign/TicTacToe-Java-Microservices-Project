package hw3q1.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw3q1.model.domain.Project;
import hw3q1.model.domain.dao.ProjectDAO;



public class ProjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Project> projList = ((ProjectDAO) getServletContext().getAttribute("projectdao")).getProjectList();
		request.setAttribute("projList", projList);
		RequestDispatcher disp = request.getRequestDispatcher("projects.jsp");
		disp.forward(request, response);
		
	}

	
}
