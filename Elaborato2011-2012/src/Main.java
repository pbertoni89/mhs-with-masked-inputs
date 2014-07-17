import java.awt.Rectangle;

import controller.Controller;
import controller.NullStaticObjectException;
import controller.gui.DeleteNodeController;
import controller.gui.ExecuteMinimalDiagnosisController;
import controller.gui.InsertActionNodeController;
import controller.gui.InsertFlowController;
import controller.gui.InsertInputNodeController;
import controller.gui.InsertOutputNodeController;
import controller.menubar.ExitProgramController;
import controller.menubar.NewFileController;
import controller.menubar.OpenFileController;
import controller.menubar.SaveAsFileController;
import controller.menubar.SaveFileController;
import controller.validator.Validator;
import graph.Model;
import graph.comp.Flow;
import gui.GUIApplication;

/**
 * @author   Koldar
 * @author   Giskard
 * @author   Micheal
 */
public class Main {

	/**
	 * @param args
	 */
	private final static String APPTITLE = "Diagnosi Minimale con Mascheramento Comprensiva degli Ingressi";
	private final static Rectangle APPSIZE = new Rectangle(0,0,800,600);
	
	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	static Model model;
	/**
	 * @uml.property  name="validator"
	 * @uml.associationEnd  
	 */
	static Validator validator;
	/**
	 * @uml.property  name="gui"
	 * @uml.associationEnd  
	 */
	static GUIApplication gui;
	
	public static void main(String[] args) throws NullStaticObjectException {
		
		model = new Model(Flow.class);
		NewFileController newfile=new NewFileController();
		
		gui = new GUIApplication(
				APPTITLE,
				model,
				newfile,//0
				new OpenFileController(),//1
				new SaveFileController(),//2
				new SaveAsFileController(),//3
				new ExitProgramController(),//4
				new InsertInputNodeController(),//5
				new InsertActionNodeController(),//6
				new InsertOutputNodeController(),//7
				new InsertFlowController(),//8
				new DeleteNodeController(),//9
				new ExecuteMinimalDiagnosisController()//10
				);

		Controller.setModel(model);
		Controller.setValidator(new Validator());
		Controller.setApplication(gui);
		Controller.setErrorConsole(gui.getLogPanel().out);
		
		newfile.actionPerformed(null);
		
		gui.setBounds(APPSIZE);
		gui.setVisible(true);
	}
}
