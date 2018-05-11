package ecstc;

import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.impl.*;
import edu.uci.ics.jung.algorithms.importance.*;
import edu.uci.ics.jung.algorithms.cluster.*;
import edu.uci.ics.jung.graph.decorators.*;
import edu.uci.ics.jung.algorithms.metrics.*;
import java.util.*;

/**
 * A class representing measuring effective size
 * 
 * @version 	$$
 * @author 	Bilal Khan
 */
public class MeasurementCON extends Measurement {

    public static class MeasurementCON_Factory extends Measurement.Factory {
	private MeasurementCON_Factory() {
	    super.register();
	}

	public Measurement newMeasurement(Graph g, Stats s, VertexMapper vm) { 
	    return new MeasurementCON(g,s,vm); 
	}

	public String getName() { return "CON"; }
    }

    public static Measurement.Factory _fact = null;
    public static Measurement.Factory getFactory() {
	if (_fact == null) {
	    _fact = new MeasurementCON_Factory();
	}
	return _fact;
    }

    private StructuralHoles _sh;

    MeasurementCON(Graph g, Stats s, VertexMapper vm) {
	super(g,s, vm);
    }
	
    void initialize(Graph g) {
	_sh = new StructuralHoles(new ConstantEdgeValue(1.0));
    }

    public double readValue(Vertex vquery) {        
	return _sh.constraint(vquery);
    }

    public String getName() {
	return getFactory().getName();
    }
};

