package controller.validator;

/**eccezione da lanciare quando il Controller passa al validator un numero di boolean
 * diverso a quello richiesto per eseguire il controllo degli errori 
 * 
 * @author Koldar
 * @version 1.0
 * @see Controller
 * @see Validator
 */
public class ParametersRequiredMismatchException extends Exception{

	private static final long serialVersionUID = -3059932634715181048L;
	
	public ParametersRequiredMismatchException(String message){
		super(message);
	}
	public ParametersRequiredMismatchException(){
		super();
	}

}
