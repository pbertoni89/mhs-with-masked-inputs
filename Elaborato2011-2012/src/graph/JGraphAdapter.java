package graph;

import graph.comp.Flow;
import graph.comp.Node;

import java.io.Serializable;
import org.jgrapht.ext.JGraphModelAdapter;

public class JGraphAdapter extends JGraphModelAdapter<Node,Flow> implements Serializable {

	private static final long serialVersionUID = 1L;

	public JGraphAdapter(Model g) {
		super(g);
		
	}
}
