package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class IngoingFlowInActionNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Ingoing flow in actionNode not found!";
	}
	
	public IngoingFlowInActionNodeNotFoundException(String message) {
		super(message);
	}
	
	public IngoingFlowInActionNodeNotFoundException() {
		this(defaultMessage);
	}
	

}