package test;

import inputstream.FileObject;
import graph.Model;
import graph.comp.Flow;
import graph.comp.InputNode;

public class StaticSave {

	public static void main(String[] args) {
		
		Model modello = new Model(Flow.class);
		
		modello.addVertex(new InputNode("input1"));
		
		FileObject saveHandler = new FileObject("save_here.sav");
		
		saveHandler.SaveObject(modello);
		
		modello.addVertex(new InputNode("input2"));
		
		Model caricato = (Model) saveHandler.LoadObject();
		
		System.out.println( saveHandler.getFileError() );
		
		caricato.toString();
		
		
	}
}
