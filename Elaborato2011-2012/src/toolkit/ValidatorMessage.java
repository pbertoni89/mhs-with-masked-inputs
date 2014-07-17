package toolkit;

import java.util.List;
import java.util.Vector;

import controller.validator.ValidatorErrorException;
import controller.validator.ValidatorWarningException;

/**
 * rappresenta il messaggio che il Validator ritorna per comunicare al Controller gli errori rilevati. Il messaggio consta di 2 settori: <ul> <li>  {@link #errorOccured}  : contiene l'eventuale errore che il Validator ha individuato</li> <li>  {@link #warningoccured}  : contiene la lista di errori che il Validaotr ha individuato</li> </ul>
 * @author   Koldar
 * @version   1.0
 * @see  Validator
 */
public class ValidatorMessage {

	/**
	 * rappresenta l'eventuale errore che l'azione utente verificata dal Validator ha generato
	 * @uml.property  name="errorOccured"
	 * @uml.associationEnd  
	 */
	private ValidatorErrorException errorOccured;
	/**
	 * rappresenta la lista di warning che l'azione utente verificata dal Validator ha generato
	 * @uml.property  name="warningoccured"
	 */
	private List<ValidatorWarningException> warningoccured;
	
	/**costruisce un nuovo messaggio del Validator. Questo nuovo messaggio avr�:
	 * <ul>
	 *  <li>{@link #errorOccured} nullo</li>
	 *  <li>{@link #warningoccured} vuoto (ma non nullo!)</li>
	 * </ul>
	 */
	public ValidatorMessage(){
		this.errorOccured=null;
		this.warningoccured=new Vector<ValidatorWarningException>(0,1);
	}

	/**
	 * @return   the errorOccured
	 * @uml.property  name="errorOccured"
	 */
	public final ValidatorErrorException getErrorOccured() {
		return errorOccured;
	}

	/**
	 * @param errorOccured   the errorOccured to set
	 * @uml.property  name="errorOccured"
	 */
	public final void setErrorOccured(ValidatorErrorException errorOccured) {
		this.errorOccured = errorOccured;
	}

	/**
	 * @return   the warningoccured
	 * @uml.property  name="warningoccured"
	 */
	public final List<ValidatorWarningException> getWarningoccured() {
		return warningoccured;
	}

	/**
	 * @param warningoccured   the warningoccured to set
	 * @uml.property  name="warningoccured"
	 */
	public final void setWarningoccured(List<ValidatorWarningException> warningoccured) {
		this.warningoccured = warningoccured;
	}
	/**aggiunge un nuovo WarningException nella lista dei warning rilevati.
	 * In caso il WarningException sia gi� presente nella lista di Warning, tale
	 * Warning <strong>non</strong> verr� aggiunto. La verifica del fatto che
	 * un Warning compaia o meno nella lista di warning utilizza il metodo
	 * {@link java.util.Vector.contains()} della classe Vector
	 * 
	 * @param warning il warning da aggiungere
	 */
	public final void addWarning(ValidatorWarningException warning){
		if (!this.warningoccured.contains(warning)){
			this.warningoccured.add(warning);
		}
	}
	/**aggiunge una nuova lista di warning a quelli gia' presenta in questo oggetto.
	 * In caso una parte dei nuovi warning siano gia' presenti nella {@link #warningoccured},
	 * tali warning non vengono aggiunti
	 * 
	 * @param warninglist la lista di warning da unire con {@link #warningoccured}
	 */
	public final void addWarningList(List<ValidatorWarningException> warninglist){
		for (int i=0;i<warninglist.size();i++){
			this.addWarning(warninglist.get(i));
		}
	}
	
}
