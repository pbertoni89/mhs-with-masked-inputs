package toolkit;

import graph.comp.Node;

/**
 * rappresenta l'azione che l'utente esegue e che deve essere passata al Validator. Grazie a tale classe il Validator capisce quale azione l'utente ha intrapreso.
 * @author   Koldar
 * @version   1.0
 * @see #controller   Validator
 */
public class UserAction {

	/**
	 * rappresenta il nodo che l'utente ha modificato (new,edit,delete)
	 * @uml.property  name="nodeToBeEdit"
	 * @uml.associationEnd  
	 */
	private Node nodeToBeEdit;
	/**
	 * rappresenta il punto di partenza dell'eventuale Flow aggiunto dall'utente
	 * @uml.property  name="startFlow"
	 * @uml.associationEnd  
	 */
	private Node startFlow;
	/**
	 * rappresenta il punto di terminazione dell'eventuale Flow aggiunto dall'utente
	 * @uml.property  name="endFlow"
	 * @uml.associationEnd  
	 */
	private Node endFlow;
	/**
	 * TRUE sse l'utente intende eliminare un elemento nel grafo
	 * @uml.property  name="toerase"
	 */
	private boolean toerase;
	
	/**crea un nuovo UserAction che dovr� poi essere successivamente passato al Validator
	 * per ricostruire le azioni richieste dall'utente
	 * 
	 * @param _node l'eventuale nodo modificato dall'utente
	 * @param _startflow l'eventuale nodo-testa del flusso modificato dall'utente
	 * @param _endflow l'eventuale nodo-coda del flusso modificato dall'utente
	 * @param _toerase TRUE sse l'utente vuole eliminare un elemento dal grafo
	 */
	public UserAction(Node _node,Node _startflow,Node _endflow,boolean _toerase){
		this.nodeToBeEdit=_node;
		this.startFlow=_startflow;
		this.endFlow=_endflow;
		this.toerase=_toerase;
	}

	/**
	 * @return   the nodeToBeEdit
	 * @uml.property  name="nodeToBeEdit"
	 */
	public final Node getNodeToBeEdit() {
		return nodeToBeEdit;
	}
	

	/**
	 * @return   the startFlow
	 * @uml.property  name="startFlow"
	 */
	public final Node getStartFlow() {
		return startFlow;
	}

	/**
	 * @return   the endFlow
	 * @uml.property  name="endFlow"
	 */
	public final Node getEndFlow() {
		return endFlow;
	}

	/**
	 * @return   the toerase
	 * @uml.property  name="toerase"
	 */
	public final boolean isToerase() {
		return toerase;
	}
	
	
}
