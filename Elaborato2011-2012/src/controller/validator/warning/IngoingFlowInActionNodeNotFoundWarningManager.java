package controller.validator.warning;

import graph.comp.ActionNode;
import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class IngoingFlowInActionNodeNotFoundWarningManager extends ValidatorErrorManager<IngoingFlowInActionNodeNotFoundException>{

	public static final int IDWARNING=7;
	
	public IngoingFlowInActionNodeNotFoundWarningManager() {
		super(IDWARNING,IngoingFlowInActionNodeNotFoundException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==ActionNode.class){
			if (_currentgraph.inDegreeOf(_source)<1){
				throw new IngoingFlowInActionNodeNotFoundException();
			}
		}
	}

}