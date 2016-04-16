package hw4q3;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

/**
 *
 *
 */
public class JenaQueryTest {
    public static void main(String[] args) {
        String cityName = "Chicago";
        getPersons(cityName);
    }

    public static void getPersons(String cityName) {
        String sparqle = "http://dbpedia.org/sparql";
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
        pss.setNsPrefix("", "http://dbpedia.org/resource/");
        pss.setNsPrefix("dbo", "http://dbpedia.org/property/");
        pss.setCommandText("SELECT ?name ?birth ?death ?person WHERE { ?person dbo:birthPlace :" + cityName + ".\n" + " ?person dbo:birthDate ?birth .\n"
                + " ?person foaf:name ?name .\n" + " ?person dbo:deathDate ?death . }\n" + " ORDER BY ?name");
        System.out.println("Query: \n" + pss.asQuery());
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqle, pss.asQuery());
        try {
            ResultSet results = qe.execSelect();
            // A simpler way of printing the results.
            ResultSetFormatter.out(System.out, results);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // In json form printing the results.
            ResultSetFormatter.outputAsJSON(out, results);
        } catch (Exception jex) {
            System.out.println(jex);
        }
        // / Other 9 nine methods for interesting queries about state/cities of
        // the world will go here
    }
}