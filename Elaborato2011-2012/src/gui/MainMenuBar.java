package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.menubar.ExitProgramController;
import controller.menubar.NewFileController;
import controller.menubar.OpenFileController;
import controller.menubar.SaveAsFileController;
import controller.menubar.SaveFileController;

/**
 * @author   patrizio
 */
public class MainMenuBar extends JMenuBar{

	/**
	 * @uml.property  name="serialVersionUID"
	 */
	private static final long serialVersionUID = -3046759850795865308L;
	
	//**************** MENUS ************************
	/**
	 * @uml.property  name="fILE"
	 */
	private static final JMenu FILE=new JMenu("File");
	/**
	 * @uml.property  name="eDIT"
	 */
	private static final JMenu EDIT=new JMenu("Edit");
	private static final JMenu OPTIONS=new JMenu("Options");
	private static final JMenu HELP=new JMenu("Help");
	//**************** FILE ITEMS *************************
	/**
	 * @uml.property  name="nEW"
	 */
	private static final JMenuItem NEW=new JMenuItem("New");
	/**
	 * @uml.property  name="oPEN"
	 */
	private static final JMenuItem OPEN=new JMenuItem("Open");
	/**
	 * @uml.property  name="sAVE"
	 */
	private static final JMenuItem SAVE=new JMenuItem("Save");
	/**
	 * @uml.property  name="sAVEAS"
	 */
	private static final JMenuItem SAVEAS=new JMenuItem("Save as");
	/**
	 * @uml.property  name="eXIT"
	 */
	private static final JMenuItem EXIT=new JMenuItem("Exit");
	//***************** HELP ITEMS *************************
	private static final JMenuItem GUIDE=new JMenuItem("Guide");
	private static final JMenuItem ABOUT=new JMenuItem("About us");
	
	/**il costruttore crea una barra dei menu senza aggiungere i listener.
	 * e' un costruttore prova, fino a quando non si decidera' quale oggetto
	 * ascoltera' gli eventi del menu'
	 */
	public MainMenuBar(){
		super();
		//creazione menu file
		FILE.add(NEW);
		FILE.add(OPEN);
		FILE.add(SAVE);
		FILE.add(SAVEAS);
		FILE.add(EXIT);
		this.add(FILE);
		//creazione menu edit
		this.add(EDIT);
		//creazione menu options
		this.add(OPTIONS);
		//creazione menu help
		HELP.add(GUIDE);
		HELP.add(ABOUT);
		this.add(HELP);
	}
	public void addListeners( NewFileController _newfilecontroller,
							  OpenFileController _openfilecontroller,
							  SaveFileController _savefilecontroller,
							  SaveAsFileController _saveasfilecontroller,
							  ExitProgramController _exitprogramcontroller) {
		
		_newfilecontroller.wireButton(NEW);
		_openfilecontroller.wireButton(OPEN);
		_savefilecontroller.wireButton(SAVE);
		_saveasfilecontroller.wireButton(SAVEAS);
		_exitprogramcontroller.wireButton(EXIT);
	}

	/**
	 * @return   the serialversionuid
	 * @uml.property  name="serialVersionUID"
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return   the file
	 * @uml.property  name="fILE"
	 */
	public static JMenu getFile() {
		return FILE;
	}
	/**
	 * @return   the edit
	 * @uml.property  name="eDIT"
	 */
	public static JMenu getEdit() {
		return EDIT;
	}
	/**
	 * @return   the new
	 * @uml.property  name="nEW"
	 */
	public static JMenuItem getNew() {
		return NEW;
	}
	/**
	 * @return   the open
	 * @uml.property  name="oPEN"
	 */
	public static JMenuItem getOpen() {
		return OPEN;
	}
	/**
	 * @return   the save
	 * @uml.property  name="sAVE"
	 */
	public static JMenuItem getSave() {
		return SAVE;
	}
	/**
	 * @return   the saveas
	 * @uml.property  name="sAVEAS"
	 */
	public static JMenuItem getSaveas() {
		return SAVEAS;
	}
	/**
	 * @return   the exit
	 * @uml.property  name="eXIT"
	 */
	public static JMenuItem getExit() {
		return EXIT;
	}
}
