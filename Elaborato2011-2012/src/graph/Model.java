package graph;

import graph.comp.Flow;
import graph.comp.Node;
import graph.comp.OutputNode;
import graph.state.CorrectModelState;
import graph.state.ErrorModelState;
import graph.state.ModelState;
import graph.state.WarningModelState;

import java.io.Serializable;
import java.util.Iterator;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ListenableDirectedGraph;

import toolkit.modelstateevent.ModelStateEvent;
import toolkit.modelstateevent.ModelStateListener;
import toolkit.modelstateevent.ModelStateManager;

/**
 * La classe rappresentante il nostro Modello, che eredita tutto l'albero gerarchico del concetto "grafo" nel package jgrapht. Alla classe e' associato uno stato, secondo il Design Pattern "State". Per modificare lo stato, a esempio per confermare la correttezza del modello, si puo' chiamare da fuori (presumibilmente dal Validator) model.setState(new CorrectModelState() ) Invece per conoscere da fuori lo stato attuale e' sufficiente chiamare model.getStatus()  e confrontarlo con gli statici  CorrectModelState.getStatus(); ErrorModelState.getStatus(); WarningModelState.getStatus();
 * @author     patrizio
 * @param < Node >
 * @param < Flow >
 * @uml.dependency   supplier="graph.state.CorrectModelState"
 */
@SuppressWarnings("unused")
public class Model extends ListenableDirectedGraph<Node, Flow> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property  name="correctModelState"
	 * @uml.associationEnd  
	 */
	private ModelState correctModelState;
	/**
	 * @uml.property  name="warningModelState"
	 * @uml.associationEnd  
	 */
	private ModelState warningModelState;
	/**
	 * @uml.property  name="errorModelState"
	 * @uml.associationEnd  
	 */
	private ModelState errorModelState;
	/**
	 * @uml.property  name="modelState"
	 * @uml.associationEnd  
	 */
	private ModelState modelState;
	
	/**
	 * @uml.property  name="modelstatemanager"
	 * @uml.associationEnd  
	 */
	private ModelStateManager modelstatemanager;
	
	/** inutilizzato per ora
	 * @param dirGraph  */
	public Model(DirectedGraph<Node, Flow> dirGraph) {
		super(dirGraph);
		
		correctModelState = new CorrectModelState();
		warningModelState = new WarningModelState();
		errorModelState = new ErrorModelState();
		
		modelState = new CorrectModelState();
		this.modelstatemanager=new ModelStateManager();
	}

	/** 
	 * @param edgeClass
	 */
	public Model(Class<Flow> edgeClass) {
		super(edgeClass);
		
		correctModelState = new CorrectModelState();
		warningModelState = new WarningModelState();
		errorModelState = new ErrorModelState();
		
		modelState = new CorrectModelState();
		this.modelstatemanager=new ModelStateManager();
	}
      
	/**dato questo modello elimina tutti i nodi presenti
	 * all'interno del modello stesso
	 */
	public void eraseModel(){
		
		while (!this.vertexSet().isEmpty()){
			Iterator<Node> it=this.vertexSet().iterator();
			if (it.hasNext()){
				this.removeVertex(it.next());
			}
		}
	}
	
	
	
	/**dato il modello passato come parametro, si assicura che il modello
	 * richiamante il metodo diventi uguale a quello passato come parametro.
	 * 
	 * @param other il modello da imitare
	 */
	public void copyModel(Model other){
		this.eraseModel();
		System.out.println("ascoltatori: "+this.modelstatemanager.size());
		//this.modelstatemanager.removeAllListeners();
		//TODO eliminar ei listenerer!!!
		Iterator<Node> vertexiterator=other.vertexSet().iterator();
		Iterator<Flow> flowiterator=other.edgeSet().iterator();
		while (vertexiterator.hasNext()){
			Node toInsert = vertexiterator.next();
			this.addVertex(toInsert);
			if(toInsert instanceof OutputNode)
			{
				//ma secondo me e pat NON VA MESSA QUA l'inserzione dell'oracle!!!
			}
		}
		while (flowiterator.hasNext()){
			Flow f=flowiterator.next();
			this.addEdge((Node)f.getSource(),(Node)f.getTarget());
		}
	}
	
	//TODO aggiungere documentazione
	/**
	 * @param  _modelState
	 * @uml.property  name="modelState"
	 */
	public void setModelState(ModelState _modelState) {
		if (!this.modelState.equals(_modelState)) {
			this.modelstatemanager.fireModelStateEvent(new ModelStateEvent(this,
					this.modelState, _modelState));
		}
		modelState = _modelState;
	}
	/**
	 * @return
	 * @uml.property  name="modelState"
	 */
	public ModelState getModelState() {
		return modelState;
	}
	
	public Model removeAllListeners(){
		this.modelstatemanager=new ModelStateManager();
		return this;
	}
	/**copia l'intero modello senza copiare i listener del modello
	 * stesso. */
	public Model clone(){
		return ((Model) super.clone()).removeAllListeners();
	}
	
	public void addModelStateListener(ModelStateListener e){
		this.modelstatemanager.addModelStateListener(e);
	}
	
	public void removeModelStateListener(ModelStateListener e){
		this.modelstatemanager.removeModelStateListener(e);
	}
	

}
