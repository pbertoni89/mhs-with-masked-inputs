package examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import java.util.HashMap;
//import java.util.HashSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphCellEditor;
import org.jgraph.graph.GraphConstants;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DirectedSubgraph;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
@SuppressWarnings("unused")
public class MyVisualHelloJGraph extends JApplet {
	
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );
 
    private JGraphModelAdapter<Comparable<String>, DefaultEdge> m_jgAdapter;

    @SuppressWarnings({ "rawtypes" , "unchecked" })
	public void init(  ) {
    	
    	
    	
        // create a JGraphT graph
        ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );
    	//DirectedSubgraph subg = new DirectedSubgraph(g,  ,DefaultEdge.class);
    	
        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );

        JGraph jgraph = new JGraph( m_jgAdapter );
        System.out.println("attributi: "+m_jgAdapter.getAttributes());
        //System.out.println(m_jgAdapter.setDefaultVertexAttributes()
        
        			//	Color arg1;
					//GraphConstants.setBorderColor(new Map(Color.black), arg1);
        
        AttributeMap miaMappa = new AttributeMap();
       // miaMappa.
        //font=java.awt.Font[family=Dialog,name=Dialog,style=bold,size=12], backgroundColor=java.awt.Color[r=255,g=153,b=0], bounds=java.awt.geom.Rectangle2D$Double[x=50.0,y=50.0,w=90.0,h=30.0], border=javax.swing.border.BevelBorder@5bcdbf6, opaque=true, foregroundColor=java.awt.Color[r=255,g=255,b=255]
        adjustDisplaySettings( jgraph );
        getContentPane(  ).add( jgraph );
        resize( DEFAULT_SIZE );

        // add some sample data (graph manipulated via JGraphT)
       
        g.addVertex( "v1" );
        g.addVertex( "v2" );
        g.addVertex( "v3" );
        g.addVertex( "v4" );

        g.addEdge( "v1", "v2" );
        g.addEdge( "v2", "v3" );
        g.addEdge( "v3", "v1" );
        g.addEdge( "v4", "v3" );

        // position vertices nicely within JGraph component
        positionVertexAt( "v1", 130, 40 );
        positionVertexAt( "v2", 60, 200 );
        positionVertexAt( "v3", 310, 230 );
        positionVertexAt( "v4", 380, 70 );
        
        System.out.println("drag enabled:" + jgraph.isDragEnabled() );
        jgraph.setSizeable(false);
        
// ----------------------------------------------------------------------------------------------------------------------
//  MODIFICA DEL CODICE PRE-ESISTENTE: ESPERIMENTI, CONOSCENZA JGRAPH <3
        
     // I VERTEX SONO PARAMETRICI
        char provaChar = 'y'; 
        	g.addVertex(provaChar);
        	g.addEdge(provaChar,"v2");
        	g.addEdge("v3",provaChar);
        	g.removeVertex(provaChar);  // RIMOZIONE VERTEX ELIMINA GLI EDGES AD ESSO RELATIVI (VIENE GRATIS X NOSTRA SPECIFICA)
    
        int provaInt = 1;
        	g.addVertex(provaInt);   
        	g.addEdge(provaInt,"v1");
        	g.addEdge("v2",provaInt);
            
        	
        	Set edgesVicini = g.edgesOf(provaInt);
        	
    		Iterator<DefaultEdge> iterator = edgesVicini.iterator();
    		
    		while ( iterator.hasNext() )
    		 {
    			DefaultEdge edgeCorrente = iterator.next();
    			System.out.println("ciao, sono edgeCorrente : "+ edgeCorrente.toString());
    			  
    		  }
            
        	int nVicini = edgesVicini.size();
        	System.out.println("Il vertice " + provaInt + " ha " + nVicini + " vertici adiacenti.");
        
        if ( g.containsVertex(provaInt)==true )
        	System.out.println("ok, c'è provaInt vertex");
        else
        	System.out.println("non c'è provaInt vertex");
        
        if ( g.containsEdge(provaInt,"v1")==true )        //  g.containsEdge("v1", provaInt) = false
        	System.out.println("ok, c'è (provaInt,v1) edge");
        else
        	System.out.println("non c'è (provaInt,v1) edge");        
// ----------------------------------------------------------------------------------------------------------------------

    }

    
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


    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void positionVertexAt( Object vertex, int x, int y ) {
    	
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map             attr = cell.getAttributes(  );
        //System.out.println( attr.toString() );
        Rectangle2D.Double b = new Rectangle2D.Double();
        b    =  (Double) GraphConstants.getBounds( attr );
        GraphConstants.setBounds( attr, new Rectangle2D.Double( x, y, b.width, b.height ) );
        
        GraphConstants.setBackground(attr, Color.green);
        
        
        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr, null, null, null );
        
    }
    
    
}