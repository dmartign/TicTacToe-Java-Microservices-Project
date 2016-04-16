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

public class AbstractServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String cityName = req.getParameter("city");
    String sparqle = "http://dbpedia.org/sparql";
    ParameterizedSparqlString pss = new ParameterizedSparqlString();
    pss.setNsPrefix("dbr", "http://dbpedia.org/resource/");
    pss.setNsPrefix("dbo", "http://dbpedia.org/ontology/");

    pss.setCommandText("SELECT  ?abstract"
        +" WHERE"
        +" { dbr:"+cityName+" dbo:abstract ?abstract ."
        +"         FILTER(langMatches(lang(?abstract), \"EN\"))"
        +" }"
        +" LIMIT 1"
            );
    QueryExecution qe = QueryExecutionFactory.sparqlService(sparqle, pss.asQuery());
    try {
        ResultSet results = qe.execSelect();
        // A simpler way of printing the results.
//         ResultSetFormatter.out(System.out, results);
//         In json form printing the results.
        ResultSetFormatter.outputAsJSON(resp.getOutputStream(), results);
    } catch (Exception e) {
        System.out.println(e);
    }
    // / Other 9 nine methods for interesting queries about state/cities of
    // the world will go here
}
}
