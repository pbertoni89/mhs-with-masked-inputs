package controller.validator.error;

import controller.validator.ValidatorErrorException;

/**eccezione da lanciare quando nel grafo possiede un flusso che entra
 * in un nodo d'ingresso
 * 
 * @author Koldar
 * @version 1.0
 * @see IngoingFlowInInputNodeErrorManager
 * @see controller.validator.Validator Validator
 *
 */
public class IngoingFlowInInputNodeException extends ValidatorErrorException{

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="non puoi inserire un flusso entrante in un nodo di ingresso!";
	}
	
	public IngoingFlowInInputNodeException(String message) {
		super(message);
	}
	
	public IngoingFlowInInputNodeException() {
		this(defaultMessage);
	}

	private static final long serialVersionUID = -3986847285963564693L;

}
