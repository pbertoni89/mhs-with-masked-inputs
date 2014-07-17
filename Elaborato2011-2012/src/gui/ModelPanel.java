package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.util.*;

import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ext.JGraphModelAdapter;

import graph.Model;
import graph.comp.Flow;
import graph.comp.Node;

/**
 * Model panel è il pannello che contiene la visualizzazione grafica del modello e gestisce metodi per gestire graficamente i nodi che lo costituiscono.
 * @author P Bertoni M Bono M Maghella
 * @version 1.0
 *
 * @since Dic 21, 2011
 */

public final class ModelPanel extends JPanel  implements MouseWheelListener{
	
	private static final long serialVersionUID = 1L;
	private static final Color DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );

	private JGraphModelAdapter<Node,Flow> jgraphAdapter;
	private JGraph jgraph;
	/**rappresenta lo stato che indica a quale zoom di rotella (se presente) il grafo si trova.
	 * Tale valore non e' lo zoom del grafo, ma solo il valore assoluto di rotazione della rotella*/
	private double currentZoom;

    private static final Dimension DEFAULT_SIZE = new Dimension( 600, 500 );
    /**rappresenta la base dell'esponenziale che viene usato per impostare il nuovo zoom di {@link #jgraph} quando
     * la rotella viene mossa. l'argomento dell'esponenziale e' il movimento della rotella 
     */
    private static final double ZOOM_BASE=3;
	
//________________________________________________________________________________________________________________    
    
	/**
	 * @return the jgraph
	 */
	public JGraph getJgraph() {
		return jgraph;
	}
	/**
	 * @param jgraph the jgraph to set
	 */
	public void setJgraph(JGraph jgraph) {
		this.jgraph = jgraph;
	}
    /**
	 * @return the jgraphAdapter
	 */
	public JGraphModelAdapter<Node, Flow> getJgraphAdapter() {
		return jgraphAdapter;
	}
	/**
	 * @param jgraphAdapter the jgraphAdapter to set
	 */
	public void setJgraphAdapter(JGraphModelAdapter<Node, Flow> jgraphAdapter) {
		this.jgraphAdapter = jgraphAdapter;
	}
	
	public ModelPanel( Model model ){
		
		super();
	 
        this.setLayout(new GridLayout());
        
        jgraphAdapter = new JGraphModelAdapter<Node,Flow> ( model );
        jgraph = new JGraph( jgraphAdapter );
        this.currentZoom=0;
       
        //customization
        jgraph.setBendable(false); //setta se gli edge sono segmentabili tramite click dx
        jgraph.setConnectable(false); //default false
        jgraph.setDisconnectable(false); //ottimo!!! i flows non sono piï¿½ disconnettabili
        jgraph.setDisconnectOnMove(false); //per sicurezza
        jgraph.setEdgeLabelsMovable(false); //banale
        jgraph.setEditable(false); //non permette di rinominare i vertex <3
        jgraph.setBackground( DEFAULT_BG_COLOR );
        //end of customization
        this.jgraph.addMouseWheelListener(this);
        this.add(jgraph);
	}

	/**dato il nodo corrente, applica il colore tipico del nodo e lo posiziona secondo
	 * il valore bounds. se bounds ï¿½ nullo, allora il metodo non effettua alcun riposizionamento
	 * 
	 * @param node il nodo da modificare
	 * @param bound il rettangolo che il nodo deve occupare
	 */
	public void ApplyAttributeToNode(Node node,Rectangle bound){
		//TODO c'ï¿½ un modo piï¿½ veloce per accedere alla mappa... trasmite per esempio il comando applyvalue!
		DefaultGraphCell cell = jgraphAdapter.getVertexCell( node );
        Map<?, ?>        attr = cell.getAttributes(  );
   	
        GraphConstants.setBackground(attr, node.getColor() );
        GraphConstants.setBounds(attr,bound);
        
        GraphConstants.setForeground(attr, Color.black );  // COLORE DELLA LABEL, SOSTANZIALMENTE
       
        Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
        
        cellAttr.put( cell, attr );
        jgraphAdapter.edit( cellAttr, null, null, null );
	}
	
//_________________________________________________________________________________________________________________
    /** Viene chiamato da ciascuno dei tre Inser[NODE]Controller, per colorare il nodo nuovo in base al tipo.
     *  Appartiene sicuramente a ModelPanel, perchï¿½ dialoga con jgraph e jgraphadapter. 
	*/
	public void colorNode( Node nodino ) {
		
		DefaultGraphCell cell = jgraphAdapter.getVertexCell( nodino );
        Map<?, ?>        attr = cell.getAttributes(  );
   	
        GraphConstants.setBackground(attr, nodino.getColor() );
        //GraphConstants.setBounds(attr, arg1);
        
        GraphConstants.setForeground(attr, Color.black );  // COLORE DELLA LABEL, SOSTANZIALMENTE
       
        Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
        
        cellAttr.put( cell, attr );
        jgraphAdapter.edit( cellAttr, null, null, null );
        
	}
//_________________________________________________________________________________________________________________
    /** Viene chiamato da OpenFileController, perchï¿½ si ritrova a scaricare dalla memoria un modello,
     *  composto da un insieme di nodi da colorare. */
	public void colorNodes( Set <Node> nodes ) {
    	Node temp;
		Iterator<Node> iterator = nodes.iterator();
		while ( iterator.hasNext() ) {
			temp = iterator.next();
			colorNode(temp);
		}	
    }
//_________________________________________________________________________________________________________________ 

	/**
	 * Posiziona un set di nodi all'interno del ModelPanel
	 * @param node
	 */
    public void positionNodes(Set<Node> node) {
   	 Iterator<Node> iterator = node.iterator();
   	 while(iterator.hasNext()){
   		 Node nod = iterator.next();
   		 positionNode(nod);	 
   	 }  	
    }    
  //_________________________________________________________________________________________________________________
     // non cancellarlo! potrebbe tornare utile
    /**
     * Posiziona un nodo all'interno del Modelpanel
     * @param node
     */
    public void positionNode( Node node) {
        DefaultGraphCell cell = jgraphAdapter.getVertexCell( node );
        Map<?, ?>        attr = cell.getAttributes(  );
        GraphConstants.setBounds( attr,node.getGraphicRect());
        Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
        
        cellAttr.put( cell, attr );
        jgraphAdapter.edit( cellAttr, null, null, null );  
    }
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		this.currentZoom+=arg0.getPreciseWheelRotation();
		this.jgraph.setScale(Math.pow(ZOOM_BASE,this.currentZoom));
	}
} // end of class