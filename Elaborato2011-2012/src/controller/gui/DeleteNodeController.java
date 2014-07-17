package controller.gui;

import event.numberevent.NumberEvent;
import graph.comp.Node;
import graph.comp.OutputNode;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jgraph.graph.DefaultGraphCell;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.NullStaticObjectException;
import controller.VisualGraphInteractorController;
import controller.validator.ParametersRequiredMismatchException;

/**rappresenta il Controller che pilota il pulsante per rimuovere
 * un nodo all'interno del grafo. Il Controller comunica con 2 componenti:
 * <ul>
 *  <li>il pulsante stesso</li>
 *  <li>il pannello su cui il grafo e' disegnato</li>
 * </ul>
 * 
 * Una volta entrati in modalita' "elimina nodo" è possibile uscirne cliccando in
 * un qualunque punto del pannello in cui il grafo e' disegnato con il destro (o con
 * il pulsante al centro)
 * 
 * @author Koldar
 * @version 1.1
 */
public class DeleteNodeController extends VisualGraphInteractorController implements MouseListener{

	/**
	 * rappresenta il nodo <strong>logico</strong> che l'utente vuole eliminare
	 * @uml.property  name="toBeDeleted"
	 * @uml.associationEnd  
	 */
	private Node toBeDeleted;
	
	public DeleteNodeController(){
		super(IDCONTROLLER_DELETENODE);
		this.resetNode();
	}
	
	/**imposta il {@link #toBeDeleted} a NULL
	 * 
	 */
	private void resetNode(){
		this.toBeDeleted=null;
	}

	//TODO bisognerebbe in VisualGraphController inserire un metodo che quando premi il destro (o ESC) esci dal wizard

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void executeValidatedAction() {
		try {
			getModel().removeVertex(toBeDeleted);
			if(toBeDeleted instanceof OutputNode){
				getApplication().getRightpanel().getObservationPanel().removeResultRenderer((OutputNode)toBeDeleted);
			}
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
		
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
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		log.reprintln("clicca sul nodo che vuoi eliminare");
		this.addVisualGraphListener();
		//TODO inserire che quando premi il pulsante la selezione della cella scompare (in modo da essere pi� user friendly)	
	}
	
	/**
	 * 
	 * @return TRUE se il modello comprende almeno un nodo, FALSE altrimenti
	 * @throws NullStaticObjectException in caso in cui il modello no nsia stato ancora cablato con il Controller
	 */
	private boolean isNodeSetBigEnough() throws NullStaticObjectException{
		return (getModel().vertexSet().size()>=1);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void leftClickOperations(MouseEvent arg0)throws NullStaticObjectException {
		toBeDeleted=this.getVirtualNode(
				(DefaultGraphCell)getApplication().getModelPanel().getJgraph().getFirstCellForLocation(arg0.getX(),arg0.getY()));
		if (toBeDeleted!=null){
			ValidatorMessage errors;
			try {
				errors = getValidator().ValidateUserAction(warningList, new UserAction(toBeDeleted, null ,null , true),getModel(),
						false,//1
						false,//2
						false,//3
						false,//4
						false,//5
						false,//6
						true,//7
						true,//8
						true,//9
						true,//10
						true,//11
						true,//12
						true//13
						);
				this.controlErrorinGraph(errors);
			} catch (InstantiationException | IllegalAccessException| ParametersRequiredMismatchException error) {
				error.printStackTrace();
			} finally{
				this.exitTotalOperations();
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
	protected void exitSpecificOperations() throws NullStaticObjectException {
		this.resetNode();
	}
	
}
