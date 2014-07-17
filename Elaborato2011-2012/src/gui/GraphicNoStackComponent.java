package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
/**
 * Un pannello in cui gli elementi all'interno possono essere collocati a piacere, non come in uno stack.
 * @author michael
 *
 */

public class GraphicNoStackComponent extends GraphicComponent{
	
	private static final long serialVersionUID = -2593159512061467524L;

	public GraphicNoStackComponent()
    {
    	this.setLayout(layout);
    }

	/**
	 * Aggiunge il componente passato dimensionandolo secondo il GridBagConstraints passato come parametro
	 * @param component componente aggiunto
	 * @param gridBagConstraints 
	 */
	public void add(Component component, GridBagConstraints gridBagConstraints)
	{
		layout.setConstraints(component, gridBagConstraints);
		this.add(component);
		
	}

}
