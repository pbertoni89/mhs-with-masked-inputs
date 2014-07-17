package gui;

	// Fri Oct 25 18:07:43 EST 2004
	//
	// Written by Sean R. Owens, sean at guild dot net, released to the
	// public domain.  Share and enjoy.  Since some people argue that it is
	// impossible to release software to the public domain, you are also free
	// to use this code under any version of the GPL, LPGL, or BSD licenses,
	// or contact me for use of another license.
	// http://darksleep.com/player

	// A very simple custom dialog that takes a string as a parameter,
	// displays it in a JLabel, along with two Jbuttons, one labeled Yes,
	// and one labeled No, and waits for the user to click one of them.

import javax.swing.JDialog; 
/**
 * ErrorOutPut � una finestra di errore che segnala all'utente che esiste gi� un nodo con lo stesso nome
 * 
 *  * @author michael
 */

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;


import java.awt.event.ActionEvent;
/**
 * Finestra di dialogo che informa l'utente di un errore nell'inserzione del nome del nodo.
 * @author michael and patrizio
 *
 */

	public class ErrorOutPut extends JDialog implements ActionListener {
		
		private static final long serialVersionUID = -8673888821543157070L;

		/**
		 * Panel all'interno del quale vengono inseriti tutti gli oggetti grafici dell'ErrorOutPut
		 * @uml.property  name="contenitore"
		 * @uml.associationEnd  
		 */
		private GraphicNoStackComponent contenitore;
		
		/** Bottone di ok*/
	    private final static String OK = "Ok";
	    
	    /** Stringa che avvisa del fatto che � stato scelto un nome gi� esistente per un altro nodo*/
	    private static final  String ERROR = "The chosen name already exists, insert a new name";
	    
	    /** etichetta del messaggio di errore*/
	    private JLabel lab; 
	    
	    // una terminazione tramite chiusura JDialog lascia ad abort il valore dell'uscita.
	    /**Bottone di OK */
		public static JButton okButton;
	    
	    
	    public ErrorOutPut(JFrame frame, boolean modal) {
			
	    	super(frame, modal);
	 
	    	
	    	lab = new JLabel(ERROR);
			okButton = new JButton(OK);
			contenitore= new GraphicNoStackComponent();
	    	
		    //creazione della panel che poi conterr� tutti gli oggetti grafici
			contenitore.setSize(200, 200);
			getContentPane().add(contenitore);
			
			
			//aggiungo la label
			contenitore.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
			contenitore.add(lab, contenitore.lim);

			//aggiunta del button OK
			okButton.addActionListener(this);
			contenitore.setGridBagConstraints(1, 0.5, 0, 1, GridBagConstraints.NONE, 1, 1);
			contenitore.add(okButton,contenitore.lim);	
			
				
			
			pack();
			setLocationRelativeTo(frame);
			setVisible(true);
			
	    }

	    
	    /**
	     * Chiude la finestra in caso l'evento si riferisca all'okButton
	     */
	    public void actionPerformed(ActionEvent e) {
	    	
	    if (okButton == e.getSource())	    
	    setVisible(false);		
	    }
	    
	    
	}
	    
