package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class IngoingFlowInOutputNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Ingoing flow in output node not found!";
	}
	
	public IngoingFlowInOutputNodeNotFoundException(String message) {
		super(message);
	}
	public IngoingFlowInOutputNodeNotFoundException() {
		this(defaultMessage);
	}

}