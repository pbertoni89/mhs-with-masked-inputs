package graph.comp;

import java.awt.Color;

/**Un InputNode e' un nodo di ingresso del grafo. Questi nodo possiedono solamente
 * delle uscite, mai degli ingressi al nodo stesso. Sono caratterizzati da un diverso
 * colore rispetto agli altri nodi. un InputNode beneficia comunque di tutte le proprieta'
 * offerta dalla classe Node 
 * @author Koldar
 * @version 1.0
 * @see Node
 */
public class InputNode extends Node implements Diagnosticable {
	
	private static final long serialVersionUID = 3910642365356010232L;
	
	/**rappresenta il colore unico di un InputNode*/
	public final static Color DEFAULTINPUTCOLOR = new Color(25,200,25);
	public final static String INPUTNODENAME = "InputNode";

	
	/**costruisce un nuovo InputNode
	 * 
	 * @param _name nome del nuovo nodo
	 */
	public InputNode(String _name) {
		
		super(_name, DEFAULTINPUTCOLOR);

	}

}
