package toolkit;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**rappresenta il filtro che viene utilizzato per selezionare i particolari
 * file .{@value #EXTENSION} dal file system locale
 * 
 * @author Koldar
 * @version 1.0
 * @see JFileChooser
 */
public final class SaveGraphFilter extends FileFilter{

	/**il valore dell'estensione con cui il nostro software salva i propri grafi*/
	private static final String EXTENSION="grph";
	
	/**accetta solo i file che:
	 * <ul>
	 *  <li>sono directory</li>
	 *  <li>terminano con ".{@value #EXTENSION}" (cioe' hanno come estensione quella specificata in {@link #EXTENSION})</li>
	 * </ul> 
	 */
	@Override
	public boolean accept(File f) {
		return (f.isDirectory())||((f.isFile())&&(f.getName().endsWith("."+EXTENSION)));
	}

	@Override
	public String getDescription() {
		return EXTENSION+" extension";
	}
	/**dato il nome che l'utente inserisce nel JFIleChooser ritorna
	 * lo stesso nome con in piu' il punto e l'estensione
	 * 
	 * @param filename il nome del file che serve per creare il nome del file con compresa l'estensione
	 * @return il nome del file comprensiva l'estensione
	 */
	public static final String getSaveableName(String filename){
		return (filename.endsWith("."+EXTENSION)?filename:filename+"."+EXTENSION);
	}

}
