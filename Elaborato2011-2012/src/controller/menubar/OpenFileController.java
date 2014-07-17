package controller.menubar;

import graph.Model;
import graph.comp.Node;
import graph.comp.OutputNode;
import inputstream.FileObject;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFileChooser;

import toolkit.SaveGraphFilter;

import controller.Controller;
import controller.MenuItemHandlerController;
import controller.NullStaticObjectException;
import controller.SaveBoundController;
import controller.validator.ParametersRequiredMismatchException;

/**gestisce le azioni che devono essere fatte quando l'utente preme il pulsante
 * sul menu' che denomina il caricamento di un grafo precedentemente salvato.
 * 
 * @author Koldar
 * @version 1.2
 */
public class OpenFileController extends MenuItemHandlerController implements SaveBoundController{
	
	/**@see Controller
	 * 
	 */
	public OpenFileController(){
		super(IDCONTROLLER_OPENFILE);
	}
	

	public void setObservations(Set<Node> nodes) throws NullStaticObjectException{
		Iterator<Node> iterator = nodes.iterator();
		while(iterator.hasNext()){
			Node node =iterator.next();
			if(node instanceof OutputNode){
				getApplication().getRightpanel().getObservationPanel().addResultRenderer((OutputNode)node);
			}
		}
		getApplication().getRightpanel().getObservationPanel().validate();
		
	}

	/**il metodo esegue le istruzioni per aprire correttamente un file. Affinche' le operazioni
	 * di caricamento possano essere svolte senza problemi � essenziale che {@link #getApplication()},
	 * {@link #getModel()} e {@link #getValidator()} ritornino un valore non nullo. il metodo
	 * richiede un file; se:
	 * <ul>
	 *  <li>il file puo' essere aperto</li>
	 *  <li>il file contiene un oggetto compatibile con questa applicazione</li>
	 *  <li>non avvengono errori run-time di caricamento</li>
	 * </ul>
	 * allora il grafo attuale viene eliminato e al suo posto viene posato il grafo appena caricato.
	 * Insieme al grafo vengono aggiornati gli eventuali warning nonch� i result boxes
	 * 
	 * @see gui.ResultRenderer
	 */
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		int openresult=SaveBoundController.filechooser.showOpenDialog(getApplication());
		if (openresult==JFileChooser.APPROVE_OPTION){
			saveHandler.setPath(SaveGraphFilter.getSaveableName(filechooser.getSelectedFile().getAbsolutePath()));
			Model retrieved = (Model) saveHandler.LoadObject();
			if (saveHandler.getFileError()!=FileObject.NULL){
				log.reprintln("nessun dato caricato! Errore mentre carico: "+ saveHandler.getFileError()+". Il vecchio grafo non verra' eliminato.");
				this.printOnLogWarningList();
			}else{
				//************** COPIA DEI DATI *******************
				getApplication().rightpanel.getObservationPanel().removeAll();
				getModel().copyModel(retrieved);
					//colore dei nodi grafici
				getApplication().getModelPanel().colorNodes(getModel().vertexSet());
				getApplication().getModelPanel().positionNodes(getModel().vertexSet());
				this.setObservations(getModel().vertexSet());
				SaveBoundController.toBeSavedWithSaveAs.setValue(false);
				//**************** AGGIORNAMENTO DEI WARNING **********************
				try {
					this.controlErrorinGraph(getValidator().ValidateUserAction(null,null,getModel(),
							true,//1
							true,//2
							true,//3
							true,//4
							true,//5
							true,//6
							true,//7
							true,//8
							true,//9
							true,//10
							true,//11
							true,//12
							true//13
							));
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ParametersRequiredMismatchException e1) {
					e1.printStackTrace();
				}
			}
		}
	this.setIdle();
	}

}