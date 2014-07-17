package controller;


/**rappresenta un Controller che ascolta un particolare
 * JButton. Un Controller di questo tipo possiede:
 * <ul>
 *  <li>delle routine per abilitare/disabilitare tutti i pulsanti</li>
 *  <li>dei metodi per prevenire i <tt>NullStaticObjectException</tt>
 *  che molto probabilmente verranno generate dall'azione del pulsante</li>
 * </ul> 
 * 
 * il ButtonHandler deve essere esteso solo per i JButton e <strong>non</strong> per le altre
 * classi che comunque implementano ActionListener: questo perche' la classe offre servizi
 * riguardo i pulsanti, non altre classi (come i MenuItem)
 * 
 * @author Koldar
 * @version 1.4
 */
public abstract class ButtonHandlerController extends AbstractButtonHandlerController{

	
	protected ButtonHandlerController(int _id) {
		super(_id);
	}
	
	

}
