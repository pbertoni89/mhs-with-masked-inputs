package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import controller.Controller;
/**
 * Componente grafico generico che viene utilizzato all'interno della GuiApplication.
 * Il suo utilizzo permette di facilitare il settaggio del GridBagConstrains.
 * @author michael
 *
 */

abstract class GraphicComponent extends JPanel{

	private static final long serialVersionUID = 1L;
	protected GridBagLayout layout= new GridBagLayout () ;
	protected GridBagConstraints lim=new GridBagConstraints() ;
	
	
	public GraphicComponent(Controller ...controller)
	{	
	}
	
	  /**setta il GridBagConstraints dell'oggetto secondo i parametri passati 
     * 
     * @param weightx
     * @param weighty
     * @param gridx
     * @param gridy
     * @param GridBagConstraintsresize  parametro per dimensionare il componente all'interno dell'oggetto grafico in cui si trova
     * @param gridwidth
     * @param gridheight
     */
	
    public void setGridBagConstraints(double weightx, double weighty , 
    								  int gridx , int gridy ,
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

}
