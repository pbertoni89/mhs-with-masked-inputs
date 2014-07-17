package gui;

import graph.comp.ActionNode;
import graph.comp.InputNode;
import graph.comp.OutputNode;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

/**
 * EdditModelButtonPanel � un JPanel in cui sono contenuti i bottoni attraverso il quale l'utente pu�  eseguire i comandi a sua disposizione
 * @author   michael
 */
public class EditModelButtonPanel extends GraphicNoStackComponent{

	private static final long serialVersionUID = -6732035256070954549L;
	
	/**
	 * JButton utilizzato dall'utente per inserire un InputNode
	 * @uml.property  name="insertInputNode"
	 */
	private JButton insertInputNode;
	/**
	 * JButton utilizzato dall'utente per inserire un ActionNode
	 * @uml.property  name="insertActionNode"
	 */
	private JButton insertActionNode;
	/**
	 * JButton utilizzato dall'utente per inserire un OutputNode
	 * @uml.property  name="insertOutputNode"
	 */
	private JButton insertOutputNode;
	/**
	 * JButton utilizzato dall'utente per inserire un flusso
	 * @uml.property  name="insertFlow"
	 */
	private JButton insertFlow;
	/**
	 * JButton utilizzato dall'utente per cancellare un nodo
	 * @uml.property  name="deleteNode"
	 */
	private JButton deleteNode;
	/**
	 * Stack all'interno del quale vengono aggiunti i pulsanti
	 * @uml.property  name="stack"
	 * @uml.associationEnd  
	 */
	private GraphicStackComponent stack;

	

	public EditModelButtonPanel(){
		//inizializzazione dei bottoni che sarano poi aggiunti
		insertInputNode=new JButton("Insert input node");
		insertActionNode=new JButton("Insert action node");
		insertOutputNode=new JButton("Insert output node");
		insertFlow=new JButton("insert flow");
		deleteNode=new JButton("Delete node");
		//inizializzazione dello stack
		stack= new GraphicStackComponent();
		
		//setto il formato dei bottoni	
		stack.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
		
		//INSERISCI INPUT NODE
		stack.addComponentInStack(insertInputNode, InputNode.DEFAULTINPUTCOLOR);
		//INSERISCI ACTION NODE;
		stack.addComponentInStack(insertActionNode ,  ActionNode.DEFAULTACTIONCOLOR);
		//INSERISCI OUTPUTNODE
		stack.addComponentInStack(insertOutputNode, OutputNode.DEFAULTOUTPUTCOLOR);
		//INSERISCI FLUSSO;
		stack.addComponentInStack(insertFlow , Color.BLACK);
		//ELIMINA UN NODO
		stack.addComponentInStack(deleteNode,  Color.BLACK);
		
		
		
		//genero un Jpanel che contiene i pulsanti, in caso un giorno volessimo aggiungere altro
		this.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
		this.layout.setConstraints(stack, stack.lim);
			
	   
		//aggiungo il contenitore al panel
		this.add(stack);
		this.setVisible(true);
	}
	

	/**Setta il controller per il JButton
	 * 
	 * @param button
	 * @param _personalcontroller 
	 */
	public void addlistener(JButton button , Controller _personalcontroller)
	{
	button.addActionListener((ActionListener) _personalcontroller);
		
	}
	


	/**imposta la selezionabilita' di tutti i pulsanti nel pannello. il metodo richiama
	 * il metodo <tt>setSelected(boolean)</tt> per ogni pulsante presente nel pannello
	 * 
	 * @param selected TRUE se i Button devono essere selezionabili, FALSE altrimenti 
	 */
	public void setButtonsEnabled(boolean selected){
		this.insertInputNode.setEnabled(selected);
		this.insertActionNode.setEnabled(selected);
		this.insertOutputNode.setEnabled(selected);
		this.insertFlow.setEnabled(selected);
		this.deleteNode.setEnabled(selected);
	}

	/**
	 * @return   the insertInputNode
	 * @uml.property  name="insertInputNode"
	 */
	public JButton getInsertInputNode() {
		return insertInputNode;
	}


	/**
	 * @return   the insertActionNode
	 * @uml.property  name="insertActionNode"
	 */
	public JButton getInsertActionNode() {
		return insertActionNode;
	}


	/**
	 * @return   the insertOutputNode
	 * @uml.property  name="insertOutputNode"
	 */
	public JButton getInsertOutputNode() {
		return insertOutputNode;
	}


	/**
	 * @return   the insertFlow
	 * @uml.property  name="insertFlow"
	 */
	public JButton getInsertFlow() {
		return insertFlow;
	}


	/**
	 * @return   the deleteNode
	 * @uml.property  name="deleteNode"
	 */
	public JButton getDeleteNode() {
		return deleteNode;
	}
	
	

	 
	/**
	 * @return   the stack
	 * @uml.property  name="stack"
	 */
	public JPanel getStack() {
		return stack;
	}




}
