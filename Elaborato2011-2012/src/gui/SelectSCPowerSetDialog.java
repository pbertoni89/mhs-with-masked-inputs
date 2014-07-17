package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JDialog;

import alg.sets.StructuralConflict;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Una finestra di dialogo dove l'utente opera una scelta su quale insieme di Structural Conflicts, nell'insieme delle parti di tutti gli StructuralConflict eccetto quello vuoto, utilizzare come base per il punto 2) (nella specifica dell'algoritmo individuato anche come punto b) ). 
 * @author   giskard
 * @version   1.1
 */
public class SelectSCPowerSetDialog extends JDialog implements ActionListener {

        private static final long serialVersionUID = -2132166491131311182L;
        public final static String OK = "Ok";
        public static final String RESET="Reset";
        public GridBagLayout layout;
        public static GridBagConstraints lim;
        
        private static final Dimension DIALOG_SIZE = new Dimension(300, 300);
        
        private JButton okButton;
        private Vector<Set<StructuralConflict>> nodeSetList;
        /**
		 * l'etichetta dello Structural Conflict selezionato
		 * @uml.property  name="jButtonSelectedLabel"
		 */
        private String JButtonSelectedLabel;
        /**rappresenta il container VIRTUALE (non grafico) che conterrà come un'unica scelta i vari RadioButton*/
        private ButtonGroup group;
        /**rappresenta il container che ingloberà tutte i radiobuttons per scegliere il set desiderato*/
        private Box borderBox;
        /**
		 * rappresenta l'insieme che l'utente ha scelto per eseguire l'analisi minimale; viene settata in ActionOPerformed
		 * @uml.property  name="result"
		 */
        private Set<StructuralConflict> result;
        
        /**
		 * @uml.property  name="biggerPanel"
		 * @uml.associationEnd  
		 */
        private GraphicStackComponent biggerPanel;
        
        /** finestra di dialogo con l'utente per selezionare lo Structural Conflict sul quale operare
         * 
         * @param frame
         * @param myMessage
         * @param _nodeSetList 
         */
        public SelectSCPowerSetDialog(JFrame frame,String myMessage ,Set<Set<StructuralConflict>> _nodeSetList){
                super(frame,true);
                this.setTitle(myMessage);
                this.layout=new GridBagLayout();
                SelectSCPowerSetDialog.lim = new GridBagConstraints();
                this.setLayout(layout);
                
                boolean first = true;
                this.group=new ButtonGroup();
                this.borderBox=new Box(BoxLayout.Y_AXIS);
                this.okButton=new JButton(OK);
                biggerPanel = new GraphicStackComponent();
                
                //aggiungo la Box, utile per aggiungere i bottoni senza modificare il layout ogni volta
                biggerPanel.setGridBagConstraints(1, 1, 0, 0, GridBagConstraints.BOTH, 1, 2);  
                biggerPanel.addComponentInStack(borderBox, Color.BLACK);
                // layout.setConstraints(borderBox, lim);
                //biggerPanel.add(borderBox);
                
                biggerPanel.setGridBagConstraints(1, 1, 0, 2, GridBagConstraints.BOTH, 1, 1);
                okButton.addActionListener(this);
                biggerPanel.addComponentInStack(okButton, Color.BLACK);
                
                nodeSetList = new Vector<Set<StructuralConflict>>(_nodeSetList);
                Iterator<Set<StructuralConflict>> iterator = nodeSetList.iterator();
                
                while (iterator.hasNext()){
                        String JBLabel=iterator.next().toString();
                        JRadioButton temp=new JRadioButton(JBLabel);

                        temp.setActionCommand(JBLabel);
                        temp.addActionListener(this);
                        this.borderBox.add(temp);
                        this.group.add(temp);
                        if(first) {
                                first = false;
                                //rendo graficamente di default
                                temp.setSelected(true);
                                //rendo strutturalmente di default
                                JButtonSelectedLabel = temp.getText();
                        }
                } 
                this.add(biggerPanel);
                setLocationRelativeTo(frame);
                this.setSize(DIALOG_SIZE);
                setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
                if ((e.getSource()==this.okButton)) {
                    result = findSCfromLabel(JButtonSelectedLabel);
                    this.setVisible(false);
                    this.dispose();
                }
                else
                    JButtonSelectedLabel = e.getActionCommand();
        }
        
        public Set<StructuralConflict> findSCfromLabel( String tofind) {
            Set<StructuralConflict> found = null;
            for( Set<StructuralConflict> toCompare  : nodeSetList)
            	if(tofind.equalsIgnoreCase(toCompare.toString()) )
                    found =  toCompare;     
            return found;
        }       
        
        /**
		 * @return
		 * @uml.property  name="jButtonSelectedLabel"
		 */
        public String getJButtonSelectedLabel() {
                return JButtonSelectedLabel;
        }

        /**
		 * @param  jButtonSelectedLabel
		 * @uml.property  name="jButtonSelectedLabel"
		 */
        public void setJButtonSelectedLabel(String jButtonSelectedLabel) {
                JButtonSelectedLabel = jButtonSelectedLabel;
        }
        
        /**
		 * @return
		 * @uml.property  name="result"
		 */
        public Set<StructuralConflict> getResult() {
        	return result;
        }
}