package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class OutgoingFlowFromInputNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Outgoing flow from input node not found!";
	}
	
	public OutgoingFlowFromInputNodeNotFoundException(String message) {
		super(message);
	}
	
	public OutgoingFlowFromInputNodeNotFoundException() {
		this(defaultMessage);
	}

}