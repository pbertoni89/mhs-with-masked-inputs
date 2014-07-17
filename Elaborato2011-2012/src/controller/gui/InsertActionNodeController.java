package controller.gui;


import graph.comp.ActionNode;
import graph.comp.Node;
import gui.ObservationPanel;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.InsertNodeController;
import controller.NullStaticObjectException;
import controller.validator.ParametersRequiredMismatchException;



public class InsertActionNodeController extends InsertNodeController<ActionNode>{

	
	public InsertActionNodeController(){
		super(IDCONTROLLER_INSERTACTIONNODE, ActionNode.ACTIONNODENAME);
	}

	@Override
	public ActionNode insertNewNode(String nodename) {
		return new ActionNode(nodename);
	}

	@Override
	public ValidatorMessage getErrorWarningOccured(Node n1, Node n2)throws ParametersRequiredMismatchException,	NullStaticObjectException, InstantiationException, IllegalAccessException {
		return getValidator().ValidateUserAction(warningList,new UserAction(n1,null,null,false),getApplication().getModel(),
				false,//1
				false,//2
				false,//3
				false,//4
				false,//5
				false,//6
				true,//7
				true,//8
				false,//9
				true,//10
				true,//11
				false,//12
				true//13
				);
	}

	@Override
	public void addingNodeList(ObservationPanel observationPanel,ActionNode node) {
			
	}
	
}
