package alg;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import graph.Model;
import graph.comp.*;

import org.jgrapht.alg.*;

/**
 * Gestisce il passaggio da un generico Model connesso o non connesso, verso un set di Model che sono grafi connessi. Si appoggia a un oggetto ConnectivityInspector, fornito da JgraphT.
 * @author   patrizio, michael
 * @see  ConnectivityInspector
 */
public class ConnectionInspector {
	
	/**
	 * Model di cui effettuare l'ispezione. Si ritorna al nome "graph" per attenersi alla teoria dei grafi.
	 * @uml.property  name="graph"
	 * @uml.associationEnd  
	 */
	private Model graph;
	/**
	 * Set di Models che sono grafi disconnessi. Output della classe.
	 * @uml.property  name="connectedGraphs"
	 */
	private Vector<Model> connectedGraphs;
	/** Variabile condivisa tra i metodi della classe.*/
	private Set<Flow> flowSet;
	/** Variabile condivisa tra i metodi della classe.*/
	private Collection<Set<Node>> connectedSets;
	
    public ConnectionInspector(Model _graph) {
    	
        connectedGraphs = new Vector<Model>();
    	graph = _graph;

    	ConnectivityInspector<Node,Flow> inspector = new ConnectivityInspector<Node,Flow>(graph);
        connectedSets = inspector.connectedSets();  
        flowSet = graph.edgeSet();
        analyzeSubgraphs();
        //WOW!!! INSERITO UN VERTEX TI DA SUCCESSORI E PREDECESSORI: inspector.connectedSetOf(d); 
    }
    
    /** Itero su ogni sottografo, per capirne i flows, crearne un Model fedele e inserirlo nei connectedGraphs. */
    public void analyzeSubgraphs() {
    	Iterator <Set<Node>> connectedSetsIt = connectedSets.iterator();

        while(connectedSetsIt.hasNext()) {
        	
        	Set<Node> tempNodeSet = connectedSetsIt.next(); //i-esimo set di nodi connessi
        	//mi copio il nodeset in un vector, per controllare pi� facilmente ogni flow.
        	Vector<Node> tempNodesConnected = Utilities.setToVector( tempNodeSet );
        	//inizializzo il modello, e gli butto dentro tutti i nodes che gli spettano.
        	Model tempModel = new Model(Flow.class);
        	Iterator<Node> nodeIt = tempNodeSet.iterator();
        	while(nodeIt.hasNext())
        		tempModel.addVertex(nodeIt.next());
        	
        	for(int s=0; s<tempNodesConnected.size()-1; s++)
        		for(int t=1; t<tempNodesConnected.size(); t++) 
        			if( !tempNodesConnected.get(s).equals(tempNodesConnected.get(t)) ) {
    					if( areConnected(tempNodesConnected.get(s), tempNodesConnected.get(t)) ) 
        					tempModel.addEdge(tempNodesConnected.get(s), tempNodesConnected.get(t));
    					else 
    					if( areConnected(tempNodesConnected.get(t), tempNodesConnected.get(s)) ) 
        					tempModel.addEdge(tempNodesConnected.get(t), tempNodesConnected.get(s));
        		}
        	connectedGraphs.add(tempModel);
        }
    }
    
    /**
	 * Offre all'esterno la collezione dei grafi connessi, visti come Models.
	 * @uml.property  name="connectedGraphs"
	 */
    public Vector<Model> getConnectedGraphs() {
    	return connectedGraphs;
    }
    
    /** Dati due Node, stabilisce se essi sono ordinatamente connessi o meno.
     * @param source
     * @param target
     * @return connected
     */
    public boolean areConnected(Node source, Node target) {
    	boolean connected = false;
    	Iterator<Flow> flowIt = flowSet.iterator();
    	Flow tempFlow;
    	while(flowIt.hasNext()) {
    		tempFlow = flowIt.next();
    		if( tempFlow.getSource().equals(source) && tempFlow.getTarget().equals(target) )
    			connected = true;
    	}
    	return connected;
    }
}