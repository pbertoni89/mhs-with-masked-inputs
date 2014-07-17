package controller;

/**errore da lanciare nel caso in cui l'oggetto statico base di ApplicationBoundController
 * piuttosto che di GraphBoundCOntroller sia impostato su NULL
 * 
 * @author Koldar
 * @version 1.0
 * @see GraphBoundController
 * @see ApplicationBoundController
 */
public class NullStaticObjectException extends Exception{

	private static final long serialVersionUID = -443817205419817318L;
	
	/**
	 * 
	 * @param error stringa da far comparire come messaggio di informazione
	 */
	public NullStaticObjectException(String error){
		super(error);
	}
	public NullStaticObjectException(){
		super();
	}

}
