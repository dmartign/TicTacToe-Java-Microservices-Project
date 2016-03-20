package hw3q1.model.domain;


public class Project {
	private String studentName;
	private String projectName;
	private String abstractText;
	
	public Project(String studentName,String projectName, String abstractText)
	{
		this.studentName = studentName;
		this.projectName = projectName;
		this.abstractText = abstractText;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	
	
}