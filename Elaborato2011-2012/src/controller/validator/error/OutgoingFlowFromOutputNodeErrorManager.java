package controller.validator.error;


import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;



public class OutgoingFlowFromOutputNodeErrorManager extends ValidatorErrorManager<OutgoingFlowFromOutputNodeException>{
	
	public static final int IDERROR=1;

	public OutgoingFlowFromOutputNodeErrorManager(){
		super(IDERROR,OutgoingFlowFromOutputNodeException.class);
	}
	
	/**_source è il nodo di uscita!
	 * 
	 */
	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==OutputNode.class){
			if (_currentgraph.outDegreeOf(_source)>0){
				throw new OutgoingFlowFromOutputNodeException();
			}
		}
	}

}
