package toolkit.modelstateevent;

import graph.state.ModelState;

import java.io.Serializable;
import java.util.EventObject;

/**
 * rappresenta l'evento che viene trasmesso quando un componente deve comunicare con i suoi <tt>ModelStateListeners</tt>. La classe consta fondamentalmente di 2 attributi, rappresentanti lo stato prima e dopo un certo evento (una modifica al Model solitamente)
 * @author   Koldar
 * @version   1.0
 */
//TODO secondo me il pacchetto modelstateevent dovrebbe andare in graph! parlare con pat & micheal
public class ModelStateEvent extends EventObject implements Serializable{
	
	private static final long serialVersionUID = -6718530625842853183L;
	/**
	 * indica lo stato che il Model possedeva prima di subire una modifica
	 * @uml.property  name="fromState"
	 * @uml.associationEnd  
	 */
	private ModelState fromState;
	/**
	 * indica lo stato che il Model possiede dopo aver subito la modifica
	 * @uml.property  name="toState"
	 * @uml.associationEnd  
	 */
	private ModelState toState;

	/**costruisce un nuovo ModelStateEvent in modo che il Listener possa capire cosa sia successo
	 * 
	 * @param source indica l'oggetto che ha spedito il messaggio
	 * @param fromState indica lo stato del Model prima di subire la modifica
	 * @param toState indica lo stato del Model dopo aver subito la modifica
	 */
	public ModelStateEvent(Object source,ModelState fromState,ModelState toState) {
		super(source);
		this.fromState=fromState;
		this.toState=toState;
	}

	/**
	 * @return   the fromState
	 * @uml.property  name="fromState"
	 */
	public ModelState getFromState() {
		return fromState;
	}

	/**
	 * @param fromState   the fromState to set
	 * @uml.property  name="fromState"
	 */
	public void setFromState(ModelState fromState) {
		this.fromState = fromState;
	}

	/**
	 * @return   the toState
	 * @uml.property  name="toState"
	 */
	public ModelState getToState() {
		return toState;
	}

	/**
	 * @param toState   the toState to set
	 * @uml.property  name="toState"
	 */
	public void setToState(ModelState toState) {
		this.toState = toState;
	}
	

}
