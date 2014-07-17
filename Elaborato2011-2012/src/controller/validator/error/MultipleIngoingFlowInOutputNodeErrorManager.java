package controller.validator.error;

import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class MultipleIngoingFlowInOutputNodeErrorManager extends ValidatorErrorManager<MultipleIngoingFlowInOutputNodeException>{
	
	public static final int IDERROR=4;

	public MultipleIngoingFlowInOutputNodeErrorManager() {
		super(IDERROR,MultipleIngoingFlowInOutputNodeException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==OutputNode.class){
			if (_currentgraph.inDegreeOf(_source)>1){
				throw new MultipleIngoingFlowInOutputNodeException();
			}
		}
	}

}
