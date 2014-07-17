package alg;

import graph.Model;
import graph.comp.Flow;
import graph.comp.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.alg.DirectedNeighborIndex;

/**
 * Un'entit� in grado di percorrere un grafo diretto.
 * @author   patrizio
 */
public class GraphWalker {

	/**
	 * @uml.property  name="index"
	 */
	private DirectedNeighborIndex<Node,Flow> index;
	/**
	 * @uml.property  name="setPredecessors"
	 */
	private Set<Node> setPredecessors;
	
	public GraphWalker(Model model) {
		index = new DirectedNeighborIndex<Node,Flow>(model);
	}
	
	public Set<Node> getPredecessors(Node node) {
		 setPredecessors = new HashSet<Node>();
		 recursiveGetPredecessors(node);
		 return setPredecessors;
	}
	
	public void recursiveGetPredecessors(Node node) {
		Set<Node> temp = index.predecessorsOf(node);	
		Iterator<Node> it = temp.iterator();  //FIXME capire come faccia a non ripartire da zero, data la ricorsione.
											  // questo metodo � da sperimentare con altri modelli!!!!!!!!!!!!!!!!!!!
		while ( it.hasNext() ) {
			Node nodeTemp = it.next();
			setPredecessors.add(nodeTemp);
			recursiveGetPredecessors(nodeTemp);
		}
	}
	
	public Set<Node> getSuccessors(Node node) {
		return index.successorsOf(node);
	}
}