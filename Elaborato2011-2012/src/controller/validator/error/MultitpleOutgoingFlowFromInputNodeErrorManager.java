package controller.validator.error;

import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class MultitpleOutgoingFlowFromInputNodeErrorManager extends ValidatorErrorManager<MultitpleOutgoingFlowFromInputNodeException>{

	public static final int IDERROR=5;
	
	public MultitpleOutgoingFlowFromInputNodeErrorManager() {
		super(IDERROR,MultitpleOutgoingFlowFromInputNodeException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==InputNode.class){
			if (_currentgraph.outDegreeOf(_source)>1){
				throw new MultitpleOutgoingFlowFromInputNodeException(); 
			}
		}
	}

}
