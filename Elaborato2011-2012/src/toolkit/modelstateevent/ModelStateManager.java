package toolkit.modelstateevent;

import java.io.Serializable;
import java.util.Vector;

public class ModelStateManager implements Serializable{

	private static final long serialVersionUID = -3125093010614991367L;

	private Vector<ModelStateListener> listener;
	
	public ModelStateManager(){
		super();
		this.listener=new Vector<ModelStateListener>(0,1);
	}
	public int size(){
		return this.listener.size();
	}
	
	public void removeAllListeners(){
		this.listener.removeAllElements();
	}
	
	public void addModelStateListener(ModelStateListener e){
		this.listener.add(e);
	}
	public void removeModelStateListener(ModelStateListener e){
		this.listener.remove(e);
	}
	public void fireModelStateEvent(ModelStateEvent e){
		for (ModelStateListener listener:this.listener){
			listener.ModelStateChanged(e);
		}
	}
	
	
}
