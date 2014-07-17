package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class InputNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Input node not found!";
	}
	
	public InputNodeNotFoundException(String message) {
		super(message);
	}
	
	public InputNodeNotFoundException() {
		this(defaultMessage);
	}

}
