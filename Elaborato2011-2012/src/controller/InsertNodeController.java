package controller;

import event.numberevent.NumberEvent;
import graph.comp.Node;
import gui.InsertNodeNameDialog;
import gui.ObservationPanel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Set;


import controller.validator.ParametersRequiredMismatchException;

import toolkit.ValidatorMessage;

/**rappresenta il generico pulsante che inserisce un nodo nel grafo. La classe offre metodi
 * per creare in maniera piu' semplice il nuovo nodo. Affinche' il nodo venga creato dall'utente
 * e' necessario che l'utente interagisca in piu' punti con il software:
 * <ul>
 *  <li>l'azione del pulsante</li>
 *  <li>decisione sulle coordinate (x;y) del nuovo nodo sul pannello di disegno del grafo</li>
 *  <li>decisione sulle coordinate (w;h) del nuovo nodo sul pannello di disegno del grafo</li>
 *  <li>decisione nome del nuovo nodo</li>
 * </ul>
 * 
 * 
 * @author patrizio
 * @author Koldar
 * @version 1.3
 * 
 */
public abstract class InsertNodeController<NODETYPE extends Node> extends VisualGraphInteractorController implements MouseListener,MouseMotionListener{

	/**titolo del Dialog che chiede il nome del nuovo nodo*/
	private static final String TEXT = "Insert new %s name: ";
	/**indica le dimensioni standard che il nodo grafico assume in caso in cui l'utente non esprima un'opinione al riguardo*/
	private static final Dimension DEFAULTDIMENSION=new Dimension(100,50);
	private static final Dimension MINIMALDIMENSION=new Dimension(25,10);
	/**rappresenta il tipo di nodo da inserire*/
	private String nodeTypeName;
	/**rappresenta il nodo da inserire nel grafo*/
	private NODETYPE node;
	/**rappresenta la clip area che l'utente traccia per disegnare le nuove dimensioni della cella del nuovo nodo*/
	private Rectangle mouseClip;
	/**indica se l'utente ha draggato il mouse durante la creazione visuale del nuovo nodo oppure no*/
	private boolean mouseDragged;
	/**rappresentano le dimensioni che la cella grafica del nuovo nodo possiede in caso in cui l'utente clicchi sul grafo, senza draggare il mouse*/
	private Dimension standardDimension;
	
	public InsertNodeController(int idcontroller,String nodeTypeName) {
		this(idcontroller,nodeTypeName,DEFAULTDIMENSION);
	}
	/**crea un nuovo controller che e' in grado (tramite apposite classi grafiche) di pilotare
	 * la creazione di un nuovo nodo
	 * 
	 * @param idcontroller indica l'identificativo univoco del Controller
	 * @param nodeTypeName indica il nome del tipo di nodo che questo controller e' in grado di costurire
	 * @param nodestandarddimension indica la dimensione di default che il nodo deve assumere in caso in cui l'utente non
	 * specifici una dimensione personalizzata
	 */
	public InsertNodeController(int idcontroller,String nodeTypeName,Dimension nodestandarddimension) {
		super(idcontroller);
		
		this.nodeTypeName=nodeTypeName;
		this.standardDimension=nodestandarddimension;
		this.mouseClip=new Rectangle();
		this.mouseDragged=false;
	}
	
	
	/**Questo metodo elenca la lista di istruzioni che deve essere eseguita qualora il nodo aggiunto 
	 * dall'utente possa essere effettivamente inglobato nel grafo; in questo caso ci potrebbero
	 * essere modifiche grafiche non solo al ModelPanel, ma anche ad altre componenti grafiche
	 * 
	 * @param observationPanel rappresenta il pannello che eventualmente va aggiornato con il nuovo nodo
	 * @param node indica il nodo da inserire
	 */
	public abstract void addingNodeList(ObservationPanel observationPanel , NODETYPE node);
	
	
	/**indica le istruzioni da eseguire quando il software deve costruire
	 * la struttura logica del nodo richiesto dall'utente.
	 * 
	 * @param nodename il nome del nodo che deve essere inserito
	 */
	public abstract NODETYPE insertNewNode(String nodename);
	
