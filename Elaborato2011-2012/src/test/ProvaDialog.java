package test;

// Fri Oct 25 18:07:43 EST 2004
//
// Written by Sean R. Owens, sean at guild dot net, released to the
// public domain.  Share and enjoy.  Since some people argue that it is
// impossible to release software to the public domain, you are also free
// to use this code under any version of the GPL, LPGL, or BSD licenses,
// or contact me for use of another license.
// http://darksleep.com/player

// This is just a very simple driver to show the use of CustomDialog,
// but I suppose it also demonstrates (minimally) how to use a
// JButton.

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension; 

public class ProvaDialog implements ActionListener {
	
    JFrame mainFrame = null;
    JButton myButton = null;

    public ProvaDialog() {
	mainFrame = new JFrame("TestTheDialog Tester");
        mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {System.exit(0);}
            });
	myButton = new JButton("Test the dialog!");
	myButton.addActionListener(this);
	mainFrame.setLocationRelativeTo(null);
	mainFrame.getContentPane().add(myButton);
	mainFrame.pack();
	mainFrame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
	if(myButton == e.getSource()) {
	    System.err.println("Opening dialog.");
	    CustomDialog myDialog = new CustomDialog(mainFrame, true, "Do you like Java?");
	    System.err.println("After opening dialog.");
	    if(myDialog.getAnswer()) {
		System.err.println("The answer stored in CustomDialog is 'true' (i.e. user clicked yes button.)");
	    }
	    else {
		System.err.println("The answer stored in CustomDialog is 'false' (i.e. user clicked no button.)");
	    }
	}
    }

    public static void main(String argv[]) {

	ProvaDialog tester = new ProvaDialog();
    }
}