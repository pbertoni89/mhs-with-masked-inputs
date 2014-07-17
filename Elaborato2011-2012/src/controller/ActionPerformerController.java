package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**rappresenta un Controller che ascolta un ActionEvent dispatcher e
 * dunque possiede deve possedere il metodo <tt>ActionPerformed(ActionEvent)</tt>.
 * Questa interfaccia offre il metodo {@link #actionOverseenPerformed(ActionEvent)}, essenziale
 * per eseguire una lista di istruzioni che possono generare la NullStaticObjectException
 * 
 * @author Koldar
 * @version 1.0
 */
public interface ActionPerformerController extends ActionListener{

	/**rappresenta la lista di azioni che il software deve eseguire quando
	 * il pulsante viene premuto. il metodo ha una forte relazione con {@link #actionPerformed(ActionEvent)}.
	 * <p>Quando il pulsante viene premuto il software richiama il metodo {@link #actionPerformed(ActionEvent)};
	 * tale metodo richiama {@link #actionOverseenPerformed(ActionEvent)} e, in caso in cui esso lanci
	 * un <tt>NullStaticObjectException</tt>, avverte la console.
	 * 
	 * @param e l'evento che ingloba le informazioni base della azione pulsante sul JButton
	 * @throws NullStaticObjectException in caso in cui si acceda ad un attributo del controller statico nullo
	 */
	public void actionOverseenPerformed(ActionEvent arg0) throws NullStaticObjectException;
	
}
