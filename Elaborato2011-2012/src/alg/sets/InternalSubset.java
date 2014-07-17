package alg.sets;

import graph.comp.OutputNode;

/** L'insieme dei nodi diagnosticabili da cui dipende un OutputNode settato a OK.
 * 
 * @author patrizio
 *
 */
public class InternalSubset extends SubSet {

	public InternalSubset(OutputNode outputNode) {
		super(outputNode);
	}

}
