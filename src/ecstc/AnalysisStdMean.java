package ecstc;

import edu.uci.ics.jung.io.*;
import edu.uci.ics.jung.graph.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A class to collect measurements of the graph's vertices and
 * aggregate statistics about the vertices of the completions
 * 
 * @version 	$$
 * @author 	Bilal Khan
 */
public class AnalysisStdMean extends Analysis {

    private static Analysis _instance = null;
    public static Analysis instance() {
	if (_instance==null) {
	    _instance=new AnalysisStdMean();
	}
	return _instance;
    }
    private AnalysisStdMean() {}

    String getName() { return "STD_MEAN"; }

    double compute(Graph g, StatsTrue st, StatsECSTC se) {

        Set vset = g.getVertices();

	double slope = AnalysisSlope.instance().compute(g, st, se);

	double max=Double.MIN_VALUE;
        for (Iterator vit= vset.iterator(); vit.hasNext(); ) {
            Vertex vquery_g = (Vertex)vit.next();
	    try {
		double std=se.getStdMean(vquery_g);
		if (std>max) max=std;
            }
            catch(Exception ex) {
            }
        }

        return max;
    }
}
