package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graph.comp.OutputNode;
import graph.comp.OutputNode.ResultList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/** Un ResultRenderer è una possibile rappresentazione grafica dell'oggetto Result, specificato in graph.comp.
 *  Sostanzialmente, l'oggetto prebuild migliore per rappresentare un Result è un RadioButton, ma nulla vieta
 *  di averne altre viste.  
 * 
 * @author michael
 * @version 1.1
 * @see ObservationPanel
 * @see Result
 */

public class ResultRenderer extends JPanel implements ActionListener{

	private static final long serialVersionUID = 6712305150549792260L;
	
	//TODO un giorno sarebbe bello collegare (magari tramite un array) i 3 button OK, KO,OKM 
	//     così ci evitiamo tutti quei if noiosi
	// NO! Valutando questa ipotesi si è scoperto che la gestione sarebbe complicatissima, quindi se un giorno 
	// dovesse tornarci il dubbio, ricordarsi che l'idea è stata scartata!
	
	/**rappresenta il pulsante per impostare questa uscita del grafo al valore di OK*/
	public JRadioButton ok;
	/**rappresenta il pulsante per impostare questa uscita del grafo al valore di KO*/
	public JRadioButton ko;
	/**rappresenta il pulsante per impostare questa uscita del grafo al valore di OKM*/
	public JRadioButton okm;
	/**rappresenta il contenitore virtuale che contiene i 3 radiobutton*/
	public ButtonGroup sizeGroup;
	/**è una copia locale dell'outputNode protagonista del Result. */
	private OutputNode outputnode;

	/**costruisce un nuovo pannello di oracolo collegante un Output Node.
	 * @param outputnode
	 */
	public ResultRenderer(OutputNode outputnode) {
		super();
		
		this.outputnode = outputnode;
		this.sizeGroup = new ButtonGroup();

		ok = new JRadioButton(ResultList.OK.toString());
		ok.addActionListener(this);
		ko = new JRadioButton(ResultList.KO.toString());
		ko.addActionListener(this);
		okm = new JRadioButton(ResultList.OKM.toString());
		okm.addActionListener(this);
		//TODO in futuro potrebbe essere interessante applicare un command!okm.setActionCommand(OKM);
		sizeGroup = new ButtonGroup();
		sizeGroup.add(ok);
		sizeGroup.add(ko);
		sizeGroup.add(okm);

		//per il futuro... si fa proprio così! TODO implementare un qualcosa che eviti ripetitività!
		Box sizeBox = Box.createHorizontalBox();
		sizeBox.add(ok);
		sizeBox.add(ko);
		sizeBox.add(okm);
		sizeBox.setBorder(BorderFactory.createTitledBorder(outputnode.getName()));
		
		this.setState();
		this.add(sizeBox);
		this.setVisible(true);
	}

	/**
	 * @return the outputnode
	 */
	public OutputNode getOutputnode() {
		return outputnode;
	}

	/**
	 * @param outputnode the outputnode to set
	 */
	public void setOutputnode(OutputNode outputnode) {
		this.outputnode = outputnode;
	}
	
	private void setState(){
		if (this.outputnode.getResult()==ResultList.OK){
			this.ok.setSelected(true);
		}
		if (this.outputnode.getResult()==ResultList.KO){
			this.ko.setSelected(true);
		}
		if (this.outputnode.getResult()==ResultList.OKM){
			this.okm.setSelected(true);
		}
	}

	/**lista di istruzioni da eseguire quando l'utente modifica lo stato
	 * di un radio button
	 * 
	 * @param e l'evento ricevuto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.ok.isSelected()){
			this.outputnode.setResult(ResultList.OK);
		}
		if (this.ko.isSelected()){
			this.outputnode.setResult(ResultList.KO);
		}
		if (this.okm.isSelected()){
			this.outputnode.setResult(ResultList.OKM);
		}
	}
}