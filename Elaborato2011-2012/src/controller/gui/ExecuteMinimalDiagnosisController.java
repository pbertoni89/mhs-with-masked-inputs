package controller.gui;

import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import toolkit.modelstateevent.ModelStateEvent;
import toolkit.modelstateevent.ModelStateListener;

import alg.ConnectionInspector;
import alg.DiagnosisInspector;
import alg.Utilities;

import controller.ButtonHandlerController;
import controller.NullStaticObjectException;
import event.numberevent.NumberEvent;

import graph.Model;
import graph.comp.*;
import graph.state.CorrectModelState;

import gui.FinalDiagnosisDialog;

/**
 * Questa classe performerà l'azione SOLO SE lo STATO del MODELLO sarà VALIDO.
 * @author   patrizio
 */
public class ExecuteMinimalDiagnosisController extends ButtonHandlerController implements ModelStateListener{
	
	private static final long serialVersionUID = 1L;
	public final static String HELLO = ": Launching Minimal Masked Input-comprehensive Diagnosis...";
	public final static String NOTCORRECTSTATUS ="Unable to diagnosticate Model, check status.";
	public final static String COMPUTINGCARTESIANPRODUCT = "Computing Cartesian Product Between multiple Diagnosis..";
	
	/** Collezione dei Model che sono, in accordo alla Teoria dei Grafi, grafi connessi.  */
	Vector<Model> connectedGraphModels;
	/** Collezione delle Diagnosi associate a ogni ConnectedGraph. */
	Vector<Set<Set<Diagnosticable>>> connectedGraphDiagnoses;
	/** Prodotto cartesiano delle Diagnosis associate a un insieme di ConnectedGraph, dove nessuno presenta Diagnosi Minimali nulle.
	 *  Intepretazione: se 1 o più ConnectedGraph presentano Diagnosi nulle, essi vengono esclusi da questo computo. */
	Set<Set<Diagnosticable>> cartesianProductDiagnoses;
	/**
	 * Oggetto grafico usato da ogni Diagnosi per rappresentarsi.
	 * @uml.property  name="diagnosisDialog"
	 * @uml.associationEnd  
	 */
	FinalDiagnosisDialog diagnosisDialog;
	/** Set di Diagnosi non nulli, input del prodotto cartesiano.*/
	Vector<Set<Set<Diagnosticable>>> notNullDiagnoses;
	/** misura quante diagnosi non sono nulle. */
	int notNullDiagnosesCount = 0;
	
