package controller.menubar;

import graph.comp.Node;
import inputstream.FileObject;

import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.JFileChooser;

import toolkit.SaveGraphFilter;

import controller.MenuItemHandlerController;
import controller.NullStaticObjectException;
import controller.SaveBoundController;

/**gestisce le azioni che devono essere fatte quando l'utente preme il pulsante
 * sul menu' che denomina l'uscita dal software
 * 
 * @author Koldar
 * @version 1.0
 * @see ApplicationBoundController
 */
public class SaveAsFileController extends MenuItemHandlerController implements SaveBoundController{
	
	public SaveAsFileController(){
		super(IDCONTROLLER_SAVEASFILE);
	}
	
	/**in caso in cui il file non sia ancora stato salvato, apre una finestra di selezione
	 * di luogo in cui salvare il grafo; altrimenti salva il file velocemente
	 * 
	 */
	//TODO dire che quando uno preme "save as..." si crea sempre un nuovo file e si cambia FileObject
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		int saveresult=SaveBoundController.filechooser.showSaveDialog(getApplication());
		if (saveresult==JFileChooser.APPROVE_OPTION){
			saveHandler.setPath(SaveGraphFilter.getSaveableName(filechooser.getSelectedFile().getAbsolutePath()));
			//salvataggio delle posizioni dei nodi
			Iterator<Node> iterator = getModel().vertexSet().iterator();
			while(iterator.hasNext()){
				Node toBeSaved = iterator.next();
			    Rectangle2D.Double dimensions= (Rectangle2D.Double)getApplication().getModelPanel().getJgraphAdapter().getVertexCell( toBeSaved).getAttributes().get("bounds");
			    toBeSaved.setGraphicRect(dimensions);
			}
			saveHandler.SaveObject(getModel().clone());
			int error=saveHandler.getFileError();
			log.reprintln();
			this.printOnLogWarningList();
			if (error!=FileObject.NULL){
				log.println("errore avvenuto! codice errore #"+error);
			}else{
				log.println("salvataggio eseguito con successo");
				SaveBoundController.toBeSavedWithSaveAs.setValue(false);
			}
		}
		this.setIdle();
	}

}
