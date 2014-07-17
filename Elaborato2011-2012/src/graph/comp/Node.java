package graph.comp;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * un Node rappresenta un qualunque nodo di un grafo. Fondamentalmente un nodo puo' essere connesso con dei predecessori oppure con dei successori (o entrambi). I collegamenti che collegano un nodo con un altro sono soggetti a rigide regole di progettazione. Se tali regole non sono rispettate, si può arrivare ad una situa zione di WARNING o, peggio, di ERRORE.  Un Node ha diversi attributi: i fondamentali sono il name (il nome) e il color(colore). Mentre il nome riveste un ruolo importante per l'identificazione del Node all'interno della HashMap di JGraphT, il colore serve per un facile lettura del grafo.
 * @author   Koldar
 * @version   1.0
 * @see ActionNode
 * @see InputNode
 * @see  OUtputNode
 */
public abstract class Node implements Serializable {

	private static final long serialVersionUID = 4701833059766359808L;
	
	/**
	 * indica l'etichetta che il Node avrà all'interno del grafo
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * indica il colore che del Node. esso serve per l'identificazione del Node nel grafo
	 * @uml.property  name="color"
	 */
	private Color color;
	/**colore che viene assunto dal Node quando non viene specificato alcun colore*/
	private static final Color DEFAULTABSTRACTCOLOR = Color.white;
	/**etichetta BASE che viene usata dal Node quando non si specificata alcuna etichetta*/
	private static final String DEFAULTABSTRACTNAME = "NODO ";
	/**
	 * oggetto rettangolare che denota posizione(x,y) e dimensione (w,h) della rappresentazione grafica del nodo. Questa rappresentazione è necessaria per il salvataggio e caricamento dello schema, per i motivi....etc
	 * @uml.property  name="graphicRect"
	 */
	private Rectangle2D.Double graphicRect;
	
	/**costruisce un nuovo Node
	 * 
	 * @param _name etichetta del Node
	 * @param _color colore che il Node assume nel grafo
	 * 
	 */
	public Node (String _name, Color _color) {
		name = _name;
		color= _color;
		graphicRect= new  Rectangle2D.Double();
	}
	
	/**
	 * @return   graphicRect
	 * @uml.property  name="graphicRect"
	 */
	public Rectangle2D.Double getGraphicRect() {
		return graphicRect;
	}

	/**
	 * @param graphicRect   the graphicRect to set
	 * @uml.property  name="graphicRect"
	 */
	public void setGraphicRect(Rectangle2D.Double graphicRect) {
		this.graphicRect = graphicRect;
	}

	/**costruiasce un nodo che ha come colore il DEFAULTABSTRACTCOLOR
	 * @param _name indica il nome del Node
	 */
	public Node (String _name) {
		this(_name,Node.DEFAULTABSTRACTCOLOR);
	}

	/**costruisce un nodo che ha come nome il DEFAULTABSTRACTNAME e come
	 * colore il DEFAULTABSTRACTCOLOR
	 */
	public Node()  {	
		this(Node.DEFAULTABSTRACTNAME,Node.DEFAULTABSTRACTCOLOR);
	}
	
	/**
	 * @return   il colore del Node
	 * @uml.property  name="color"
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}
	
	/**elabora il nome e il colore di un nodo e li inserisce in una stringa. Il pattern
	 * usato e':
	 * 
	 * {[Node_name],[Color_properties]}
	 * 
	 * dove Color_properties e' la stringa rilasciata dal metodo Color.toString()
	 * 
	 * @return il nome e il colore sottoforma di una stringa
	 */
	public String getNameColor() {
		return "{["+name+"],["+color.toString()+"]}";
	}
	
	/** @return il nome del nodo
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/** @return il nome della classe a cui il nome appartiene
	 */
	public String getNodeType() {
		return this.getClass().getSimpleName();
	}
	
}