package controller.validator.error;


import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorErrorManager;
import controller.validator.ValidatorException;


/**rappresenta la classe che tenta di verificare e di gestire l'eventualità
 * che l'utente inserisce un flusso entrante in un nodo di ingresso: tale evento
 * rientra negli errori e va pertanto eliminato. L'errore si verifica, per esempio,
 * in una situazione del genere:
 * <p> <img src="IngoingFlowInInputNodeError.png" alt="image not found"/>
 * 
 * <p> questo controllo va eseguito quando:
 * <ul>
 *  <li>si crea un nuovo flusso: esso può avere come nodo finale un nodo di input</li>
 * </ul>
 * @author Koldar
 * @version 1.1
 *
 */
public class IngoingFlowInInputNodeErrorManager extends ValidatorErrorManager<IngoingFlowInInputNodeException>{
	
	public static final int IDERROR=0;

	public IngoingFlowInInputNodeErrorManager() {
		super(IDERROR,IngoingFlowInInputNodeException.class);
	}

	@Override
	public void specificHandler(Node _source, DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (_source.getClass()==InputNode.class){
			if (_currentgraph.inDegreeOf(_source)>0){
				throw new IngoingFlowInInputNodeException();
			}
		}
	}

	

}
