package controller.menubar;

import graph.comp.Node;
import inputstream.FileObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

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
public class SaveFileController extends MenuItemHandlerController implements SaveBoundController{

	public SaveFileController(){
		super(IDCONTROLLER_SAVEFILE);
	}
	
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
		if (SaveBoundController.toBeSavedWithSaveAs.isValue()){
			((ActionListener)controllerList.get(IDCONTROLLER_SAVEASFILE)).actionPerformed(e);
		}else{
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

	@Override
	public void executeValidatedAction() {
	}

}
