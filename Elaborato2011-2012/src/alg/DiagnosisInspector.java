package alg;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import alg.sets.*;

import toolkit.StringPrintStream;

import graph.Model;
import graph.comp.*;
import gui.GUIApplication;
import gui.SelectSCPowerSetDialog;

/**
 * Questa è la classe a cui è delegato ciò che concerne la Diagnosi Minimale con Mascheramento Comprensiva degli Ingressi. 
 * Il controller associato al bottone di elaborazione crea un'istanza DiagnosisInspector passandole il Modello.
 * @author    patrizio
 * @see  ExecuteMinimalDiagnosisController
 */
public class DiagnosisInspector {

	public static final String HELLO = "Inspecting Graph: ";
	public static final String GENERATINGSUBSETS = "Generating Subsets...";
	public static final String GENERATINGPOWERSET = "Generating PowerSet...";
	public static final String GENERATINGDIFFINTERSECT = "Generating Differences, Intersections...";
	public static final String GENERATINGMHS = "Generating Minimal Hitting Sets...";
	public static final String PRESENTINGMHS = "Diagnosis are: ";
	public static final String PREFILTERING = "There aren't Minimal Masked Input-comprehensive Diagnosis.";
	public static final String SELECTIONDIALOG = "Select a base set for diagnosis.";
	public static final String ABORT = "Diagnosis aborted.";
	public static final String WAITINGUSERSCCHOICE = "Waiting for user's choice of set(s)...";
	
	/**
	 * Oggetto Stream  in grado di comunicare con il log dell'applicazione.
	 * @uml.property  name="log"
	 * @uml.associationEnd  
	 */
	private StringPrintStream log;
	/**
	 * Copia coerente del Modello da diagnosticare.
	 * @uml.property  name="model"
	 * @uml.associationEnd  
	 */
	private Model model;
	private Set<Node> nodeSet;
	/**
	 * Camminatore del grafo, in grado di trovare ricorsivamente predecessori e successori di un certo nodo.
	 * @uml.property  name="graphWalker"
	 * @uml.associationEnd  
	 */
	private GraphWalker graphWalker;
	/**
	 * @uml.property  name="gui"
	 * @uml.associationEnd  
	 */
	private static GUIApplication gui;
	
	/** Ottimizzazione 1) se non ci sono almeno un nodo KO E un nodo OKM, non si hanno diagnosi. */
	private boolean preFilteringAtLeast_1KO_1OKM;
	/** Ottimizzazione 2) se per uno StructuralMaskedConflict la sua intersezione con tutti gli StructuralConflict 
	 *  scelti è nulla, non si hanno diagnosi.
	 */
	private boolean preFilteringVoidSet;
	/** Chiusura abortiva a seguito della terminazione del dialogo di selezione dal SCpowerSet. */
	private boolean abort;
	/** Seconda struttura dati più importante del programma: sono Diagnosi Minimali con Mascheramento comprensive di Ingressi. */
	private Set<Set<Diagnosticable>> setOfDiagnosis; 
	/** Insieme di tutti gli InternalSubsets, cioè i set di nodi predecessori di un certo OutputNode associato a un Result OK. */
	private Set<InternalSubset> setOfIS;
	/** Insieme di tutti gli StructuralConflict, cioè i set di nodi predecessori di un certo OutputNode 
	 *  associato a un Result KO. Gli elementi di questo insieme verranno modificati, rimuovendone l'unione 
	 *  degli InternalSubset (i-esimo C underscore)
	 */
	private Set<StructuralConflict> setOfSC;
	/** Insieme di tutti gli StructuralMaskedConflict, cioè i set di nodi predecessori di un certo OutputNode 
	 *  associato a un Result OKM. Gli elementi di questo insieme verranno modificati, rimuovendone l'unione 
	 *  degli InternalSubset (i-esimo CM underscore)
	 */ 
	private Set<StructuralMaskedConflict> setOfSMC;
	/** Unione dei nodi di tutti gli InternalSubsets. */
	private Set<Diagnosticable> unionIS;
	/** Insieme delle parti dell'insieme degli StructuralConflict, privo dell'insieme vuoto. */
	private Set<Set<StructuralConflict>> SCpowerSet;
	/** L'insieme di StructuralConflict scelto per proseguire la diagnosi del punto 2), tra tutti quelli del SCpowerSet. */
	private Set<StructuralConflict> SCselection;
	/** L'insieme di StructuralConflict complementare di SCselection, rispetto a setOfSC. */
	private Set<StructuralConflict> SCnonSelection;
	/** Costituita da tutti gli insiemi ottenuti al punto 2) + i nodi degli StructuralConflict non utilizzati al punto 2). */
	private Vector<Set<Diagnosticable>> collectionReadyForDiagnosis;
		
