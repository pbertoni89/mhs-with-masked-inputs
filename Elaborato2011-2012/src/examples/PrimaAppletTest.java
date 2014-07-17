 package examples;

import inputstream.FileObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

import java.util.*;

import javax.swing.JApplet;

import javax.xml.transform.TransformerConfigurationException;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.*;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.GraphMLExporter;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.*;

import org.jgrapht.VertexFactory;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.generate.RandomGraphGenerator;
import org.jgrapht.generate.RandomGraphGenerator.DefaultEdgeTopologyFactory;
import org.jgrapht.graph.ClassBasedVertexFactory;
import org.jgrapht.graph.DefaultEdge;
import org.xml.sax.SAXException;

import graph.Model;
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

@SuppressWarnings("unused")  // ha effetto su TUTTA la classe!
public final class PrimaAppletTest extends JApplet implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 600, 500 );
    private static final String SAVEPATH = "..\\Saves\\saveNew.grph";

    private static final String ACTIONNODE = "ActionNode";
    private static final String INPUTNODE = "InputNode";
	private static final String OUTPUTNODE = "OutputNode";
    
    //private static final String ACTIONNODEDEFAULTNAME = "A";
    //private static final String INPUTNODEDEFAULTNAME = "I";
    //private static final String OUTPUTNODEDEFAULTNAME = "O";
    
	private JGraphModelAdapter<Node,Flow> jgraphAdapter;
	private JGraph jgraph;
	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	private Model model;
	
	private FileObject loadSaveHandler;
	
//________________________________________________________________________________________________________________    
    
	public void init(  ) {
    	
		model = new Model(Flow.class);
        		
        createBasicGraph();
		
        //FIXME ISTRUZIONE COLPEVOLE DELL'ERRORE 3 IN SALVATAGGIO
        //jgraphAdapter = new JGraphModelAdapter<Node,Flow> ( scheme.getModel() );
       //jgraph = new JGraph( jgraphAdapter );
        
        loadSaveHandler = new FileObject(SAVEPATH);
        
        /*  // dipendono da istruzione colpevole            
        adjustDisplaySettings( jgraph );
        getContentPane().add( jgraph );
        resize(DEFAULT_SIZE);

    	randomNodePosition( scheme.getModel() );
        colorNodes( scheme.getModel() );
		*/
        
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
    	
    	System.out.println("modello!!"+ model.toString());
    	if (model.containsVertex(i1))
    		System.out.println("c'�");
    	if(model.vertexSet().contains(i1))
    		System.out.println("c'� in set");
    	
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
    
    public void colorNodes( Model  g ) {
    	
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

    public void randomNodePosition(Model g) {
	 
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

	@Override
	public void run() {
		
	}

} // end of class   */