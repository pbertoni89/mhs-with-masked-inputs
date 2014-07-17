package controller;

import event.numberevent.NumberEvent;

/**rappresenta un Controller che ascolta un JMenuItem contenuto nel menu' del software. 
 * 
 * @author Koldar
 * @version 1.1
 *
 */
public abstract class MenuItemHandlerController extends AbstractButtonHandlerController{

	protected MenuItemHandlerController(int _id) {
		super(_id);
	}
	
	/**non importa quale sia il MenuItem, il software non eseguira' nulla se viene
	 * richiamato il metodo {@link #controlErrorinGraph(toolkit.ValidatorMessage)}
	 * 
	 */
	@Override
	public void executeValidatedAction() {
	}
	
	@Override
	public void idleStateAction(NumberEvent<Integer> arg0){
		if (this.listenedButton!=null){
			this.listenedButton.setEnabled(true);
		}
	}
	
	public void editStateAction(NumberEvent<Integer> arg0){
		this.listenedButton.setEnabled(false);
	}

}
