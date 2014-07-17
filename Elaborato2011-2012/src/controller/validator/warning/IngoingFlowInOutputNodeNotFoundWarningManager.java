package controller.validator.warning;

import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class IngoingFlowInOutputNodeNotFoundWarningManager extends ValidatorErrorManager<IngoingFlowInOutputNodeNotFoundException>{

	public static final int IDWARNING=8;
	
	public IngoingFlowInOutputNodeNotFoundWarningManager() {
		super(IDWARNING,IngoingFlowInOutputNodeNotFoundException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==OutputNode.class){
			if (_currentgraph.inDegreeOf(_source)<1){
				throw new IngoingFlowInOutputNodeNotFoundException();
			}
		}
	}

}