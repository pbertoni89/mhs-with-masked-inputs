package controller.validator;

/**eccezione rappresentante il fatto che un errore di constistenza del grafo
 * è stato rilevato durante una routine di controllo del Validator
 * 
 * @author Koldar
 * @version 1.0
 * @see controller.validator.Validator Validator
 * @see controller.validator.ValidatorErrorManager ValidatorErrorManager
 */
public abstract class ValidatorErrorException extends ValidatorException{

	private static final long serialVersionUID = -2717768325938811982L;
	
	public ValidatorErrorException(String message) {
		super(message);
	}

}
