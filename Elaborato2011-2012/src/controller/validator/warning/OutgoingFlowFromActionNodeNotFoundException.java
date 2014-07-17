package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class OutgoingFlowFromActionNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="outgoing flow from action node not found";
	}
	
	public OutgoingFlowFromActionNodeNotFoundException(String message) {
		super(message);
	}
	
	public OutgoingFlowFromActionNodeNotFoundException() {
		this(defaultMessage);
	}

}