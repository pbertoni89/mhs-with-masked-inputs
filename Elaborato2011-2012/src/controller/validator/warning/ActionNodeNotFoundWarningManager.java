package controller.validator.warning;


import graph.comp.ActionNode;
import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;
import controller.validator.ValidatorException;
import controller.validator.ValidatorStateErrorManager;

public class ActionNodeNotFoundWarningManager extends ValidatorStateErrorManager<ActionNodeNotFoundException>{

	public static final int IDWARNING=6;
	
	public ActionNodeNotFoundWarningManager() {
		super(IDWARNING,ActionNodeNotFoundException.class);
	}

	
	@Override
	public void specificHandler(Node _source,DirectedGraph<Node, Flow> _currentgraph,boolean _islastnode) throws ValidatorException {
		
		if (this.state==1){//stato 1: è già stato trovato un actionnode. il controllo è terminato
			if (_islastnode){
				this.state=0;
			}
			return;
		}
		if (_source.getClass()==ActionNode.class){
			if (!_islastnode){
				this.state=1;
			}
			return;
		}
		if (_islastnode){
			throw new ActionNodeNotFoundException();
		}
		
	}

}
