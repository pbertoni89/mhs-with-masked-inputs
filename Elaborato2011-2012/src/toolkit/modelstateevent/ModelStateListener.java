package toolkit.modelstateevent;

import java.io.Serializable;
import java.util.EventListener;

/**rappresenta l'intenzione di una classe di ricevere notifiche
 * circa il cambio di stato di un certo Model
 * 
 * @author Koldar
 * @version 1.0
 */
public interface ModelStateListener extends EventListener,Serializable{

	/**indica la lista di istruzioni da eseguire quando lo stato di
	 * un certo Model ascoltato viene modificato
	 * 
	 * @param event l'evento contenente le informazioni circa il cambio di stato
	 */
	public void ModelStateChanged(ModelStateEvent event);
}
