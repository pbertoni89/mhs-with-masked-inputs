package controller;

import java.io.PrintStream;
import java.util.EventListener;
import java.util.List;
import java.util.Vector;

import toolkit.StringPrintStream;

import graph.*;
import gui.GUIApplication;
import controller.validator.Validator;

/**rappresenta l'oggetto che ascolta un particolare oggetto grafico. Gli eventi 
 * riguardanti tale oggetto vengono inoltrati a questo oggetto che ripondera'
 * eseguendo una risposta spefica (variabile a seconda dell'oggetto e dell'evento
 * cui e' collegato). I compiti di un controller posson oessere dunque riassunti in:
 * <ul>
 *  <li> rilevazione eventi dell'oggetto di riferimento</li>
 *  <li> esecuzione risposta specifica</li>
 * </ul>
 * Ogni controller possiede poi oggetti comuni a tutte le sue specificizzazioni;
 * questi oggetti consentono l'adempimento degli obiettivi del Controller, nonch�
 * offrono un ponte tra il Controller e l'utente. Tali oggetti sono:
 * <ul>
 *  <li>{@link controller.validator.Validator Validator}</li>
 *  <li>{@link gui.GUIApplication GUIApplication}</li>
 *  <li>{@link graph.Model Model}</li>
 *  <li>{@link java.io.PrintStream errorconsole}</li>
 * </ul>
 * 
 * @author Koldar
 * @version 1.5
 * @see Validator
 * @see GUIApplication
 * @see Model
 */
public abstract class Controller implements EventListener{
	
	public static final int IDCONTROLLER_NEWFILE=0;
	public static final int IDCONTROLLER_OPENFILE=1;
	public static final int IDCONTROLLER_SAVEFILE=2;
	public static final int IDCONTROLLER_SAVEASFILE=3;
	public static final int IDCONTROLLER_EXITPROGRAMFILE=4;
	
	public static final int IDCONTROLLER_INSERTINPUTNODE=5;
	public static final int IDCONTROLLER_INSERTACTIONNODE=6;
	public static final int IDCONTROLLER_INSERTOUTPUTNODE=7;
	public static final int IDCONTROLLER_INSERTFLOW=8;
	public static final int IDCONTROLLER_DELETENODE=9;
	
	public static final int IDCONTROLLER_EXECUTEMINIMALANALYSIS=10;
	//TODO public static final int IDCONTROLLER_=;
	
	
	/**rappresenta l'oggetto che rileva e gestisce gli errori e i warning nel grafo*/
	private static Validator validator;
	/**rappresenta l'oggetto che viene ascoltato da tutte le specificizzazioni di Controller*/
	private static GUIApplication application;
	/**indica la struttura dati che contiene tutte le informazioni del grafo, disponibile a tutti i controller*/
	private static Model model;
	/**indica la console d'errore da usare per trasmettere eventuali errori durante l'esecuzioni degli ordini dell'utente*/
	protected static StringPrintStream log;
	/**rappresenta una la lista di controller usati. questa lista � indispensabile per far comunicare i controller tra di loro, cosa altrimenti impossibile*/
	protected static List<Controller> controllerList;
	/**identifica univocamente il Controller all'interno dell'archivio*/
	private int id;
	
	static{
		controllerList=new Vector<Controller>(0,1);
	}
	
	/**aggiugne il dato Controller all'interno della lista di controller utilizzati. L'id del controller
	 * indica precisamente anche il posto nella lista in cui il dato controller si trova 
	 * 
	 * @param _id l'id univoco del controller appena creato
	 */
	protected Controller(int _id){
		super();
		this.id=_id;
		controllerList.add(id,this);
	}
	
	/**
	 * @return the validator
	 * @throws NullStaticObjectException 
	 */
	protected static final Validator getValidator() throws NullStaticObjectException {
		if (validator==null){
			throw new NullStaticObjectException("validator nullo!");
		}
		return validator;
	}
	
	/**
	 * @param _validator
	 */
	public static void setValidator(Validator _validator){
		validator=_validator;
	}
	
	/**
	 * @return the application
	 * @throws NullStaticObjectException 
	 */
	protected static GUIApplication getApplication() throws NullStaticObjectException {
		if (application==null){
			throw new NullStaticObjectException("application nullo!");
		}
		return application;
	}
	
	/**
	 * @param application the application to set
	 */
	public static void setApplication(GUIApplication _application) {
		application = _application;
	}
	/**
	 * @return the scheme
	 * @throws NullStaticObjectException 
	 */
	
	
	protected static Model getModel() throws NullStaticObjectException {
		if (model==null){
			throw new NullStaticObjectException("model nullo!");
		}
		return model;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public static void setModel(Model _model) {
		model=_model;
	}
	/**
	 * @return the errorconsole
	 */
	protected static final PrintStream getErrorconsole() {
		return log;
	}
	/**
	 * @param errorConsole the errorconsole to set
	 */
	public static final void setErrorConsole(StringPrintStream errorConsole) {
		log = errorConsole;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
}
