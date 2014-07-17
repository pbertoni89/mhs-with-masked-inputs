package controller.validator.error;

import controller.validator.ValidatorErrorException;

/**
 * 
 * @author Koldar
 *
 */
public class MultipleIngoingFlowInOutputNodeException extends ValidatorErrorException{

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="a certain output node has more than one predecessor!";
	}
	
	public MultipleIngoingFlowInOutputNodeException(String message) {
		super(message);
	}
	
	public MultipleIngoingFlowInOutputNodeException() {
		this(defaultMessage);
	}

	private static final long serialVersionUID = 8619380753075756991L;

}
