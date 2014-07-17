package gui;

import graph.comp.Diagnosticable;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FinalDiagnosisDialog extends JDialog {
	
/**E' la finestra di dialogo dove viene presentato il risultato dell'analisi richiesta dall'utente.
 * @author michael
 */
	
	private static final long serialVersionUID = 2824796584195396595L;
	private static final Dimension DEFAULTMINIMUMSIZE = new Dimension(200,100);
	private static final Dimension DEFAULTSIZE = new Dimension(200,400);
	private JPanel panel;
	private JScrollPane scrollPane;
	
	public static final GridBagLayout layout = new GridBagLayout();
	public static final GridBagConstraints lim = new GridBagConstraints();
	
	public FinalDiagnosisDialog(JFrame frame, String myMessage, Collection<Set<Diagnosticable>> setOfDiagnosis){
		/** Il dialog sar� NON modale, poich� non ho nessun bisogno di fermare l'azione utente.*/
		super(frame,false);
		//setto la grandezza del frame
		this.setMinimumSize(DEFAULTMINIMUMSIZE);
		this.setSize(DEFAULTSIZE);
		this.setTitle(myMessage);

		//creo il pannello dove stampare le label associandogli uno scrollpanel
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(layout);
		scrollPane = new JScrollPane(panel);
		this.add(scrollPane);
		
	    //setto alcuni parametri del GridBagConstrains, gridy verr� poi incrementato per stampare le etichette una sotto l'altra
		this.setGridBagConstraints(0, 0, 0, 0, GridBagConstraints.BOTH, 1, 1);

		
		//itero su tutte le analisi in ingresso e ne stampo "il nome"
		Iterator<Set<Diagnosticable>> diagnosisIt =  setOfDiagnosis.iterator();
		while(diagnosisIt.hasNext()) {
			JLabel lab = new JLabel(lim.gridy+1 +") "+diagnosisIt.next().toString());
			layout.setConstraints(lab, lim);
			panel.add(lab);
			lim.gridy++;
			}
		this.setVisible(true);
	}
	
	/**
	 * metodo che inserisce il bottone passato come parametro nell'EditModelButtonPanel, ridimensionandolo secondo i valori passati
	 * 
	 * @param nome nome dell'etichetta nel pulsante
	 * @param weightx parmetro del GridbagConstrains
	 * @param weighty parmetro del GridbagConstrains
	 * @param gridx parmetro del GridbagConstrains
	 * @param gridy parmetro del GridbagConstrains
	 * @param gridBagConstraintsdim parametro per dimensionare il componente all'interno dell'oggetto grafico in cui si trova
	 * @param color Colore dell'etichetta
	 * @param button bottone da aggiungere
	 * @param layout 
	 */
	public void setGridBagConstraints(double weightx, double weighty, 
									  int gridx, int gridy, 
									  int GridBagConstraintsresize, 
									  int gridwidth , int gridheight) {
		lim.weightx=weightx;
		lim.weighty=weighty;
		lim.gridx=gridx;
		lim.gridy=gridy;
		lim.fill= GridBagConstraintsresize;
		lim. gridwidth= gridwidth;
		lim.gridheight= gridheight;
	}

	/**
	 * Aggiunge il componente passato nlla GuiApplication dimensionandolo secondo il GridBagConstraints passato come parametro
	 * @param component
	 * @param gridBagConstraints
	 */
	public void add(Component component, GridBagConstraints gridBagConstraints) {
		layout.setConstraints(component, gridBagConstraints);
		panel.add(component);
	}
}