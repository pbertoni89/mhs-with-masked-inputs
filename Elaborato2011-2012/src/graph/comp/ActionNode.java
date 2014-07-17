package graph.comp;

import java.awt.Color;

public class ActionNode extends Node implements Diagnosticable {
	
	public static final long serialVersionUID = 1794525294445209831L;
	
	//public final static Color DEFAULTACTIONCOLOR = new Color(254,237,181);
	public final static Color DEFAULTACTIONCOLOR = new Color(225,120,0);
	public final static String ACTIONNODENAME = "ActionNode";

	public ActionNode(String _name) {
		
		super(_name, DEFAULTACTIONCOLOR);
			
	}
	

}