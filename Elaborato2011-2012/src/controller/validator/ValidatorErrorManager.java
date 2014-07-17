package controller.validator;


import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;


/**
 * rappresenta la classe radice che rappresenta le routine di gestione degli errori. Ogni gestione errore presenta 2 peculiarit�: <ul> <li>  {@link #errorMessage}   indica il messaggio di errore da mostrare se la gestione di un errore non ha successo</li> <li>  {@link #errorID}   indica il codice univoco di ogni errore </ul> ogni routine di gestione errore ha, inoltre, il metodo   {@link #specificResponse()}   che contiene l'insieme di azioni da eseguire per gestire l'errore: dato che ogni errore va gestito in modo diverso, ci saranno pi� estensioni di questa classe ognuna risolvente un errore diverso
 * @author   Koldar
 * @version   1.2
 */
public abstract class ValidatorErrorManager<ERROR extends ValidatorException> {
	
	/**
	 * rappresenta il numero che identifica univocamente il tipo dell'errore
	 * @uml.property  name="errorID"
	 */
	private int errorID;
	/**
	 * indica l'eccezione da lanciare in caso di errore
	 * @uml.property  name="exceptionthrown"
	 */
	private Class<ERROR> exceptionthrown;
	
	public ValidatorErrorManager(int _errorID,Class<ERROR> exceptiontype){
		this.errorID=_errorID;
		this.exceptionthrown=exceptiontype;
	}


	/**l'insieme delle azioni che devono essere eseguite per 
	 * controllare e (opzionalmente) eliminare un particolare errore
	 *  
	 * @param _source indica il nodo sorgente indicato che deve essere sottoposto alla verifica
	 * anti-errore
	 * @param _currentgraph indica il grafo che deve essere controllato. Tale grafo non verr�
	 * assolutamente modificato alla fine del processo 
	 * @throws ValidatorException in caso in cui la routine dedicata ad un particolare
	 *  errore rilevi nel grafo proprio l'errore che cercava
	 */
	public abstract void specificHandler(Node _source,DirectedGraph<Node,Flow> _currentgraph,boolean _islastnode) throws ValidatorException;


	/**
	 * @return   the errorID
	 * @uml.property  name="errorID"
	 */
	public final int getErrorID() {
		return errorID;
	}


	/**
	 * @return   the exceptionthrown
	 * @uml.property  name="exceptionthrown"
	 */
	public Class<ERROR> getExceptionthrown() {
		return exceptionthrown;
	}
	
	

	
}
