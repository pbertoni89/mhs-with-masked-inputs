package graph.comp;

import java.awt.Color;

/**
 * @author   patrizio
 */
public class OutputNode extends Node {
	
	private static final long serialVersionUID = 1L;
	/**
	 * @author   patrizio
	 */
	public static enum ResultList {/**
	 * @uml.property  name="oK"
	 * @uml.associationEnd  
	 */
	OK,/**
	 * @uml.property  name="kO"
	 * @uml.associationEnd  
	 */
	KO,/**
	 * @uml.property  name="oKM"
	 * @uml.associationEnd  
	 */
	OKM};
	
	public final static Color DEFAULTOUTPUTCOLOR = new Color(200,25,25);
	public final static String OUTPUTNODENAME = "OutputNode";
	
	/**
	 * rappresenta lo stato corrente di questa uscita
	 * @uml.property  name="associatedResult"
	 * @uml.associationEnd  
	 */
	private ResultList associatedResult;

	/**costruisce un nuova uscita avente come nome l'etichetta passata
	 * e come stato lo stato OK
	 * 
	 * @param _name il nome del nuovo OutputNode da inserire
	 */
	public OutputNode(String _name) {
		super(_name, DEFAULTOUTPUTCOLOR);
		associatedResult = ResultList.OK;
	}
	
	public ResultList getResult() {
		return associatedResult;
	}
	
	public void setResult(ResultList value) {
		associatedResult=value;
	}
}