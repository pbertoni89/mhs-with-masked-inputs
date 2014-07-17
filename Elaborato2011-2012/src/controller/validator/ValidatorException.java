package controller.validator;

/**eccezione radice che raggruppa tutte le eccezioni che le classi figlie del
 * ValidatorErrorManager possono lanciare. 
 * 
 * @author Koldar
 * @version 1.0
 * @see controller.validator.ValidatorErrorManager ValidatorErrorManager
 */
public abstract class ValidatorException extends Exception{

	private static final long serialVersionUID = -6895161726186627133L;

	/**costurisce una nuova eccezione con il particolare messaggio
	 * 
	 * @param message il messaggio da inserire nell'eccezione
	 */
	public ValidatorException(String message){
		super(message);
	}
	
	/**2 eccezioni del validator sono uguali se contengono lo stesso messaggio
	 * 
	 */
	public boolean equals(Object arg0){
		if (arg0 instanceof ValidatorException){
			return this.getMessage().equals(((ValidatorException)arg0).getMessage());
		}else{
			return super.equals(arg0);
		}
	}
}
