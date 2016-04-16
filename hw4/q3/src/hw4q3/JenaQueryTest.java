package hw4q3;

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
        pss.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
        pss.setNsPrefix("rdf", "https://www.w3.org/1999/02/22-rdf-syntax-ns");
        pss.setNsPrefix("yago", "http://dbpedia.org/class/yago/");

        pss.setCommandText("SELECT"
                + " ?name ?office ?person"
                + " WHERE {"
                + " ?person dbo:birthPlace ?city.\n"
                + " FILTER regex(?city,'"+cityName+"')\n"
                + " ?person foaf:name ?name .\n"
                + " ?person a yago:HeadOfState110164747 .\n"
                + " ?person dbo:office ?office .\n"
                + " }\n"
                + " ORDER BY ?name"
                );
        System.out.println("Query: \n" + pss.asQuery());
        QueryExecution qe = QueryExecutionFactory.sparqlService(sparqle, pss.asQuery());
        try {
            ResultSet results = qe.execSelect();
            // A simpler way of printing the results.
             ResultSetFormatter.out(System.out, results);
            // In json form printing the results.
//            ResultSetFormatter.outputAsJSON(System.out, results);
        } catch (Exception e) {
            System.out.println(e);
        }
        // / Other 9 nine methods for interesting queries about state/cities of
        // the world will go here
    }
}