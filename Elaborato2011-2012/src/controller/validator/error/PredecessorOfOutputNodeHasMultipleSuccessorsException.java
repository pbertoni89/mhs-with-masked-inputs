package controller.validator.error;

import controller.validator.ValidatorErrorException;

public class PredecessorOfOutputNodeHasMultipleSuccessorsException extends ValidatorErrorException{

	private static final long serialVersionUID = -3330176707017384882L;
	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="predecessor of outputNode has multiple Successors!";
	}
	
	public PredecessorOfOutputNodeHasMultipleSuccessorsException(String message) {
		super(message);
	}
	
	public PredecessorOfOutputNodeHasMultipleSuccessorsException() {
		this(defaultMessage);
	}

}
