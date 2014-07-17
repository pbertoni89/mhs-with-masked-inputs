package controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import org.jgraph.graph.DefaultGraphCell;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.Controller;
import controller.VisualGraphInteractorController;

import controller.NullStaticObjectException;
import controller.validator.ParametersRequiredMismatchException;
import event.numberevent.NumberEvent;
import graph.comp.Node;


/**rappresenta la classe che controlla le azioni da eseguire quando l'utente richiede l'inserimento
 * di un nuovo flusso.
 * 
 * @author Koldar
 * @version 1.4
 * @see Controller
 * @see graph.comp.Flow Flow
 */
public class InsertFlowController extends VisualGraphInteractorController{
	
	/**
	 * indica il primo nodo scelto dall'utente per la creazione del flusso
	 * @uml.property  name="startNode"
	 * @uml.associationEnd  
	 */
	private Node startNode;
	/**
	 * indica il secondo nodo scelto dall'utente per la creazione del flusso
	 * @uml.property  name="endNode"
	 * @uml.associationEnd  
	 */
	private Node endNode;

	/**costruisce un nuovo controller. al momento tale costruttore non
	 * fa perfettamente nulla
	 * 
	 */
	public InsertFlowController(){
		super(IDCONTROLLER_INSERTFLOW);
		this.resetFlow();
	}
	
	private void resetFlow(){
		this.startNode=null;
		this.endNode=null;
	}
	
	


	@Override
	public void executeValidatedAction() {
		try {
			getModel().addEdge(startNode, endNode);
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		log.reprintln("clicca il primo nodo");
		this.addVisualGraphListener();
	}
	/**
	 * 
	 * @return TRUE se il modello comprende almeno 2 nodi, FALSE altrimenti
	 * @throws NullStaticObjectException in caso in cui il modello no nsia stato ancora cablato con il Controller
	 */
	private boolean isNodeSetBigEnough() throws NullStaticObjectException{
		return (getModel().vertexSet().size()>=2);
	}
	
	@Override
	public void idleStateAction(NumberEvent<Integer> e){
		try {
			this.listenedButton.setEnabled(isNodeSetBigEnough());
		} catch (NullStaticObjectException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**azioni da eseguire in caso in cui il modello <strong>grafico</strong> del grafo
	 * emana un MouseEvent sinistro. Il Controller e' progettato in modo che:
	 * <ul>
	 *  <li>l'utente selezioni i nodi interessati con il click sinistro</li>
	 *  <li>ogni altro pulsante del mouse annulli l'inserimento del flusso</li>
	 *  <li>l'utente selezioni il nodo di inizio del flusso</li>
	 *  <li>con un secondo click l'utente selezioni il nodo di fune di flusso</li>
	 * </ul>
	 */
	@Override
	public void leftClickOperations(MouseEvent arg0) throws NullStaticObjectException {
		if (this.startNode==null){//inizio
			this.startNode=this.getVirtualNode(
					(DefaultGraphCell)getApplication().getModelPanel().getJgraph().getFirstCellForLocation(arg0.getX(),arg0.getY()));
			if (this.startNode!=null){
				log.reprintln("clicca sul secondo nodo");
			}
			return;
		}
		if (this.endNode==null){//il primo nodo e' stato scelto
			this.endNode=this.getVirtualNode(
					(DefaultGraphCell)getApplication().getModelPanel().getJgraph().getFirstCellForLocation(arg0.getX(),arg0.getY()));
			//effettual il caso del validator
			if (this.endNode!=null){
				ValidatorMessage errors;
				try {
					errors = getValidator().ValidateUserAction(warningList, new UserAction(null, startNode, endNode, false),getModel(),
						true,//1
						true,//2
						true,//3
						true,//4
						true,//7
						true,//8
						true,//9
						true,//10
						true,//11
						true,//12
						true,//13
						true,//14
						true//15
					);
					this.controlErrorinGraph(errors);
				} catch (InstantiationException | IllegalAccessException| ParametersRequiredMismatchException e) {
					e.printStackTrace();
				} finally {
					this.exitTotalOperations();
				}
			}
		}
	}
	
	@Override
	public void rightClickOperations(MouseEvent arg0)throws NullStaticObjectException {
		log.reprintln();
		this.printOnLogWarningList();
		log.println("operazione annullata");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseClicked(e);
	}

	@Override
	protected void exitSpecificOperations() throws NullStaticObjectException {
		this.resetFlow();
	}

}
