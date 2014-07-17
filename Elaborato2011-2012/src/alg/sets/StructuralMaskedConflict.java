package alg.sets;

import graph.comp.OutputNode;

/** L'insieme dei nodi diagnosticabili da cui dipende un OutputNode settato a OKM.
 * 
 * @author patrizio
 *
 */
public class StructuralMaskedConflict extends SubSet {

	public StructuralMaskedConflict(OutputNode outputNode) {
		super(outputNode);
	}

}
