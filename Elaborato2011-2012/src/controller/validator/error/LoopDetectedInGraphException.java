package controller.validator.error;

import controller.validator.ValidatorErrorException;

/**eccezione da lanciare quando viene rilevato che nel grafo passato alla routine
 * di controllo del validator è presente una ciclicità. Un grafo viene definito
 * ciclico se presenta in una sua sottoparte (insieme proprio di grafo) uno
 * stato simile a quello raffigurato:
 * <p> <img src="LoopDetectedInGraphError.png" alt="image not found"/>
 * 
 * @author Koldar
 * @version 1.0.1
 * @see LoopDetectedInGraphErrorManager
 * @see controller.validator.Validator Validator
 */
public class LoopDetectedInGraphException extends ValidatorErrorException{

	private static final long serialVersionUID = 3100009679957692808L;
	
	/**indica la stringa di errore standard contenente le cause dell'eccezione*/
	protected static String defaultMessage; 
	
	static{
		defaultMessage="a loop will be created if the current action will be executed!";
	}
	
	public LoopDetectedInGraphException(String message) {
		super(message);
	}
	
	public LoopDetectedInGraphException() {
		this(defaultMessage);
	}

}
