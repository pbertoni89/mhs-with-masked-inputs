package controller.menubar;


import java.awt.event.ActionEvent;

import controller.MenuItemHandlerController;
import controller.NullStaticObjectException;
import controller.SaveBoundController;
import controller.validator.warning.ActionNodeNotFoundException;
import controller.validator.warning.InputNodeNotFoundException;
import controller.validator.warning.OutputNodeNotFoundException;



/**La classe si pone come obiettivo quello di gestire l'evento in cui l'utente
 * desideri cancellare il grafo con sta lavorando e rimpiazzarlo con uno completamente
 * nuovo.
 * 
 * @author michael
 * @author Koldar
 * @version 1.2
 * @see graph.comp.OutputNode
 * @see gui.ObservationPanel
 * @see gui.ResultRenderer
 */
public class NewFileController extends MenuItemHandlerController implements SaveBoundController{

	/**costurisce un nuovo controller abilitato all'ascolto di pulsanti per la creazione di un
	 * nuovo grafo
	 * 
	 */
	public NewFileController() {
		super(IDCONTROLLER_NEWFILE);
		toBeSavedWithSaveAs.setValue(false);
	}

	
	/**Dato il {@link #getModel()} e {@link #getApplication()} non nulli, il metodo
	 * e' in grado di eliminare sia il grafo virtuale che quello grafico. Inoltre,
	 * in caso di presenza di OutputNode, il metodo elimina i relativi Box di risultato
	 * all'interno del ObservationPanel. Quello che rimane alla fine, dunque, e':
	 * <ul>
	 *  <li>un grafo vuoto</li>
	 *  <li>warning di InputNodeNotFound,ActionNodeNotFound,OutputNodeNotFound</li>
	 *  <li>un ObservationPanel vuoto</li>
	 *  <li>un ModelPanel vuoto</li>
	 * </ul>
	 */
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		getModel().eraseModel();
		getApplication().rightpanel.getObservationPanel().removeAll();
		toBeSavedWithSaveAs.setValue(true);
		this.controlErrorinGraph(null,new InputNodeNotFoundException(),new ActionNodeNotFoundException(),new OutputNodeNotFoundException());
		log.reprintln();
		this.printOnLogWarningList();
		log.println("Nuovo file disponibile");
		getApplication().rightpanel.getObservationPanel().repaint();
		getApplication().rightpanel.getObservationPanel().validate();
		this.setIdle();
	}
}
