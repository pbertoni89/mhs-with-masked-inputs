package controller.gui;

import graph.comp.Node;
import graph.comp.OutputNode;
import gui.ObservationPanel;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.InsertNodeController;
import controller.NullStaticObjectException;
import controller.validator.ParametersRequiredMismatchException;



public class InsertOutputNodeController extends InsertNodeController<OutputNode>{
	
	public InsertOutputNodeController(){
		super(IDCONTROLLER_INSERTOUTPUTNODE, OutputNode.OUTPUTNODENAME);
	}
	

	@Override
	public OutputNode insertNewNode(String nodename) {
		return new OutputNode(nodename); 
	}

	
	//non posso scrivere (OutputNode n1, OutPutNode n2)
	@Override
	public ValidatorMessage getErrorWarningOccured(Node n1, Node n2)throws ParametersRequiredMismatchException,	NullStaticObjectException, InstantiationException, IllegalAccessException {
				return getValidator().ValidateUserAction(warningList,new UserAction(n1,null,null,false), getApplication().getModel(),
				false,//1
				false,//2
				false,//3
				false,//4
				false,//5
				false,//6
				true,//7
				false,//8
				true,//9
				true,//10
				false,//11
				false,//12
				true);//13
	}

	@Override
	public void addingNodeList(ObservationPanel observationPanel,OutputNode node) {
		observationPanel.addResultRenderer(node);
		observationPanel.validate();
	}
}
