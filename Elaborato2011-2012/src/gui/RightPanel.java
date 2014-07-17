package gui;

import graph.state.CorrectModelState;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import controller.Controller;

/**
 * Rappresenta il pannello contenente: l'ObservationPanel, lo statusLog e il pulsante di richiesta di analisi minimale.
 * @author   Micheal
 * @version   1.1
 */
public class RightPanel extends GraphicNoStackComponent{

	private static final long serialVersionUID = 1075679923442920339L;
	

	
	/**
	 * Stringa del contenuto del bottone di Execute Diagnosis
	 * @uml.property  name="eXECUTEDIAGNOSIS"
	 */
	private static final String EXECUTEDIAGNOSIS = "Execute Diagnosis";
	/**
	 * Pannello dove sono conenute le osservazioni
	 * @uml.property  name="observationPanel"
	 * @uml.associationEnd  
	 */
	private ObservationPanel observationPanel;
	/**
	 * Pannello che indica lo stato del modello
	 * @uml.property  name="statuslog"
	 * @uml.associationEnd  
	 */
	private StatusLog statuslog;
    /**
	 * Bottone che lancia l'esecuzione della diagnosi
	 * @uml.property  name="executeMinimalDiagnosis"
	 */
	private JButton executeMinimalDiagnosis;
	/**
	 * Scrollbar associata all'ObservationPanel
	 * @uml.property  name="scrollObservation"
	 */
	private JScrollPane scrollObservation;
	
	
	
	public RightPanel(){		
		
		//creo i componente che andranno aggiunti
		statuslog= new StatusLog (Color.red,CorrectModelState.CORRECTSTATUS);
		observationPanel = new ObservationPanel();
		executeMinimalDiagnosis=new JButton(EXECUTEDIAGNOSIS);
		scrollObservation=new JScrollPane(observationPanel);
		contenitore = new GraphicNoStackComponent();
		
		

		//aggiungo l'ObesrvationPanel che è contenuto il uno scrollbar
		contenitore.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
		contenitore.layout.setConstraints(observationPanel, contenitore.lim);
		
		contenitore.add(scrollObservation,contenitore.lim);

		
		//inserisco lo StatePanel, che per ora � un bottone
		contenitore.setGridBagConstraints(1, 0.3, 0, 1, GridBagConstraints.BOTH, 1, 1);
		contenitore.add(statuslog, contenitore.lim);

		//colorazione del right panel
		this.statuslog.setOpaque(true);
		this.statuslog.setBackground(Color.red);
		
		//aggiungi il bottono per eseguire la diagnosi minimale
		contenitore.setGridBagConstraints(1, 0.3, 0, 2, GridBagConstraints.BOTH, 1, 1);
		contenitore.add(executeMinimalDiagnosis, contenitore.lim);
		
		//aggiungo il contnitore nel RightPanel
		this.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 1);
		this.add(contenitore, this.lim);
		
		
		this.setVisible(true);
	}
	/**imposta lo stato dello StatusLog all'interno di questo contenitore
	 * 
	 * @param str la stringa da inserire al centro dello StatusLog
	 * @param color il colore da usare come sfondo nello StatusLog
	 */
	protected void SetState(String str,Color color){
		this.statuslog.setState(str, color);
	}
	
	public RightPanel(Controller minimalanalysis){
		this();
		this.addListeners(minimalanalysis);
	}
	
	/**aggiunge l'ascoltatore del pulsante di analisi minimale
	 * 
	 * @param minimalanalysis
	 */
	public void addListeners(Controller minimalanalysis){
		this.executeMinimalDiagnosis.addActionListener((ActionListener) minimalanalysis);
	}
	
	
	
	
	/**
	 * @return   the executeMinimalDiagnosis
	 * @uml.property  name="executeMinimalDiagnosis"
	 */
	public JButton getExecuteMinimalDiagnosis() {
		return executeMinimalDiagnosis;
	}
	
	/**
	 * @return   the statuslog
	 * @uml.property  name="statuslog"
	 */
	public StatusLog getStatuslog() {
		return statuslog;
	}
	/**
	 * @param statuslog the statuslog to set
	 */
	/**
	 * @param statuslog  the statuslog to set
	 * @uml.property  name="observationPanel"
	 */
	public ObservationPanel getObservationPanel() {
		return observationPanel;
	}
	/**
	 * @param observationPanel   the observationPanel to set
	 * @uml.property  name="observationPanel"
	 */
	public void setObservationPanel(ObservationPanel observationPanel) {
		this.observationPanel = observationPanel;
	}
	/**
	 * @return   the contenitore
	 * @uml.property  name="contenitore"
	 */
	public GraphicNoStackComponent getContenitore() {
		return contenitore;
	}
	/**
	 * @param contenitore   the contenitore to set
	 * @uml.property  name="contenitore"
	 */
	public void setContenitore(GraphicNoStackComponent contenitore) {
		this.contenitore = contenitore;
	}
	/**
	 * @return   the scrollObservation
	 * @uml.property  name="scrollObservation"
	 */
	public JScrollPane getScrollObservation() {
		return scrollObservation;
	}
	/**
	 * @param scrollObservation   the scrollObservation to set
	 * @uml.property  name="scrollObservation"
	 */
	public void setScrollObservation(JScrollPane scrollObservation) {
		this.scrollObservation = scrollObservation;
	}
	/**
	 * @return   the serialversionuid
	 * @uml.property  name="serialVersionUID"
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return   the executediagnosis
	 * @uml.property  name="eXECUTEDIAGNOSIS"
	 */
	public static String getExecutediagnosis() {
		return EXECUTEDIAGNOSIS;
	}
	/**
	 * @param executeMinimalDiagnosis   the executeMinimalDiagnosis to set
	 * @uml.property  name="executeMinimalDiagnosis"
	 */
	public void setExecuteMinimalDiagnosis(JButton executeMinimalDiagnosis) {
		this.executeMinimalDiagnosis = executeMinimalDiagnosis;
	}
	/**
	 * @uml.property  name="contenitore"
	 * @uml.associationEnd  
	 */
	private GraphicNoStackComponent contenitore;
	
	/**
	 * @param  statuslog
	 * @uml.property  name="statuslog"
	 */
	public void setStatuslog(StatusLog statuslog) {
		this.statuslog = statuslog;
	}


}
