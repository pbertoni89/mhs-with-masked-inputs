package controller.validator.error;

import java.util.Iterator;

import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.DirectedNeighborIndex;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

/**
 * 
 * @author Koldar
 *
 */
public class InputNodeLeadsToDirectOutputNodeErrorManager extends ValidatorErrorManager<InputNodeLeadsToDirectOutputNodeException>{

	public static final int IDERROR=5;
	
	private DirectedNeighborIndex<Node,Flow> util;
	
	public InputNodeLeadsToDirectOutputNodeErrorManager() {
		super(IDERROR,InputNodeLeadsToDirectOutputNodeException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==InputNode.class){
			this.util=new DirectedNeighborIndex<Node,Flow>(_currentgraph);
			Iterator<Node> iterator=this.util.successorsOf(_source).iterator();
			while (iterator.hasNext()){
				if (iterator.next().getClass()==OutputNode.class){
					throw new InputNodeLeadsToDirectOutputNodeException();
				}
			}
		}
		
	}

}
