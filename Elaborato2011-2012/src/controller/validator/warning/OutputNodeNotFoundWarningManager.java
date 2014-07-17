package controller.validator.warning;

import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorException;
import controller.validator.ValidatorStateErrorManager;

public class OutputNodeNotFoundWarningManager extends ValidatorStateErrorManager<OutputNodeNotFoundException>{

	public static final int IDWARNING=12;
	
	public OutputNodeNotFoundWarningManager() {
		super(IDWARNING,OutputNodeNotFoundException.class);
		this.state=0;
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (this.state==1){//stato 1: l'outputnode è già stato trovato
			if (_islastnode){
				this.state=0;
			}
			return;
		}
		if (_source.getClass()==OutputNode.class){
			if (!_islastnode){//se è l'ultimo nodo lo stato non ha importanza perché dovrebbe essere settato a 1 e subito dopo essere settato a 0
				this.state=1;
			}
			return;
		}
		if (_islastnode){
			throw new OutputNodeNotFoundException();
		}
	}

}