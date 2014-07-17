package alg.sets;

import graph.comp.OutputNode;

/** L'insieme dei nodi diagnosticabili da cui dipende un OutputNode settato a KO.
 * 
 * @author patrizio
 *
 */
public class StructuralConflict extends SubSet {

	public StructuralConflict(OutputNode outputNode) {
		super(outputNode);
	}

}
