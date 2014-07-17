package controller.menubar;

import java.awt.event.ActionEvent;
import controller.Controller;
import controller.MenuItemHandlerController;
import controller.NullStaticObjectException;

/**gestisce le azioni che devono essere fatte quando l'utente preme il pulsante
 * sul menu' che denomina l'uscita dal software.
 * 
 * @author Koldar
 * @version 1.1
 * @see ApplicationBoundController
 */
public class ExitProgramController extends MenuItemHandlerController{

	/**@see Controller
	 * 
	 */
	public ExitProgramController(){
		super(IDCONTROLLER_EXITPROGRAMFILE);
	}
	
	/**chiude la finestra principale immediatamente. Tale azione pu� essere eseguita
	 * unicamente se {@link #getApplication()} restituisce un valore non nullo: in caso
	 * contrario non viene eseguita alcunche.
	 * 
	 */
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		getApplication().dispose();
	}

}
