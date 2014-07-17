package controller.validator.error;

import controller.validator.ValidatorErrorException;

public class OutgoingFlowFromOutputNodeException extends ValidatorErrorException{

	private static final long serialVersionUID = -2455087979783157104L;
	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="Non puoi inserire un flusso uscente da un uscita";
	}
	
	public OutgoingFlowFromOutputNodeException(String message) {
		super(message);
	}
	
	public OutgoingFlowFromOutputNodeException() {
		this(defaultMessage);
	}

}
