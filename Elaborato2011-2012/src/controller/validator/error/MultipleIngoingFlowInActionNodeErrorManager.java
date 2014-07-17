package controller.validator.error;

import graph.comp.ActionNode;
import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class MultipleIngoingFlowInActionNodeErrorManager extends ValidatorErrorManager<MultipleIngoingFlowInActionNodeException>{

	public static final int IDERROR=5;
	
	public MultipleIngoingFlowInActionNodeErrorManager() {
		super(IDERROR,MultipleIngoingFlowInActionNodeException.class);
	}

	/**si crea per il flusso
	 * 
	 */
	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==ActionNode.class){
			if (_currentgraph.inDegreeOf(_source)>1){
				throw new MultipleIngoingFlowInActionNodeException();
			}
		}
	}

}
