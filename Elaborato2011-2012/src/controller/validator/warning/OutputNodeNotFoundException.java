package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class OutputNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="output node not found!";
	}
	
	public OutputNodeNotFoundException(String message) {
		super(message);
	}
	
	public OutputNodeNotFoundException() {
		this(defaultMessage);
	}

}