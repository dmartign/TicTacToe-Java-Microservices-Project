package hw4q3.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class AirportsServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("city");
        String sparqle = "http://dbpedia.org/sparql";
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        pss.setNsPrefix("dbr", "http://dbpedia.org/resource/");
        pss.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        pss.setNsPrefix("rdf", "https://www.w3.org/1999/02/22-rdf-syntax-ns");
        pss.setNsPrefix("yago", "http://dbpedia.org/class/yago/");
        pss.setNsPrefix("dbp", "http://dbpedia.org/property/");
        
        pss.setCommandText("SELECT ?name "
        		+ " WHERE { "
        		+ " ?airport dbp:name ?name."
        		+ " ?airport dbp:cityServed dbr:"+cityName+"."
        		+ " }"
                );
        
        
        
        
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqle, pss.asQuery());
        try {
            ResultSet results = qe.execSelect();
            // A simpler way of printing the results.
//             ResultSetFormatter.out(System.out, results);
//             In json form printing the results.
            ResultSetFormatter.outputAsJSON(resp.getOutputStream(), results);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

}
