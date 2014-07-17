package controller.validator.error;

import controller.validator.ValidatorErrorException;

public class MultitpleOutgoingFlowFromInputNodeException extends ValidatorErrorException{

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="multiple flow outgoing from a single InputNode!";
	}
	
	public MultitpleOutgoingFlowFromInputNodeException(String message) {
		super(message);
	}
	
	public MultitpleOutgoingFlowFromInputNodeException() {
		this(defaultMessage);
	}

	private static final long serialVersionUID = -6895840064577716397L;

}
