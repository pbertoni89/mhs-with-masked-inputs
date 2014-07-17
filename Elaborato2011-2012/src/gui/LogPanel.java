package gui;


import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import event.numberevent.NumberEvent;
import event.stringevent.StringEvent;
import event.stringevent.StringListener;
import graph.state.CorrectModelState;
import graph.state.WarningModelState;

import outputstream.StringOutputStream;
import toolkit.StringPrintStream;
import toolkit.modelstateevent.ModelStateEvent;
import toolkit.modelstateevent.ModelStateListener;

/**Rappresenta l'oggetto con cui il software comunica con l'utente.
 * 
 * @author koldar
 * @version 1.2
/**
 * Rappresenta l'oggetto con cui il software comunica con l'utente.
 * @author   koldar
 * @version   1.1
 */
//TODO mettere le informazioni del Log Panel in centro e non nell'angolo  a sx che e' brutto!

public class LogPanel extends JPanel implements StringListener{

	private static final long serialVersionUID = -6650510060023724643L;
	/**
	 * rappresenta l'icona da mostrare a sinistra del pannello
	 * @uml.property  name="icon"
	 */
	private JLabel icon;
	/**rappresenta il componente contenente il testo da visualizzare*/
	private JTextArea label;
	private JScrollPane scrolllabel;
	/**
	 * rappresenta lo stream dati su cui si possono scrivere gli errori
	 * @uml.property  name="out"
	 * @uml.associationEnd  
	 */
	public StringPrintStream out;
	/**identifica lo stream particolare per rilevare variazioni della stringa out*/
	private StringOutputStream stream;
	
	/**costruisce un nuovo Log Panel con nessuna scritta al suo interno
	 * 
	 */
	public LogPanel(){
		this("");
	}
	/**costruisce un nuovo LogPanel con struttura di questo tipo:
	 * <p><img src="logpanel.png"/>
	 * <p>la figura viene impostata per default a sinistra del testo, mentre il testo
	 * pue' essere utilizzato per interagire con l'utente
	 * 
	 * @param img l'immagine da utilizzare
	 * @param initialString la stirnga di testo iniziale
	 */
	public LogPanel(ImageIcon img,String initialString){
		super();
		this.setLayout(new BorderLayout());
		
		this.icon=new JLabel(img);
		this.label=new JTextArea(initialString);
		this.label.setEditable(false);
		this.label.setOpaque(false);
	
		this.scrolllabel=new JScrollPane(this.label);
		this.stream=new StringOutputStream();
		this.out=new StringPrintStream(this.stream);
		this.stream.getListenedStream().addStringListener(this);
		
		this.add(scrolllabel,BorderLayout.CENTER);
		this.add(icon,BorderLayout.WEST);
	}
	public LogPanel(String imgpath,String initialString){
		this(new ImageIcon(LogPanel.class.getResource(imgpath)),initialString);
	}
	/**costruisce un nuovo Log Panel con su scritto un messaggio iniziale
	 * 
	 * @param initialString il messaggio da visualizzare all'inizio
	 */
	public LogPanel(String initialString){
		this((ImageIcon)null,initialString);
	}
	
	@Override
	public void valueChanged(StringEvent event) {
		this.label.setText(event.getNewvalue());
	}
	/**imposta la nuova icona da inserire a sinistra del LogPanel
	 * 
	 * @param icon la nuova icona da impostare
	 */
	public void setIcon(ImageIcon icon){
		this.icon.setIcon(icon);
	}
	/**
	 * @return   l'icona corrente a sinistra del pannello in formato ImageIcon.  Tale metodo non fa altro che eseguire il metodo getIcon() di JLabel
	 * @uml.property  name="icon"
	 */
	public ImageIcon getIcon(){
		return (ImageIcon)this.icon.getIcon();
	}

}
