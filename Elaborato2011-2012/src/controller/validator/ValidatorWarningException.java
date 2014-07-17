package controller.validator;

/**rappresenta la radice delle eccezioni da lanciare quando un controllo di validità
 * effettuato dal Validator rileva un Warning
 * 
 * @author Koldar
 * @version 1.0
 * @see controller.validator.Validator Validator
 * @see controller.validator.ValidatorErrorManager ValidatorErrorManager
 */
public abstract class ValidatorWarningException extends ValidatorException{

	private static final long serialVersionUID = -7295502008357259858L;
	
	public ValidatorWarningException(String message) {
		super(message);
	}

}
