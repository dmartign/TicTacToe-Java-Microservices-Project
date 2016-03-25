package hw3q1.model.domain.dao;

import hw3q1.model.domain.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public List<Project> projectList;

    public ProjectDAO() {

        this.projectList = new ArrayList<Project>();
        this.createProject(
                "Daniela, Jennifer, Peter",
                "Semantic Web",
                "In this paper we will summarize three current, 2010+, research papers on how ontologies can be used to navigate and combine social networks to facilitate decisions on which meaning or instance of a concept a user wants to see. A common problem amongst this domain is linking concepts between ontologies if those ontologies use different semantics for the same concept or the same semantics for different concepts, techniques are evolving to solve this problem. We will also cover a summary of various ontology description languages and how they interact in semantic web for various uses. These techniques and ontology languages can be used when querying social networks to find relations to narrow the search down to those that are relevant to the current user. ");
        this.createProject("Daniela, Jennifer, Peter", "CSE551 Homework 3",
                "This assignment covering the usage of servlets and a simple web application with some user niceties.");
        this.createProject(
                "Mohammed Alquwaisem, Peter Miele",
                "Comparing Open Source Static Security Analysis Tools Using Open Source Software",
                "Vulnerabilities in software provide significant risk to the operation of user's of software. Software has been developed over time to assist in identification and rectification of such vulnerabilities in software by doing static analysis of the source code of software. We present a comparison of a set of such of static analysis tools. The results will aid software developers in determining which tools will best suit their needs in detecting software vulnerabilities.");
        this.createProject(
                "Daniela Martignani, Aarathi Shibu",
                "Situation-Driven Software Development: An approach using a test case and testing techniques",
                "Situation-driven software development is an alternative approach that suggests taking into account the external factors of a system and their influence in the main system behavior. Traditional software development methodologies mainly focus on the internal factors of a system and consider them to be the only ones that can have an impact on such a system. In a sense, the main motivation of this paper is to present some work done in the area of situation-driven development and enhance the key points of situation and scenarios, applying them through a case study to show how they can be useful in incorporating external factors into the main system requirements. The case study presented goes through the development of use cases based on specific attributes from the users of the system, the creation of an activity diagram based on the use cases’ scenarios, and the use of the activity diagram to verify our approach through a graph data structure based on the activity diagram to perform appropriate testing. A new approach based on graph coverage testing is introduced, named Situation Coverage Testing, which follows similar principles from graph coverage but applies them to situation contexts. The testing results show that the approach can prove to be a promising way to properly incorporate both internal and external factors into the development of a more accurate software system.");
    }

    private void createProject(String studentName, String projectName, String abstractText) {
        Project project = new Project(studentName, projectName, abstractText);
        this.projectList.add(project);
    }

    public List<Project> getProjectList() {
        return this.projectList;
    }

}
