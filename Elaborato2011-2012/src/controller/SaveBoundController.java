package controller;

import javax.swing.JFileChooser;

import toolkit.BooleanValue;
import toolkit.JGraphChooser;
import inputstream.FileObject;

/**rappresenta un controller che e' legato (in qualche modo) alla gestione del grafo corrente.
 * tutti i controller che implmentano questa classe astratta saranno forniti di un attributo boolean
 * {@link #toBeSavedWithSaveAs} nonche' di un {@link #saveHandler} utilizzato per il salvataggio.
 * Un Controller estendente SaveBoundController e' in grado quindi di salvare e caricare grafi
 * da file system precedentemente creati.
 * 
 * @author koldar
 * @version 1.1
 * @see Controller
 *
 */
public interface SaveBoundController{

	/**indica se il file da salvare deve essere salvato tramite la procedura Save piuttosto che con quella
	 * di SaveAs. Per costruzione un file deve essere salvato tramite SaveAs quando:
	 * <ul>
	 *  <li>e'appena stato creato</li>
	 *  <li>l'utente specifica chiaramente che vuole creare un nuovo file</li>
	 * </ul>
	 * Invece un file deve essere salvato tramite Save quando:
	 * <ul>
	 *  <li>l'utente ha modificato un file gia' esistente</li>
	 * </ul>
	 */
	public static BooleanValue toBeSavedWithSaveAs=new BooleanValue(true);
	/**rappresenta la struttura dati che assiste il salvataggio e il caricamento dei file graph*/
	public static FileObject saveHandler=new FileObject();
	/**rappresenta il dialog che consente una navigazione facile e veloce nel file system per ricercare i file graph*/
	public static JFileChooser filechooser=new JGraphChooser();
}
