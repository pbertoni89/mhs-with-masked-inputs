package controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;


import event.numberevent.NumberEvent;
import event.numberevent.StateNumber;

/**rappresenta un controller che ascolta e pilota un AbstractButton. Affinche' il 
 * controller possa controllare efficacemente il pulsante e' necessario che al posto
 * di usare metodi come addActionListener si utilizzi {@link #wireButton(AbstractButton)}:
 * questo metodo setta variabili interne al Controller che velocizzano il transito di informazioni;
 * inoltre abilita il Controller ad ascoltare gli eventi che il pulsante genera.
 * 
 * @author Koldar
 * @version 1.0
 */
public abstract class AbstractButtonHandlerController extends WarningUserController implements ActionPerformerController, UserStateListener{

	/**rappresenta il pulsante ascoltato dal Controller*/
	protected AbstractButton listenedButton;
	/**indica lo stato che rappresenta momentaneamente se il l'utente sta modificando il grafo oppure no:
	 * <ul>
	 *  <li>{@link #IDLE}: l'utente non sta modificando il grafo.</li>
	 *  <li>{@link #EDITING}: l'utente sta modificando il grafo e quindi non bisogna accettare ulteriori richieste
	 *  di modifica grafo</li>
	 * </ul>
	 * lo stato e' particolarmente utile per abilitare/disabilitare i pulsante nella GUI
	 */
	protected static StateNumber<Integer> state;
	
	static{
		state=new StateNumber<Integer>(START);
	}
	
	protected AbstractButtonHandlerController(int _id) {
		super(_id);
		state.addNumberListener(this);
	}
	
	/**cabla il presente Controller con il dato pulsante e viceversa.
	 * 
	 * @param button il pulsante da cablare con il Controller. In caso il Controller era gia' cablato
	 * con un altro pulsante, il precedente bottone viene slegato dal controller
	 */
	public void wireButton(AbstractButton button){
		if (this.listenedButton!=null){
			this.listenedButton.removeActionListener(this);
		}
		this.listenedButton=button;
		button.addActionListener(this);
	}

	/**imposta lo {@link #state} a {@link #EDITING}, quindi richiama il metodo
	 * {@link #actionOverseenPerformed(ActionEvent)}
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			state.setValue(EDITING);
			this.actionOverseenPerformed(arg0);
		}catch(NullStaticObjectException error){
			error.printStackTrace();
		}
	}
	
	@Override
	public void idleStateAction(NumberEvent<Integer> e){
		//TODO sar' astratto questo metodo???
	}
	/**in caso in cui il {@link #listenedButton} sia stato cablato (cioe' non sia NULL),
	 * disattiva tale pulsante 
	 */
	@Override
	public void EditingStateAction(NumberEvent<Integer> e){
		if (listenedButton!=null){
			this.listenedButton.setEnabled(false);
		}
	}
	@Override
	public void startStateAction(NumberEvent<Integer> e){
	}
	/**indica al software che l'azione del Controller e' finalmente terminata.
	 * Praticamente imposta {@link #state} al valore di {@link #IDLE}
	 * 
	 */
	public void setIdle(){
		state.setValue(IDLE);
	}

}
