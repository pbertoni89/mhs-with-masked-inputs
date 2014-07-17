package examples;

import inputstream.FileObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import java.util.*;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import org.jgrapht.VertexFactory;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.RandomGraphGenerator;
import org.jgrapht.generate.RandomGraphGenerator.DefaultEdgeTopologyFactory;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultEdge;

import graph.Model;
import graph.JGraphAdapter;
import graph.comp.ActionNode;
import graph.comp.Flow;
import graph.comp.InputNode;
import graph.comp.Node;
import graph.comp.OutputNode;

/**
 * La nostra prima applet di testing e sviluppo know-how di JGraph.
 * @author   P Bertoni M Bono M Maghella
 * @since   Dic 21, 2011
 */

public final class RandomGraphTest extends JApplet {
	
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 600, 500 );
    private static final String SAVEPATH = "save.grph";

    private static final String ACTIONNODE = "ActionNode";
    private static final String INPUTNODE = "InputNode";
    private static final String OUTPUTNODE = "OutputNode";
    
    private static final String ACTIONNODEDEFAULTNAME = "A";
    private static final String INPUTNODEDEFAULTNAME = "I";
    private static final String OUTPUTNODEDEFAULTNAME = "O";
    
	/**
	 * @uml.property  name="jgraphAdapter"
	 * @uml.associationEnd  
	 */
	private JGraphAdapter jgraphAdapter;
	private JGraph jgraph;
	
	private FileObject loadSaveHandler;
	
	// non in uso, commentata per il resto del programma
	/**
	 * @uml.property  name="randGraph"
	 * @uml.associationEnd  
	 */
	static Model randGraph;
	//static Graph<Node,Flow> randGraph;

//________________________________________________________________________________________________________________    
    
	public void init(  ) {
    	
        Model g = new Model(Flow.class); //createFirstGraph();
		
        //createRandomGraph();   //inizializza randGraph
		
		//AcyclicDirectedGraph<Node, Flow> g1 = randGraph;
		//Graph<Node, Flow> g1 = randGraph;
        
        jgraphAdapter = new JGraphAdapter ( g );

        jgraph = new JGraph( jgraphAdapter );
        
        loadSaveHandler = new FileObject(SAVEPATH);
              
        adjustDisplaySettings( jgraph );
        getContentPane().add( jgraph );
        resize(DEFAULT_SIZE);

    	//positionNodes(g);
     
    	loadSaveHandler.SaveObject( g.vertexSet() );
    	System.out.println("Salvo: errore"+ loadSaveHandler.getFileError() );
    	
        colorNodes(g);
        
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
        loadSaveHandler.LoadObject();
        System.out.println("Carico: errore"+ loadSaveHandler.getFileError() );
        
	}
	
//_________________________________________________________________________________________________________________	
    
    private static Model createFirstGraph() {
    	
    	Model g =
            new Model ( Flow.class );
    	
    	Node i1 = createNode("ingr1", "InputNode");
    	Node a1 = createNode("act1", "ActionNode");
    	Node a2 = createNode("act2", "ActionNode");
    	Node o1 = createNode("out1", "OutputNode");
    	
	    g.addVertex( i1 );
	    g.addVertex( a1 );
	    g.addVertex( a2 );
	    g.addVertex( o1 );
	    
	    //Flow i1_a1 = new Flow(
			
	    g.addEdge(i1, a1); 
	    
        return g;
    }
 
//_________________________________________________________________________________________________________________
    
    public void createRandomGraph() {  // NON worka at the moment
 	   
 	   int cardNodes = 5;
 	   int cardFlows = cardNodes+1;
 	   
 	   //randGraph = new AcyclicDirectedGraph<Node,Flow>(Flow.class);
 	   
        //Create the RandomGraphGenerator object
        RandomGraphGenerator<Node, Flow> randomGenerator =
            new RandomGraphGenerator<Node, Flow>  (cardNodes, cardFlows);

        //Create the VertexFactory so the generator can create vertices
        VertexFactory<Node> vFactory =
                new ClassBasedVertexFactory<Node>(Node.class);

      
        //Use the RandomGraphGenerator object to make randomGraph a
        //random graph with [size] number of vertices
         randomGenerator.generateGraph( randGraph, vFactory, null);
      
    }    
    
//_________________________________________________________________________________________________________________
    /** Inserisce il nodo nuovo nel modello, con le propriet� passategli.
     * 
     * @param name � il nominativo del nuovo nodo
     * @param type � il tipo di nodo inserito.
     */
    public static Node createNode( String name, String type) {
    	
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

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
        
    }
 
//_________________________________________________________________________________________________________________
    
    public void colorNodes(Model g ) {
    	
    	Node temp;
    	
    	Set<Node> nodes = g.vertexSet();
    	
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
    
   public void positionNodeAt( Object node, int x, int y ) {
   	
       DefaultGraphCell cell = jgraphAdapter.getVertexCell( node );
       Map<?, ?>        attr = cell.getAttributes(  );
       Rectangle2D.Double b = new Rectangle2D.Double();
       b    =  (Double) GraphConstants.getBounds( attr );
       GraphConstants.setBounds( attr, new Rectangle2D.Double( x, y, b.width, b.height ) );
       
       Map<DefaultGraphCell, Map<?, ?>> cellAttr = new HashMap<DefaultGraphCell, Map<?, ?>>(  );
       cellAttr.put( cell, attr );
       jgraphAdapter.edit( cellAttr, null, null, null );
       
   }
   

} // end of class