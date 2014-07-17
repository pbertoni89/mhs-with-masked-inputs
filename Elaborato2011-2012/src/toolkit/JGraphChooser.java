package toolkit;

import javax.swing.JFileChooser;

/**rappresenta un JFileChooser ideato appositamente per
 * rilevare nel file system i file di tipo graph del nostro software.
 * Per ulteriori informazioni riguardo a come questo chooser
 * filtra i file, consultare SaveGraphFilter
 *  
 * @author Koldar
 * @version 1.0
 * @see SaveGraphFilter
 */
public class JGraphChooser extends JFileChooser{

	private static final long serialVersionUID = 9144595878985429734L;
	/**costurisce un nuovo JFIleChooser filtrante i file specificati
	 * da SaveGraphFilter
	 */
	public JGraphChooser(){
		super();
		this.setFileFilter(new SaveGraphFilter());
	}

}