	/**indica la serie di istruzioni da eseguire per verificare le conseguenze
	 * sullo stato del grafo qualora l'azione richiesta dall'utente
	 * venisse eseguita sul grafo in modifica. Tale verifica quindi <strong>non</strong>
	 * modifica il grafo corrente.   
	 * 
	 * @param n1 il primo nodo convolto nella modifica dell'utente
	 * @param n2 l'eventuale secondo nodo coinvolto nella modifica dell'utente. Tale parametro
	 * e' utile solo se l'azione utente coinvolge dei flussi. Un flusso infatti e' contraddistinto
	 * da una coppia di nodi.
	 * @return una struttura emanata dal Validator che contiene informazioni circa i warning e gli errori
	 * che verrebbero generati se il software eseguisse l'azione utente richiesta.
	 * @throws ParametersRequiredMismatchException
	 * @throws NullStaticObjectException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	//TODO documentazione in cui togliere le eccezione che tanto non capiteranno mai!
	public abstract ValidatorMessage getErrorWarningOccured(Node n1,Node n2)throws ParametersRequiredMismatchException, NullStaticObjectException, InstantiationException, IllegalAccessException;
	
	@Override
	public void executeValidatedAction() {
		try {
			this.addingNodeList(getApplication().rightpanel.getObservationPanel(),node);
			getModel().addVertex(node);
			getApplication().getModelPanel().ApplyAttributeToNode(node,this.mouseClip);
			
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
		
	}
	
	/**se l'utente preme il click del mouse, il software non deve eseguire nulla di particolare
	 * 
	 */
	@Override
	public void leftClickOperations(MouseEvent arg0){
		
	}
	
	@Override
	public void rightClickOperations(MouseEvent arg0){
		this.mouseDragged=false;
		log.reprintln();
		this.printOnLogWarningList();
		log.println("operazione annullata");
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0){
		this.mouseClip.width=arg0.getX()-this.mouseClip.x;
		this.mouseClip.height=arg0.getY()-this.mouseClip.y;
		this.mouseDragged=true;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.mouseClip.x=arg0.getX();
		this.mouseClip.y=arg0.getY();
	}
	
	/**dato il rettangolo che viene assegnato durante i movimenti del mouse, modifica
	 * i valori del rettangolo stesso in modo che rappresenti la vera area tracciata dall'utente.
	 * Se non ci fosse questo metodo il rettangolo tracciato dall'utente partendo da un punto
	 * e draggando il mouse veros l'alto a sinistra non verrebbe correttamente incamerato.
	 * <p>Inoltre il metodo si preoccupa che la dimensione settata dall'utente non sia troppo
	 * piccola. Se lo e' (dimensione minore di {@link #MINIMALDIMENSION})imposta la dimensione troppo scarsa ai valori espressi in {@link #DEFAULTDIMENSION}
	 */
	private void calculateNormalRectangle(){
		if (this.mouseClip.width<0){
			this.mouseClip.x=this.mouseClip.x+this.mouseClip.width;
			this.mouseClip.width=Math.abs(this.mouseClip.width);
		}
		if (this.mouseClip.width<MINIMALDIMENSION.width){
			this.mouseClip.width=DEFAULTDIMENSION.width;
		}
		if (this.mouseClip.height<0){
			this.mouseClip.y=this.mouseClip.y+this.mouseClip.height;
			this.mouseClip.height=Math.abs(this.mouseClip.height);	
		}
		if (this.mouseClip.height<MINIMALDIMENSION.height){
			this.mouseClip.height=DEFAULTDIMENSION.height;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0){
		Set<Node> nodeSet = null;
		InsertNodeNameDialog dialog = null;
		if (arg0.getButton()!=MouseEvent.BUTTON1){//l'utente tiene premuto e rilascia un pulsante <> dalsinistro
			return;
		}

		if (this.mouseDragged==false){//utilizzo le dimensioni standard
			this.mouseClip.width=this.standardDimension.width;
			this.mouseClip.height=this.standardDimension.height;
		}else{
			this.calculateNormalRectangle();
		}
		try {
			nodeSet = getModel().vertexSet();
			dialog = new InsertNodeNameDialog(
					getApplication(),
					true, // dialogo modale
					String.format(TEXT,this.nodeTypeName),
					nodeSet);
			if (dialog.isOk()){
				this.node=this.insertNewNode(dialog.getNewNameOk());
				ValidatorMessage errors=this.getErrorWarningOccured(node,null);
				this.controlErrorinGraph(errors);
			}else{
				this.rightClickOperations(arg0);
			}
			this.exitTotalOperations();
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParametersRequiredMismatchException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void idleStateAction(NumberEvent<Integer> e){
		this.listenedButton.setEnabled(true);
	}
	
	@Override
	public void exitSpecificOperations(){
	}
	
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		this.mouseDragged=false;
		this.addVisualGraphListener();
		log.reprintln("spostati sul grafo e disegna le dimensioni del nuovo nodo.");
	}

}
