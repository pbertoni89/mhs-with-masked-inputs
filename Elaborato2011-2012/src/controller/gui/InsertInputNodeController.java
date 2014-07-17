package controller.gui;


import graph.comp.InputNode;
import graph.comp.Node;
import gui.ObservationPanel;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.InsertNodeController;
import controller.NullStaticObjectException;
import controller.validator.ParametersRequiredMismatchException;



public class InsertInputNodeController extends InsertNodeController<InputNode>{

	
	public InsertInputNodeController(){
		super(IDCONTROLLER_INSERTINPUTNODE, InputNode.INPUTNODENAME);
	}



	@Override
	public InputNode insertNewNode(String nodename){
		return new InputNode(nodename);
	}



	@Override
	public ValidatorMessage getErrorWarningOccured(Node n1,Node n2) throws ParametersRequiredMismatchException, NullStaticObjectException, InstantiationException, IllegalAccessException {
		
		//getApplication.getModel  NON getModel!!!!!!!
		return getValidator().ValidateUserAction(warningList,new UserAction(n1,null,null,false),getApplication().getModel(),
				false,//1
				false,//2
				false,//3
				false,//4
				false,//5
				false,//6
				true,//7
				false,//8
				false,//9
				true,//10
				false,//11
				true,//12
				true);//13
	}



	@Override
	public void addingNodeList(ObservationPanel observationPanel,InputNode node) {
		
	}
	
	
}
