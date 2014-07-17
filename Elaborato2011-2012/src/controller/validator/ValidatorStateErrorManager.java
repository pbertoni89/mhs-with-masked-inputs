package controller.validator;

/**rappresenta il gestore di un errore/warning che richiede, per la sua rilevazione,
 * l'utilizzo di uno stato.
 * 
 * @author Koldar
 * @version 1.0
 *
 */
public abstract class ValidatorStateErrorManager<ERROR extends ValidatorException> extends ValidatorErrorManager<ERROR>{

	/**indica lo stato della verifica dell'errore*/
	protected byte state;
	
	public ValidatorStateErrorManager(int _errorID,Class<ERROR> exceptiontype) {
		super(_errorID,exceptiontype);
		this.state=0;
	}
	
}
