package controller.validator.warning;

import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;

import controller.validator.ValidatorException;
import controller.validator.ValidatorStateErrorManager;

public class InputNodeNotFoundWarningManager extends ValidatorStateErrorManager<InputNodeNotFoundException>{

	public static final int IDWARNING=9;
	
	public InputNodeNotFoundWarningManager() {
		super(IDWARNING,InputNodeNotFoundException.class);
	}

	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		if (this.state==1){//state 1: è già stato trovato un inputnode
			if (_islastnode){
				this.state=0;
			}
			return;
		}
		if (_source.getClass()==InputNode.class){
			if (!_islastnode){
				this.state=1;
			}
			return;
		}
		if (_islastnode){
			this.state=0;
			throw new InputNodeNotFoundException();
		}
	}

}