package alg.sets;

import graph.comp.Diagnosticable;
import graph.comp.Node;
import graph.comp.OutputNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Un insieme di Nodi diagnosticabili, legato concettualmente a un OutputNode di modo che esso sia tutto e solo l'insieme dei nodi predecessori dell'OutputNode, nel contesto di un grafo diretto aciclico.
 * @author   patrizio
 * @see  Diagnosticable
 */
public abstract class SubSet {

	/**
	 * @uml.property  name="ownSet"
	 */
	private Set<Diagnosticable> ownSet;
	/**
	 * @uml.property  name="outputNode"
	 * @uml.associationEnd  
	 */
	private OutputNode outputNode;
	
	public SubSet(OutputNode outputNode) {
		this.outputNode = outputNode;
		ownSet = new HashSet<Diagnosticable>();
	}
	
	/**
	 * @return
	 * @uml.property  name="ownSet"
	 */
	public Set<Diagnosticable> getOwnSet() {
		return ownSet;
	}
	
	public void add(Diagnosticable newElem) {
		ownSet.add(newElem);
	}
	public void addAll(Set<Diagnosticable> newSet) {
		ownSet.addAll(newSet);
	}
	public void remove(Diagnosticable newElem) {
		ownSet.remove(newElem);
	}
	public void removeAll(Set<Diagnosticable> newSet) {
		ownSet.removeAll(newSet);
	}
	/**
	 * @return
	 * @uml.property  name="outputNode"
	 */
	public OutputNode getOutputNode() {
		return outputNode;
	}
	
	public String toString() {
		//String string = outputNode.getName() +" ("+outputNode.getResult()+")";
		String string = outputNode.getName();
		return string;
	}
	
	public String nodeToString() {
		return outputNode.getName() +" ("+outputNode.getResult()+") ";
	}
	
	public String setToString() {
		String string = "{ ";
		Iterator<Diagnosticable> it = ownSet.iterator();
		while( it.hasNext() )
			string = string + ((Node) it.next()).getName() +", ";
		string = string +  " }";
	return string;
	}
}