	public DiagnosisInspector(Model _model, GUIApplication _gui) {
		
		gui = _gui;
		log = _gui.getLogPanel().out;
		model = _model;
		graphWalker = new GraphWalker(model);
		nodeSet = model.vertexSet();
		setOfIS = new HashSet<InternalSubset>();
		setOfSC = new HashSet<StructuralConflict>();
		setOfSMC = new HashSet<StructuralMaskedConflict>();
		SCpowerSet = new HashSet<Set<StructuralConflict>>();
		collectionReadyForDiagnosis = new Vector<Set<Diagnosticable>>();
		SCselection = new HashSet<StructuralConflict>();
		SCnonSelection = new HashSet<StructuralConflict>();
		
		preFilteringAtLeast_1KO_1OKM = true;
		preFilteringVoidSet = true;
		abort = false;
	}

//1_____________________________________________________________________________________________________________________________________	
	/** Assegna alle cardinalità  il loro giusto valore.
	 *  Genera gli Internal Subsets, gli StructuralConflict e gli StructuralMaskedConflict. 
	 *  Opera un prefiltraggio sulla possibilità  di avere diagnosi minimali; infatti:
	 *  Se l'osservazione non contempla almeno un valore KO E almeno un OKM,
	 *  	=> NON ESISTONO DIAGNOSI MINIMALI CON MASCHERAMENTO 
	 * @throws IllegalArgumentException */
	public void generateSubsets() {

		Node temp;
		OutputNode tempOut;
		Iterator<Node> it = nodeSet.iterator();
		
		while ( it.hasNext() ) {
		 	temp = it.next();
		 	if (temp.getClass().equals(OutputNode.class)) {
		 		tempOut = (OutputNode) temp;
		 		if (tempOut.getResult().equals(OutputNode.ResultList.OK)) {
		 			InternalSubset newIS = new InternalSubset(tempOut);
		 			newIS.addAll( diagnosticableSubset(tempOut) );
		 			setOfIS.add(newIS);
		 		}
		   else if (tempOut.getResult().equals(OutputNode.ResultList.KO)) {
	 				StructuralConflict newSC = new StructuralConflict(tempOut);
		 			newSC.addAll( diagnosticableSubset(tempOut) );
		 			setOfSC.add(newSC);
		 		 }
		   else if (tempOut.getResult().equals(OutputNode.ResultList.OKM)) {
	 				StructuralMaskedConflict newSMC = new StructuralMaskedConflict(tempOut);
		 			newSMC.addAll( diagnosticableSubset(tempOut) );
		 			setOfSMC.add(newSMC);	 			
		   		 }
		   else throw new IllegalArgumentException();
		 		}
		}
		if ( setOfSC.size() == 0 || setOfSMC.size() == 0) {
			preFilteringAtLeast_1KO_1OKM = false;
			log.println(PREFILTERING);
		}
	}
//1_____________________________________________________________________________________________________________________________________	
	/** Sottrae ai C e ai CM l'unione di tutti gli IS. */
	public void subtrackUnionIS() {
		
		Iterator<StructuralConflict> itSC = setOfSC.iterator();
		while (itSC.hasNext()) 
			itSC.next().removeAll(unionIS);
		Iterator<StructuralMaskedConflict> itSMC = setOfSMC.iterator();
		while (itSMC.hasNext())
			itSMC.next().removeAll(unionIS);
	}
//1_____________________________________________________________________________________________________________________________________		
	/** Restituisce, dato un OutputNode, il sottoinsieme di nodi del grafo diretto dai quali l'OutputNode dipende.
	 *  Per prima cosa otterrà il set di tutti i suoi predecessori (fornito ricorsivamente dal GraphWalker,
	 *  Poi lo filtrerà con quelli diagnosticabili.
	 * @see GraphWalker */
	public Set<Diagnosticable> diagnosticableSubset(OutputNode node) {
		
		Set<Diagnosticable> filtered = new HashSet<Diagnosticable>();
		Set<Node> predecessors = graphWalker.getPredecessors(node);
		Iterator<Node> it = predecessors.iterator();
		while ( it.hasNext() ) {
			Node temp = it.next();
			if ( temp instanceof Diagnosticable )
				filtered.add((Diagnosticable) temp);
		}
		return filtered;	
	}
//2_____________________________________________________________________________________________________________________________________	
	/** Gestisce la chiamata a powerSet, eliminando l'insieme vuoto. */
	public void generatePowerSet() {

		SCpowerSet = Utilities.powerSet(setOfSC);
		SCpowerSet.remove(new HashSet<Integer>() );
	}
//2_____________________________________________________________________________________________________________________________________	
	/** Gestisce il dialogo per selezionare un insieme di StructuralConflict dall SCPowerSet.
	*   Prepara l'insieme complementare a quello scelto.
	*   @see SCselection
	*   @see SCnonSelection */
	@SuppressWarnings("unchecked")
	public void handleSCselectionDialog() {

		if(SCpowerSet.size()==1) 
			SCselection = (HashSet<StructuralConflict>) SCpowerSet.toArray()[0];
		else {
			SelectSCPowerSetDialog selectDialog = new SelectSCPowerSetDialog( gui, SELECTIONDIALOG, SCpowerSet);

	    	SCselection = selectDialog.getResult();
			if( SCselection == null) {
				abort = true;
				log.println(ABORT);
			} else {
				Iterator<StructuralConflict> itSC = setOfSC.iterator();
				while( itSC.hasNext() ) { 
					StructuralConflict temp = itSC.next();
					if( !SCselection.contains(temp) ) 
						SCnonSelection.add(temp);
				} 
			}
		}
	}
//_____________________________________________________________________________________________________________________________________	
	public void generateDifferencesIntersections() {

		Iterator<StructuralMaskedConflict> itSMC = setOfSMC.iterator();
		StructuralConflict tempSC;
		StructuralMaskedConflict tempSMC;
		while ( itSMC.hasNext() && preFilteringVoidSet ) {
				//supposizione pessimistica: tutte le sue intersezioni nulle => non ho diagnosi.
				boolean tempVoidSet = false;
				tempSMC = itSMC.next();
				Iterator<StructuralConflict> itSCselection = SCselection.iterator();
				while ( itSCselection.hasNext() ) {
					tempSC = itSCselection.next();
					
					Set<Diagnosticable> tempIntersect  = new HashSet<Diagnosticable>();
					tempIntersect.addAll( tempSMC.getOwnSet() );
					Set<Diagnosticable> tempDifference = new HashSet<Diagnosticable>();
					tempDifference.addAll( tempSMC.getOwnSet() );

					tempIntersect.retainAll(tempSC.getOwnSet());
					tempDifference.removeAll(tempSC.getOwnSet());

					collectionReadyForDiagnosis.add( tempIntersect );
					collectionReadyForDiagnosis.add( tempDifference );
					
					if ( tempIntersect.size()>0 && tempDifference.size()>0 ) 
						tempVoidSet = true;
				}
				if(!tempVoidSet) {
					preFilteringVoidSet = false;
					log.println(PREFILTERING);
				}
		}
	}
//3_____________________________________________________________________________________________________________________________________
	/** Si preoccupa di inserire nella collezione di insiemi finale anche gli StructuralConflict non utilizzati al passo 2. */
	public void addSCnonSelection() {
		Iterator<StructuralConflict> itSCnonSelection = SCnonSelection.iterator();
		while( itSCnonSelection.hasNext() )
			collectionReadyForDiagnosis.add( itSCnonSelection.next().getOwnSet() );
	}
//_____________________________________________________________________________________________________________________________________	
	/** Sottrae alla collezione pronta per l'algoritmo MHS eventuali set vuoti di Diagnosticable.
	 *  Metodo nato dal refactoring della logica, dovuto all'errata comprensione della specifica sul prodotto cartesiano per Grafi Disconnessi.
	 * 
	 * @return
	 */
	public boolean removeVoidDiagnosis() {
		for(int i=0; i<collectionReadyForDiagnosis.size(); i++)
			if(collectionReadyForDiagnosis.get(i).size()==0)
				collectionReadyForDiagnosis.remove(i);
		if(collectionReadyForDiagnosis.size()==0) {
			log.println(PREFILTERING);
			return false;
		 }
		else
			return true;
	}
//PASSI__________________________________________________________________________________________________________________________________	
	/** Passo 1. Sottrarre unionIS a tutti i Ci e i CMj, ottenendo C_i e CM_j  */
	public boolean step1() {
		log.println(HELLO + model.vertexSet().toString() );
		generateSubsets();
		if (preFilteringAtLeast_1KO_1OKM) {
			unionIS = Utilities.unionIS(setOfIS);
			subtrackUnionIS();
		}
		return preFilteringAtLeast_1KO_1OKM;
	}
	/** Passo 2. Per ogni CM_j, calcolare almeno un CM_j\C_i non vuoto ( e il CM_j intersect C_i non vuoto relativo). */	
	public boolean step2() {
		generatePowerSet();
		handleSCselectionDialog();
		if(!abort)
			generateDifferencesIntersections();
		return preFilteringVoidSet;
	}
	/** Passo 3. Calcolare i MHS della collezione costituita da:
	 * 		tutti gli insiemi di cui al passo 2
	 * 		tutti gli eventuali C_h che non hanno concorso a creare gli insiemi del passo 2
	 *  Ciascun MHS è una diagnosi minimale con mascheramento. */	
	public boolean step3() {
		addSCnonSelection(); 
		boolean notNullSetOfDiagnosis = removeVoidDiagnosis();
		setOfDiagnosis = Utilities.generateMHS(collectionReadyForDiagnosis);
		return notNullSetOfDiagnosis;
	}
	/** Metodo principale della classe. In tre passi differenti, modularizzati per ottimizzazioni, restituisce le Diagnosi. 
	 *  @return l'insieme di Diagnosis del problema. 
	 */
	public Set<Set<Diagnosticable>> getDiagnosis() {
		if( step1() )
			if( step2() && !abort)
				step3();
		return setOfDiagnosis;
     }
}  //end of class