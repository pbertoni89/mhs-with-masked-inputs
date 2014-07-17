package controller.validator.warning;

import controller.validator.ValidatorWarningException;

public class ActionNodeNotFoundException extends ValidatorWarningException{

	private static final long serialVersionUID = -3454937556772068913L;

	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Action Node not found in this graph!";
	}
	
	public ActionNodeNotFoundException(String _message) {
		super(_message);
	}
	public ActionNodeNotFoundException(){
		this(defaultMessage);
	}

}