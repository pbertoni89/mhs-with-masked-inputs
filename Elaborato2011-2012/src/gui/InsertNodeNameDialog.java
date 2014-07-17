package gui;

import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Set;

import graph.comp.Node;

/**
 * InsertNodeDialogue � una finestra di dialogo con l'utente per inserire il nome del Nodo appena creato
 * @author   michael
 * @version   1.1
 */
public class InsertNodeNameDialog extends JDialog implements ActionListener, KeyListener{

	private static final long serialVersionUID = -2132166491131311182L;
	/**stringa che contiene il testo in okButton */
	public final static String OK = "Ok";
	/**stringa che contiene il testo in resetButton */
	public final static String RESET = "Reset";
	/**stringa vuota*/
	private final static String BLANK = "";
	/**Dimensioni della finestra di dialogo */
	private static final Dimension DIALOG_SIZE = new Dimension(300, 125);
	/**
	 * Contenitore dove viene inserito qualsiasi elemento grafico del dialogo
	 * @uml.property  name="contenitore"
	 * @uml.associationEnd  
	 */
	private GraphicNoStackComponent contenitore = null;
	/**Textarea l'utente inserisce il nome */
	private JTextField textArea = null;
	/**Bottone di ok */
	private JButton okButton = null;
	/**elimina tutto ci� che � stato immesso nella textbox dell'utente*/
	private JButton resetButton = null;
	/**Etichetta della finestra di dialogo */
	private JLabel lab;

	// una terminazione tramite chiusura JDialog lascia ad abort il valore
	// dell'uscita.
	/**
	 * Nome inserito dall'utente alla fine del dialogo
	 * @uml.property  name="newNameOk"
	 */
	private String newNameOk;
	/**Nodeset del modello attuale */
	private Set<Node> nodeSet;


	
	/**
	 * indica alla classe padre se il dialog ha esplicato alla sua funzione (ritornare una stringa accettabile) oppure no
	 * @uml.property  name="isOk"
	 */
	private boolean isOk;
	/**JFrame al quale � legato l'InsertNodeNameDialogue */
	private JFrame frame;


	// modal = true => la finestra � "HEAVY", ovvero costringe l'utente a interagire e a terminarla prima di ritornare
	public InsertNodeNameDialog(JFrame frame, boolean modal, String myMessage,Set<Node> nodeSet) {
		super(frame, modal);
		

		resetButton = new JButton(RESET);
		lab = new JLabel(myMessage);
		textArea = new JTextField();
		okButton = new JButton(OK);
		contenitore = new GraphicNoStackComponent();
		
		//System.out.println(nodeSet.toString());  //stampa il vecchio nodeset
		
		this.newNameOk="";
		this.nodeSet = nodeSet;
		this.frame = frame;
		this.isOk=false;
		this.setMinimumSize(DIALOG_SIZE);

		
		getContentPane().add(contenitore);
		
			
		//aggiungo l'etichetta etichetta che chiede di inserire il nome
		contenitore.setGridBagConstraints(1, 0.3, 0, 0, GridBagConstraints.BOTH, 1, 1);
		contenitore.add(lab, contenitore.lim);

		//ggiungo il text fireld
	
		textArea.setVisible(true);
		textArea.setFocusable(true);
		textArea.addKeyListener(this);
		contenitore.setGridBagConstraints(1, 1, 0, 1, GridBagConstraints.HORIZONTAL, 2, 1);
		contenitore.add(textArea, contenitore.lim);

		//aggiungo il bottone di OK
		
		okButton.addActionListener(this);
		contenitore.setGridBagConstraints(1, 1, 0, 2, GridBagConstraints.NONE, 1, 1);
		contenitore.add(okButton, contenitore.lim);
	
		//aggiungo il bottono di reset
		resetButton.addActionListener(this);
		contenitore.setGridBagConstraints(1, 1, 1, 2, GridBagConstraints.NONE, 1, 1);
		contenitore.add(resetButton, contenitore.lim);

		//setto il dialogue visibile
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	/**
	 * metodo che gestisce i click dei pulsanti OK o RESET
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource()==this.okButton)&&(!textArea.getText().equals(BLANK))){
			// gestione univocit� nome
			newNameOk = textArea.getText();
			if (checkUniqueName()) {
				this.isOk=true;
				setVisible(false);
			} else {// se il nome � gi� esistente
				new ErrorOutPut(frame, true);
				textArea.setText(BLANK);
			}
		}
		if (resetButton == e.getSource()) {
			textArea.setText(BLANK);
		}

	}

	/**
	 * Metodo che controlla l'univocit� del nome del nodo inserito all'interno del modello.
	 * @return true se non si verifica l'unicit� del nome, altrimenti false
	 */
	public boolean checkUniqueName() {
		Node temp;
		Iterator<Node> iterator = nodeSet.iterator();
		
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getName().equals(newNameOk))
				return false;
		}
		return true;

	}

	/**
	 * Shortcut che mermette di premere Enter invece che il pulsante OK
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
			//TODO mettere una costante privata per la stringa OK!
			this.actionPerformed(new ActionEvent(this.okButton,ActionEvent.ACTION_PERFORMED,"Ok"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * @return   the isOk
	 * @uml.property  name="isOk"
	 */
	public boolean isOk() {
		return isOk;
	}

	/**
	 * @param isOk   the isOk to set
	 * @uml.property  name="isOk"
	 */
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	

	/**
	 * @return
	 * @uml.property  name="newNameOk"
	 */
	public String getNewNameOk() {
		return newNameOk;
	}
	

}