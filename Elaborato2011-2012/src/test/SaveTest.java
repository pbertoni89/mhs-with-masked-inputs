package test;

import inputstream.FileObject;
import graph.Model;
import graph.comp.Flow;
import graph.comp.InputNode;

/**
 * @author   patrizio
 */
public class SaveTest {

	/**
	 * @uml.property  name="graph"
	 * @uml.associationEnd  
	 */
	static Model graph=new Model(Flow.class);
	/**
	 * @uml.property  name="loadedgraph"
	 * @uml.associationEnd  
	 */
	static Model loadedgraph=null;
	
	public static void main(String[] args){
		FileObject save=new FileObject("save.dat");
		graph.addVertex(new InputNode("Object"));
		
		System.out.println("saving...");
		save.SaveObject(graph);
		System.out.println(save.getFileError());
		System.out.println("loading...");
		loadedgraph=(Model)save.LoadObject();
		System.out.println(save.getFileError());
		System.out.println(loadedgraph.toString());
		
		
	}
	
}
