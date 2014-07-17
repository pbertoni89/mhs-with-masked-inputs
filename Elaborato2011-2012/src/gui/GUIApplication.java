package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;

import graph.Model;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

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

/** Il JFrame nel quale gira l'Applicazione. comprende:
 * -un pannello nel quale si trovano i bottoni tramite qui l'utente pu� svolgere le operazioni a lui offerte
 * -un pannello che descrive graficamente il grafo
 * -un pannello dove si indicano i valori delle varie uscite
 * -un pannello che indica lo stato del grafo
 * -un logpanel utile per comunicare con l'utente
 * 
 * @author   patrizio
 */
public class GUIApplication extends JFrame{

	private static final long serialVersionUID = 7092951650657197411L;
		
	/**
	 * @uml.property  name="contenitore"
	 * @uml.associationEnd  
	 */
	private GraphicNoStackComponent contenitore;
	
	/**
	 * @uml.property  name="applicationMenu"
	 * @uml.associationEnd  
	 */
	private MainMenuBar applicationMenu;
	/**
	 * @uml.property  name="applicationModelButtonPanel"
	 * @uml.associationEnd  
	 */
	private EditModelButtonPanel applicationModelButtonPanel;
	/**
	 * @uml.property  name="logPanel"
	 * @uml.associationEnd  
	 */
	private LogPanel logPanel;
	/**
	 * @uml.property  name="modelPanel"
	 * @uml.associationEnd  
	 */
	private ModelPanel modelPanel;
	private JScrollPane scrollModelPanel;
	/**
	 * @uml.property  name="rightpanel"
	 * @uml.associationEnd  
	 */
	public  RightPanel rightpanel;

	/**
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	private Model model;


	public GUIApplication(String title,Model model,NewFileController newfile,OpenFileController open,SaveFileController save,SaveAsFileController saveas,ExitProgramController exit,InsertInputNodeController input,InsertActionNodeController action,InsertOutputNodeController output,InsertFlowController insflow,DeleteNodeController delnode,ExecuteMinimalDiagnosisController minimal){
		super(title);

		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contenitore= new GraphicNoStackComponent();
		
		this.model = model;
		modelPanel = new ModelPanel(model);
		scrollModelPanel=new JScrollPane(modelPanel);
		logPanel = new LogPanel("img_warning.png","");
		applicationModelButtonPanel = new EditModelButtonPanel();
		applicationMenu = new MainMenuBar();
		rightpanel = new RightPanel();
	
		contenitore.setGridBagConstraints(0, 0, 0, 0, GridBagConstraints.BOTH, 1, 1);
		contenitore.add(applicationModelButtonPanel, contenitore.lim);

		contenitore.setGridBagConstraints(1, 0.3, 0, 1, GridBagConstraints.BOTH, 3, 1);
		contenitore.add(logPanel, contenitore.lim);

		contenitore.setGridBagConstraints(6, 6, 1, 0, GridBagConstraints.BOTH, 1, 1);
		contenitore.layout.setConstraints(modelPanel, contenitore.lim);

		contenitore.add(scrollModelPanel,contenitore.lim);

		contenitore.setGridBagConstraints(0.7,1.5, 2, 0 , GridBagConstraints.BOTH, 1, 1);
		contenitore.add(rightpanel, contenitore.lim);
	
		this.add(contenitore);
		this.setJMenuBar(applicationMenu);
		this.addListeners(newfile, open, save, saveas, exit, input, action, output, insflow, delnode, minimal);
		this.setVisible(true);
		
	}
	
	
	/**imposta lo stato dello StatusLog contenuto a destra della GuiApplication
	 * 
	 * @param str la stringa da posizionare al centro dello StatusLog
	 * @param color il colore da usare come sfondo dello StatusLog
	 */
	public void setStatusLogState(String str,Color color){
		rightpanel.SetState(str, color);
	}
	
	/**
	 * Aggiunta di tutti i Controller che gestiscono i Listener della GuiApplication
	 * 
	 * @param newfile
	 * @param open
	 * @param save
	 * @param saveas
	 * @param exit
	 * @param input
	 * @param action
	 * @param output
	 * @param insflow
	 * @param delnode
	 * @param minimal
	 */
	public void addListeners(NewFileController newfile,OpenFileController open,SaveFileController save,SaveAsFileController saveas,ExitProgramController exit,InsertInputNodeController input,InsertActionNodeController action,InsertOutputNodeController output,InsertFlowController insflow,DeleteNodeController delnode,ExecuteMinimalDiagnosisController minimal){
		
		this.applicationMenu.addListeners(newfile, open, save, saveas, exit);
		
		input.wireButton(applicationModelButtonPanel.getInsertInputNode());
		action.wireButton(applicationModelButtonPanel.getInsertActionNode());
		output.wireButton(applicationModelButtonPanel.getInsertOutputNode());
		insflow.wireButton(applicationModelButtonPanel.getInsertFlow());
		delnode.wireButton(applicationModelButtonPanel.getDeleteNode());
		minimal.wireButton(rightpanel.getExecuteMinimalDiagnosis());
		
		this.model.addModelStateListener(this.rightpanel.getStatuslog());
		this.model.addModelStateListener(minimal);
	}
	
	/**
	 * @return   the applicationModelButtonPanel
	 * @uml.property  name="applicationModelButtonPanel"
	 */
	public EditModelButtonPanel getApplicationModelButtonPanel() {
		return applicationModelButtonPanel;
	}

	/**
	 * @return   the rightpanel
	 * @uml.property  name="rightpanel"
	 */
	public RightPanel getRightpanel() {
		return rightpanel;
	}

	/**
	 * @return
	 * @uml.property  name="model"
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param  model
	 * @uml.property  name="model"
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return
	 * @uml.property  name="logPanel"
	 */
	public  LogPanel getLogPanel() {
		return logPanel;
	}
	
	/**
	 * @return
	 * @uml.property  name="modelPanel"
	 */
	public ModelPanel getModelPanel() {
		return modelPanel;
	}

}