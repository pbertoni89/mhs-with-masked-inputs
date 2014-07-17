package gui;

import java.awt.Color;
import java.awt.Component;

public class GraphicStackComponent extends GraphicComponent {
/**
 *Componente grafico nel quale gli oggetti aggiunti vengono posizionati l'uno sotto l'altro come in uno stack.
 */
	private static final long serialVersionUID = 8401051099371128404L;

	public GraphicStackComponent()
	 {
		 this.setLayout(layout);
		 layout.setConstraints(this, this.lim);
		 
	 }
	
	/**
	 * Aggiunge il componente passato come parametro nell'EditmorelButtonPanel dimensionandolo secondo il GridBagConstraints passato come parametro
	 * @param component componente aggiunto
	 * @param color colore del componente da aggiungere
	 */
	public void addComponentInStack(Component component, Color color)
	{
		layout.setConstraints(component, lim);
		component.setForeground(color);
		this.add(component);
		lim.gridy++;
		
	}
	
	public void addComponentInStack(Component component)
	{
		layout.setConstraints(component, lim);
		this.add(component);
		lim.gridy++;
		
	}
	

}
