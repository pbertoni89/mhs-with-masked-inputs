package test;

	// Fri Oct 25 18:07:43 EST 2004
	//
	// Written by Sean R. Owens, sean at guild dot net, released to the
	// public domain.  Share and enjoy.  Since some people argue that it is
	// impossible to release software to the public domain, you are also free
	// to use this code under any version of the GPL, LPGL, or BSD licenses,
	// or contact me for use of another license.
	// http://darksleep.com/player

	// A very simple custom dialog that takes a string as a parameter,
	// displays it in a JLabel, along with two Jbuttons, one labeled Yes,
	// and one labeled No, and waits for the user to click one of them.

	import javax.swing.JDialog; 
	import java.awt.event.ActionListener;
	import javax.swing.JPanel;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JButton;
	import java.awt.event.ActionEvent;

	/**
	 * @author   patrizio
	 */
	public class CustomDialog extends JDialog implements ActionListener {
	    private JPanel myPanel = null;
	    private JButton yesButton = null;
	    private JButton noButton = null;
	    /**
		 * @uml.property  name="answer"
		 */
	    private boolean answer = false;
	    /**
		 * @return
		 * @uml.property  name="answer"
		 */
	    public boolean getAnswer() { return answer; }

	    // modal = true  => la finestra � "HEAVY", ovvero costringe l'utente a interagire e a terminarla prima di ritornare su
	    public CustomDialog(JFrame frame, boolean modal, String myMessage) {
		super(frame, modal);
		myPanel = new JPanel();
		getContentPane().add(myPanel);
		myPanel.add(new JLabel(myMessage));
		yesButton = new JButton("Yes");
		yesButton.addActionListener(this);
		myPanel.add(yesButton);	
		noButton = new JButton("No");
		noButton.addActionListener(this);
		myPanel.add(noButton);	
		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) {
		if(yesButton == e.getSource()) {
		    System.err.println("User chose yes.");
		    answer = true;
		    setVisible(false);
		}
		else if(noButton == e.getSource()) {
		    System.err.println("User chose no.");
		    answer = false;
		    setVisible(false);
		}
	    }
	    
	}