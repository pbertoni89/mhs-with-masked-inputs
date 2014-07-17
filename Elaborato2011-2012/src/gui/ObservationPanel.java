package gui;


import graph.comp.OutputNode;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * L'ObservationPanel è una zona della GUI dove vengono mostrati graficamente tutti i risultati dei nodi d'uscita
 * attraverso dei ResultRenderer che sono raccolti in un una lista.
 *  
 * @author michael
 */
public class ObservationPanel extends GraphicStackComponent{

	private static final long serialVersionUID = 7105714753229238545L;


	/**rappresenta la lista di oracoli contenuti all'interno di questo pannello.*/
	private List<ResultRenderer> vectorOfResultRenderers;
	public int numberOfResults;
	
	
	
	public ObservationPanel(){
		super();
		this.vectorOfResultRenderers=new Vector<ResultRenderer>(0,1);
		this.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
		
		this.setVisible(true);
	}
	
	/**il metodo crea un componente ResultRenderer e lo aggiunge al pannello. Affinché il tutto
	 * funzioni è necessario che l'oggetto ResultRenderer vada collegato ad un particolare OutputNode
	 * che  è proprio il parametro passato al metodo stesso.
	 * 
	 * @param output l'outputnode che deve essere collegato all'ResultRenderer che viene creato in questo metodo
	 * @see ResultRenderer
	 * @see #graph.comp.OutpuNode OutputNode
	 */
	public void addResultRenderer (OutputNode output){
		ResultRenderer resultRenderer = new ResultRenderer (output);
		this.vectorOfResultRenderers.add(resultRenderer);
		this.addComponentInStack(resultRenderer);
		System.out.println(lim.gridy);
		
	}
	/**
	 * Rimuove il ResultRenderer dell'OutputNode richiesto.
	 * @param output
	 */
	public void removeResultRenderer (OutputNode output){
		for (ResultRenderer resultRenderer : vectorOfResultRenderers) 
			if(resultRenderer.getOutputnode() == output)
				this.remove(resultRenderer);
		this.validate();
		this.repaint();
	}
}