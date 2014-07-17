package controller.validator.error;

import java.util.NoSuchElementException;

import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.DirectedNeighborIndex;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;

public class PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager extends ValidatorErrorManager<PredecessorOfOutputNodeHasMultipleSuccessorsException>{

	public static final int IDERROR=2;
	
	private DirectedNeighborIndex<Node,Flow> util;
	
	public PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager() {
		super(IDERROR,PredecessorOfOutputNodeHasMultipleSuccessorsException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==OutputNode.class){
			this.util=new DirectedNeighborIndex<Node,Flow>(_currentgraph);
			try{
				if (_currentgraph.outDegreeOf((this.util.predecessorsOf(_source).iterator().next()))>1){
					throw new PredecessorOfOutputNodeHasMultipleSuccessorsException();
				}
			}catch(NoSuchElementException error){
				/*causato quando io effettuo questo controllo su un grafo affetto dal warning che l'outputnode non ha collegamenti
				 *con un precedente ActionNode. In questo caso ho deciso che questo errore non va
				 *lanciato. */
				return;
			}
		}
	}

}
