package test;

import javax.swing.JFrame;

import gui.LogPanel;

public class LogPanelTest {

	public static void main(String[] args) throws InterruptedException{
		LogPanel log=new LogPanel("img_warning.png","premi <INVIO> per continuare...");
		
		JFrame frame=new JFrame("test");
		frame.add(log);
		frame.setSize(500,400);
		frame.setVisible(true);
		
		Thread.sleep(2000);
		log.out.println("effetto riuscito!!!");
		Thread.sleep(2000);
		/*a.println("ciao"+true+" 5 "+6);
		Thread.sleep(2000);
		a.reset();*/
		
	}
}