	public ExecuteMinimalDiagnosisController(){
		super(IDCONTROLLER_EXECUTEMINIMALANALYSIS);
	}
//_________________________________________________________________________________________________________________________________________	
	/** Esegue l'azione specifica del controller: la diagnosi minimale del Modello. */
	@Override
	public void actionOverseenPerformed(ActionEvent e) throws NullStaticObjectException {
	
		boolean cartesian = false;
		if ( getModel().getModelState().grantAnalysisRights() ) {
			initializeDiagnosis(cartesian);
			handleDiagnosis(cartesian);
			}
		else
			log.println(NOTCORRECTSTATUS);
	}
//_______________________________________________________________________________________________________________________________________________	
	@Override
	public void idleStateAction(NumberEvent<Integer> arg0){
		try {
			this.listenedButton.setEnabled((getModel().getModelState() instanceof CorrectModelState));
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
	}
//_______________________________________________________________________________________________________________________________________________
	/** Comunica all'utente la data e ora di inizio dell'elaborazione, e inizializza le sue strutture dati. */
	public void initializeDiagnosis(boolean cartesian) {
		
		Timestamp now = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		log.reprintln(now.toString()+HELLO);
		if(cartesian) {
			
			notNullDiagnoses = new Vector<Set<Set<Diagnosticable>>>();
			notNullDiagnosesCount = 0;
			cartesianProductDiagnoses = new HashSet<Set<Diagnosticable>>();
			
			try {
				
				ConnectionInspector connectionInspector = new ConnectionInspector( getModel() );
				connectedGraphModels = connectionInspector.getConnectedGraphs();
				connectedGraphDiagnoses = new Vector<Set<Set<Diagnosticable>>>();
				
			} catch (NullStaticObjectException e1) {
				log.reprintln(e1);
			} finally{
				this.setIdle();
			}
		}
		
	}
//_______________________________________________________________________________________________________________________________________________
	/** Identifica tutti i sottografi che sono grafi connessi; su ognuno di questi richiamerà @see connectedDiagnosis 
	 *  e infine, all'occorrenza, eseguirà il prodotto cartesiano degli insiemi di Diagnosi ottenute prima di stampare, sempre
	 *  se ce ne sono, i risultati ottenuti. 
	 */
	public void handleDiagnosis(boolean cartesianImplemented) {

		try {
			
			if(cartesianImplemented) {

				Iterator<Model> connectedModelIt = connectedGraphModels.iterator();
				while(connectedModelIt.hasNext())
					connectedDiagnosis(connectedModelIt.next());

				if(notNullDiagnosesCount!=0)
					if(notNullDiagnosesCount==1) 
						presentDiagnosisDialog(notNullDiagnoses.get(0));
					else
						launchCartesianProduct();
			}
			else {
				DiagnosisInspector diagnosisInspector = new DiagnosisInspector( getModel(), getApplication() );
				Set<Set<Diagnosticable>> modelDiagnosis = diagnosisInspector.getDiagnosis();
				if(modelDiagnosis!=null) 
					presentDiagnosisDialog(modelDiagnosis);
			}
			
		} catch (NullStaticObjectException e1) {
			log.reprintln(e1);
		} finally{
			this.setIdle();
		}
	}
//_______________________________________________________________________________________________________________________________________________
	/** Crea l'oggetto grafico delegato a visualizzare il risultato delle Diagnosi. 
	 *  @throws NullStaticObjectException
	 */
	public void presentDiagnosisDialog(Collection<Set<Diagnosticable>> diagnoses) throws NullStaticObjectException {
		diagnosisDialog  = new FinalDiagnosisDialog( getApplication(), 
				 DiagnosisInspector.PRESENTINGMHS,
				 diagnoses );
	}
//_______________________________________________________________________________________________________________________________________________
	/** Crea un Ispettore di Diagnosi per ogni sottografo connesso, il quale si occupa di ottenere le Diagnosi Connesse.
	 *  Se il prodotto dell'elaborazione è diverso da null, lo aggiunge nella struttura apposita @see notNullDiagnoses
	 *  e conteggia un inserimento (che indica altrove se sia o no necessario fare il prodotto cartesiano delle diagnosi. 
	 * @throws NullStaticObjectException
	 */
	public void connectedDiagnosis(Model tempConnectedGraphModel) throws NullStaticObjectException {
		
		DiagnosisInspector diagnosisInspector = new DiagnosisInspector( tempConnectedGraphModel, getApplication() );
		Set<Set<Diagnosticable>> tempConnectedGraphDiagnosis = diagnosisInspector.getDiagnosis();
		connectedGraphDiagnoses.add( tempConnectedGraphDiagnosis );
		log.println(tempConnectedGraphDiagnosis.toString());
		
		if(tempConnectedGraphDiagnosis!=null) {
			notNullDiagnoses.add(tempConnectedGraphDiagnosis);
			notNullDiagnosesCount++;
		}
	}
//_______________________________________________________________________________________________________________________________________________
	/** Gestisce la generazione del prodotto cartesiano degli insiemi di Diagnosi NON nulli.
	 *  Presenta i risultati ottenuti richiamando @see presentDiagnosisDialog
	 * @throws NullStaticObjectException
	 */
	public void launchCartesianProduct() throws NullStaticObjectException {
		log.println(COMPUTINGCARTESIANPRODUCT+"of"+ notNullDiagnoses.toString());
		Set<Set<Set<Diagnosticable>>> temp =	Utilities.cartesianProduct(notNullDiagnoses);
		Iterator<Set<Set<Diagnosticable>>> tempIt = temp.iterator();
		System.out.println("cartesian product size="+ temp.size());
		while(tempIt.hasNext()) 
			cartesianProductDiagnoses.addAll(tempIt.next());
		
		presentDiagnosisDialog(cartesianProductDiagnoses);
	}
	//_______________________________________________________________________________________________________________________________________________	
	@Override
	public void executeValidatedAction() {
	}
//_______________________________________________________________________________________________________________________________________________	
	@Override
	public void ModelStateChanged(ModelStateEvent event) {
		try {
			getApplication().getRightpanel().
											getExecuteMinimalDiagnosis().setEnabled((event.getToState() instanceof CorrectModelState));
		} catch (NullStaticObjectException e) {
			e.printStackTrace();
		}
	}

} //end of class