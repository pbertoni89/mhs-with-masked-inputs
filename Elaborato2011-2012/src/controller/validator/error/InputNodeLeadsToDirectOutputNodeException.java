package controller.validator.error;

import controller.validator.ValidatorErrorException;

/**errore da lanciare quando il grafo contiene un nodo d'ingresso che conduce
 * DIRETTAMENTE (cioé senza nodi intermediari) ad un nodo d'uscita. La situazione
 * descritta è graficata in questo disegno:
 * <p> <img src="InputNodeLeadsToDirectOutputNode.png" alt="image not found"/>
 * 
 * @author Koldar
 * @version 1.0
 * @see InputNodeLeadsTODirectOutputNodeErrorManager
 * @see controller.validator.Validator
 *
 */
public class InputNodeLeadsToDirectOutputNodeException extends ValidatorErrorException{

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Input node leading directly to an output node detected!";
	}
	
	public InputNodeLeadsToDirectOutputNodeException(String message) {
		super(message);
	}
	
	public InputNodeLeadsToDirectOutputNodeException() {
		this(defaultMessage);
	}
	
	private static final long serialVersionUID = -7869362636822050323L;

}
