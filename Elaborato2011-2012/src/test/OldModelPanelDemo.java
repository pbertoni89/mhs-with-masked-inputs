package test;

import graph.Model;
import graph.comp.ActionNode;
import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;
import graph.comp.OutputNode;
import inputstream.FileObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;

/**
 * @author   P Bertoni M Bono M Maghella
 * @version   1.0
 * @since   Dic 21, 2011
 */

@SuppressWarnings("unused")  // ha effetto su TUTTA la classe!
public final class OldModelPanelDemo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 600, 500 );
    private static final String SAVEPATH = "saveNewFrame.grph";

    private static final String ACTIONNODE = "ActionNode";
    private static final String INPUTNODE = "InputNode";
	private static final String OUTPUTNODE = "OutputNode";
    
	private JGraphModelAdapter<Node,Flow> jgraphAdapter;
	private JGraph jgraph;
	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	private Model model;
	
	private FileObject loadSaveHandler;
	
//________________________________________________________________________________________________________________    
    
	public OldModelPanelDemo(){
		//sbagliato, stiamo spostando in GUIApplication
		model = new Model(Flow.class);
		loadSaveHandler = new FileObject(SAVEPATH);
		
        createBasicGraph();
        saveNload();
        Set<Node> nodeSet = model.vertexSet();
        
        //FIXME ISTRUZIONE COLPEVOLE DELL'ERRORE 3 IN SALVATAGGIO
        jgraphAdapter = new JGraphModelAdapter<Node,Flow> ( model );
        jgraph = new JGraph( jgraphAdapter );
       
        // dipendono da istruzione colpevole
        this.add(jgraph);
        //tolte da max perch� lui le riteneva inutili. ha agigunto this.add(jgraph)
        //adjustDisplaySettings( jgraph );
        //getContentPane().add( jgraph );
        
    	randomNodePlacing( model );
        colorNodes( nodeSet );
        
        printPosition(nodeSet);

        //saveNload();
        //da eliminare appena inserito in contesto GUIApplication
        this.setSize(500,400);
	}

//_________________________________________________________________________________________________________________
	
	public void saveNload() {

		loadSaveHandler.SaveObject(model);
    	
    	System.out.println("Salvo: "+ loadSaveHandler.getFileError() );
        System.out.println("stampo grafo: "+ model.toString() );
    	        
        /*Scheme loadedScheme = (Scheme) loadSaveHandler.LoadObject();
        System.out.println("Carico: "+ loadSaveHandler.getFileError() );
        
        if (loadedScheme != null)
        	System.out.println("stampo nuovo grafo: "+ loadedScheme.toString() );*/
	}
	
//_________________________________________________________________________________________________________________	
    
    private void createBasicGraph() {
    	InputNode  i1 = new InputNode("ingr1");
    	ActionNode a1 = new ActionNode("act1");
    	ActionNode a2 = new ActionNode("act2");
    	OutputNode o1 = new OutputNode("out1");
    	Node genAct  = createNode("gen", "ActionNode" );
    	
    	model.addVertex( i1 );
    	model.addVertex( a1 );
    	model.addVertex( a2 );
    	model.addVertex( o1 );
    	model.addVertex( genAct );
			
    	model.addEdge(i1, a1);
    	//exp
    	Flow eja = model.getEdge(i1, a1);
    	
    }
 
//_________________________________________________________________________________________________________________
   //FIXME STA PER ESSERE DEPRECATA
    /** Inserisce il nodo nuovo nel modello, con le propriet� passategli.
     * 
     * @param name � il nominativo del nuovo nodo
     * @param type � il tipo di nodo inserito.
     */
    public Node createNode( String name, String type) {
    	
    	Node n;
    	
    	if (type.equals(ACTIONNODE) )
    		 n = new ActionNode(name);
    	else
    		if (type.equals(INPUTNODE) )
    			 n = new InputNode(name);
    		else
    			 n = new OutputNode(name);
    
    	//System.out.println("sono il tipo: "+ n.getNodeType());
    	return n;
    	
    }   
    
//_________________________________________________________________________________________________________________
   
    private void adjustDisplaySettings( JGraph jg ) {
    	
        jg.setPreferredSize( DEFAULT_SIZE );

        Color c = DEFAULT_BG_COLOR;
        jg.setBackground( c );
        
    }
 
//_________________________________________________________________________________________________________________
    
    public void colorNodes( Set <Node> nodes ) {
    	
    	Node temp;
    	
		Iterator<Node> iterator = nodes.iterator();
		
		while ( iterator.hasNext() )
		 {
			temp = iterator.next();
			
			DefaultGraphCell cell = jgraphAdapter.getVertexCell( temp );
	        Map<?, ?>        attr = cell.getAttributes(  );
       	
	        GraphConstants.setBackground(attr, temp.getColor() );
	        GraphConstants.setForeground(attr, Color.black );
	       
	        Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
	        
	        cellAttr.put( cell, attr );
	        jgraphAdapter.edit( cellAttr, null, null, null );
			  
		  }	
    }
    
//_________________________________________________________________________________________________________________    
   
    public Collection<Node> getNextVertices() {
	   
/*   Set edgesVicini = g.edgesOf(provaInt);
        	int nVicini = edgesVicini.size();
        	System.out.println("Il vertice " + provaInt + " ha " + nVicini + " vertici adiacenti.");
        	        	{
        		// investigare sugli elementi del set 
        		// ottenere source e target di ogni elemento
        		// se provaInt � target prendere source, e viceversa
        		// return: ho trovato tutti gli adiacenti di provaInt !!!
      
*/	   
	   return null;
   }
   
//_________________________________________________________________________________________________________________

    public void randomNodePlacing(Model g) {
	 
	   Set<Node> nodes = g.vertexSet();
	   Node temp;
	   Iterator<Node> iterator = nodes.iterator();
		
	   while ( iterator.hasNext() ) {
			
			temp = iterator.next();
			int x = (int) Math.round( Math.random()*DEFAULT_SIZE.getWidth()/2 );
			int y = (int) Math.round( Math.random()*DEFAULT_SIZE.getHeight()/2 );
			positionNodeAt( temp, x, y );
	   }		
   }
   
 //_________________________________________________________________________________________________________________
    
   public void positionNodeAt( Node node, int x, int y ) {
   	
	   Rectangle2D.Double rect = new Rectangle2D.Double();
	   
       DefaultGraphCell cell = jgraphAdapter.getVertexCell( node );
       Map<?, ?>        attr = cell.getAttributes(  );
       
       rect = (Double) GraphConstants.getBounds( attr );
       
       GraphConstants.setBounds( attr, new Rectangle2D.Double( x, y, rect.width, rect.height ) );
       
       Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
       
       cellAttr.put( cell, attr );
       jgraphAdapter.edit( cellAttr, null, null, null );
         
   }

 //_________________________________________________________________________________________________________________   
   
   public void printPosition(Set <Node> nodes) {
   		Node temp;
		Iterator<Node> iterator = nodes.iterator();
		
		while ( iterator.hasNext() )
		 {
			temp = iterator.next();
			
		   Rectangle2D.Double rect = new Rectangle2D.Double();
		   
	       DefaultGraphCell cell = jgraphAdapter.getVertexCell( temp );
	       Map<?, ?>        attr = cell.getAttributes(  );
	       rect = (Double) GraphConstants.getBounds( attr );       
	       
	       System.out.println("nodo "+temp.getName() +"| x = "+ rect.x + "; y = "+ rect.y);
		 }
    
   }
   
   
} // end of class