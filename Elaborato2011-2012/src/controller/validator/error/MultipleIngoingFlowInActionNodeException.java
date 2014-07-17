package controller.validator.error;

import controller.validator.ValidatorErrorException;

/**eccezione da lanciare quando nel grafo esiste almeno un nodo 
 * intermedio in cui entrano più flussi. Ciò può essere descritto
 * mediante questo semplice disegno:
 * <p> <img src="MultipleIngoingFlowInActionNode.png" alt="image not found"/>
 * 
 * @author Koldar
 * @version 1.0
 * @see MultipleIngoingFlowInActionNodeErrorManager
 * @see controller.validator.Validator Validator
 *
 */
public class MultipleIngoingFlowInActionNodeException extends ValidatorErrorException{

	private static final long serialVersionUID = -8080388697930416689L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="multiple ingoing flow to an action node detected!";
	}
	
	public MultipleIngoingFlowInActionNodeException(String message) {
		super(message);
	}
	
	public MultipleIngoingFlowInActionNodeException() {
		this(defaultMessage);
	}

}
