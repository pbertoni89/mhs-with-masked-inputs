package controller;

import graph.comp.Node;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import org.jgraph.graph.DefaultGraphCell;

import controller.validator.ValidatorException;

/**rappresenta un Controller che interagisce con il {@link #gui.ModelPanel ModelPanel} rilevano
 * gli eventi dell'utente, come un eventuale click del mouse. La classe fornisce quindi utili
 * servizi per interagire correttamente con la versione <strong>grafica</strong> del grafo
 * corrente
 * 
 * @author Koldar
 * @version 1.3
 *
 */
public abstract class VisualGraphInteractorController extends ButtonHandlerController implements MouseListener,MouseMotionListener{

	protected VisualGraphInteractorController(int _id) {
		super(_id);
	}
	
	/**data una cella cliccata dall'utente, il metodo ritorna il nodo virtuale che
	 * la rappresenta. Praticamente effettua il passaggio da DefaultGraphCell a Node (proprio
	 * il passaggio inverso del metodo getGraphCell). Se la cella non viene trovata,
	 * restituisce NULL
	 * 
	 * @param cell la cella grafica da cui ricavare la cella virtuale
	 * @return la cella virtuale che rappresenta nel modello jgrapht la cella grafica
	 */
	public Node getVirtualNode(DefaultGraphCell cell){
		Iterator<Node> iterator = null;
		Node node=null;
		try {
			iterator=getModel().vertexSet().iterator();
			while (iterator.hasNext()){
				node=iterator.next();
				if (getApplication().getModelPanel().getJgraphAdapter().getVertexCell(node).equals(cell)){
					return node;
				}
			}
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**fa in modo che questo controller si interessi agli eventi di tipo
	 * Mouse e MouseMotion del JGraph panel
	 * 
	 * @throws NullStaticObjectException in caso in cui il JGraph panel non sia stato cablato
	 * con i Controller
	 */
	protected void addVisualGraphListener() throws NullStaticObjectException{
		getApplication().getModelPanel().getJgraph().addMouseListener(this);
		getApplication().getModelPanel().getJgraph().addMouseMotionListener(this);
	}
	
	/**fa in modo che questo controller si disinteressi agli eventi di tipo
	 * Mouse e MouseMotion del JGraph panel
	 * 
	 * @throws NullStaticObjectException in caso in cui il JGraph panel non sia stato cablato
	 * con i Controller
	 */
	protected void removeVisualGraphListener() throws NullStaticObjectException{
		getApplication().getModelPanel().getJgraph().removeMouseListener(this);
		getApplication().getModelPanel().getJgraph().removeMouseMotionListener(this);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0){
		try{
			if (arg0.getButton()==MouseEvent.BUTTON1){
				this.leftClickOperations(arg0);
				return;
			}
			if (arg0.getButton()==MouseEvent.BUTTON3){
				this.exitTotalOperations();
				for (ValidatorException e:warningList){
					log.println(e.getMessage());
				}
				this.rightClickOperations(arg0);
			}
		}catch(NullStaticObjectException error){
			error.printStackTrace();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0){
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0){
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0){
		
	}
	
	
	
	/**indica la serie di istruzioni per terminare il wizard corrente.
	 * Tale metodo comprende:
	 * <ul>
	 *  <li> la rimozione dei listener Mouse/MouseMotion</li>
	 *  <li>l'impostazione dello stato ad IDLE</li>
	 * </ul>
	 * 
	 * Il metodo deve essere richiamato quando il wizard e' finito.
	 * 
	 * 
	 * @throws NullStaticObjectException
	 */
	protected void exitTotalOperations() throws NullStaticObjectException{
		this.exitSpecificOperations();
		this.removeVisualGraphListener();
		this.setIdle();
	}
	
	/**indica una lista di istruzioni da eseguire quando il wizard viene
	 * terminato, sia in caso di terminazione voluta dell'utente che per completamento
	 * del wizard stesso. il metodo viene richiamato in {@link #exitTotalOperations()},
	 * quindi i listener e lo stato di IDLE vengono gia' eseguite
	 * 
	 * @throws NulStaticObjectException
	 */
	protected abstract void exitSpecificOperations() throws NullStaticObjectException;
	
	/**indica la lista di istruzioni quando l'utente preme il pulsante
	 * sinistro del mouse, segno che vuole procedere il wizard che questo
	 * Contoller gestisce
	 * 
	 * @param arg0 l'evento mouse che ha scatenato l'utente
	 * 
	 * @throws NullStaticObjectException in caso enga usato un oggetto del Controller non ancora cablato
	 */
	public abstract void leftClickOperations(MouseEvent arg0) throws NullStaticObjectException;
	
	/**indica la lista di istruzioni da eseguire quando l'utente preme il tasto
	 * destro del mouse, sinonimo di rinuncia dell'attuale wizard.
	 * Il metodo viene eseguito subito dopo {@link #exitTotalOperations()}; quindi in questo
	 * metodo non va messo:
	 * <ul>
	 *  <li>la rimozione dei listener</li>
	 *  <li>il settaggio dello stato a IDLE</li>
	 *  <li>il println della lista di warning (se un'azione viene annullata i precedenti warning vanno riscritti)<li>
	 *  <li>le istruzioni contenute in {@link #exitSpecificOperations()}</li>
	 * </ul>
	 * 
	 * questo perche' esse vengono gia' eseguite!
	 * 
	 * @param arg0 l'evento mouse che ha scatenato l'utente
	 * 
	 * @throws NullStaticObjectException in caso venga usato un oggetto del Controller che non e' stato ancora cablato.
	 */
	public abstract void rightClickOperations(MouseEvent arg0) throws NullStaticObjectException;

}
