package hw3q1.model.domain.dao;

import java.util.ArrayList;
import java.util.List;

import hw3q1.model.domain.Project;

public class ProjectDAO {
		
		public List<Project> projectList;
		
		
		
	
		public ProjectDAO()
		{
			
			projectList = new ArrayList<Project>();
			Project proj1 = new Project("Peter","ResearchProj1", "This paper focuses on some really cool stuff!");
			Project proj2 = new Project("Daniela", "ResearchProj2","This paper has more cool stuff!");
			Project proj3 = new Project("Jennifer","ResearchProj3","This paper is just okay.");
			projectList.add(proj1);
			projectList.add(proj2);
			projectList.add(proj3);
		}
		
		
	
		public List<Project> getProjectList() {
			return projectList;
		}



	

	

}
