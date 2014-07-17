package toolkit;

/**
 * rappresenta una classe contenente un singolo boolean  che puo' modificando a piacimento; la classe e' particolarmente utile se si vuole fare in modo che un boolean vada in un interfaccia ma che sia facilmente modificabile 
 * @author   Koldar
 * @version   1.0
 */
public class BooleanValue {

	/**
	 * rappresenta il valore del boolean in questo momento
	 * @uml.property  name="value"
	 */
	private boolean value;
	
	/**costurisce una nuova classe contenente unicamente un boolean
	 * 
	 * @param b il valore iniziale che {@link #value} deve assumere
	 */
	public BooleanValue(boolean b){
		this.value=b;
	}

	/**
	 * @return   the value
	 * @uml.property  name="value"
	 */
	public boolean isValue() {
		return value;
	}

	/**
	 * @param value   the value to set
	 * @uml.property  name="value"
	 */
	public void setValue(boolean value) {
		this.value = value;
	}
	
}
