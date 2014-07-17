package toolkit;

import java.io.PrintStream;

import outputstream.StringOutputStream;

/**rappresenta una flusso di dati stampabile avente come base fondamentale
 * un flusso OutputStream basato su stringa. per maggiori informazioni vedere
 * le classi in see
 * 
 * @author Koldar
 * @see java.io.OutputStream
 * @see java.io.PrintStream
 * @see gui.LogPanel
 *
 */
public class StringPrintStream extends PrintStream{

	public StringPrintStream(StringOutputStream out) {
		super(out);
	}
	public <OBJECT> void reprintln(OBJECT str){
		((StringOutputStream)this.out).reset();
		this.println(str.toString());
	}
	public void reprintln(Exception error){
		this.reprintln(error.getMessage());
	}
	public void reprintln(){
		((StringOutputStream)this.out).reset();
	}

}
