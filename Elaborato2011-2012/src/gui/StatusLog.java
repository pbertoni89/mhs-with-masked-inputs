package gui;

import java.awt.Color;
import javax.swing.JLabel;

import toolkit.modelstateevent.ModelStateEvent;
import toolkit.modelstateevent.ModelStateListener;

/**
 * DA DOCUMENTARE! L'obbiettivo del LogPanel � quello di riprodurre graficamente lo stato del grafo
 * @author     koldar
 * @version     1.0
 * @uml.dependency   supplier="toolkit.modelstateevent.ModelStateEvent"
 */
public class StatusLog extends JLabel implements ModelStateListener{

	private static final long serialVersionUID = -6650510060023724643L;
	
	/**costruisce un nuovo Log Panel con nessuna scritta al suo interno
	 * 
	 */
	public StatusLog(){
		this(Color.white,"");
	}
	
	public StatusLog(Color _bkgcolor){
		this(_bkgcolor,"");
	}
	public StatusLog(Color _bkgcolor,String initialstring){
		super(initialstring,JLabel.CENTER);
		this.setBackground(_bkgcolor);
	}
	public StatusLog(String initialString){
		this(Color.white,initialString);
	}
	/**imposta una nuova stringa all'interno del contenitore e un nuovo colore di sfondo
	 * 
	 * @param str la stringa da inserire al centro del contenitore
	 * @param color il colore da usare come sfondo
	 */
	protected void setState(String str,Color color){
		this.setText(str);
		this.setBackground(color);
	}

	@Override
	public void ModelStateChanged(ModelStateEvent event) {
		this.setState(event.getToState().getStatus(),event.getToState().getStatusColor());
	} 
	
}
