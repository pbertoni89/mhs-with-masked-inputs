package controller;

import event.numberevent.NumberEvent;
import event.numberevent.NumberListener;
import event.numberevent.Range;

/**l'interfaccia consente alla classe implementante di avere l'abilita' di ricevere
 * informazioni circa lo stato di uno StateNumber. Questo StateNumber dovrebbe contenere
 * informazioni circa lo stato dell'utente del software: in effetti l'utente puo' essere
 * in 2 "modalita'":
 * <ul>
 *  <li>l'utente puo' essere in <strong>idle mode</strong>: in tale modalita' l'utente
 *  sta semplicemente guardando il grafico, gestendo al massimo gli observers.</li>
 *  <li>l'utente puo' essere in <strong>edit mode</strong>: in tale modalita' l'utente sta
 *  seguendo un wizard per inserire (o eliminare) un qualcosa dal grafo oppure sta creandoi
 *  la sua anlisi minimale con mascheramento: qualunque essa sia, l'utente non deve poter
 *  incominciare un nuovo wizard fino a quando non ha finito il precedente. Questo
 *  stato aiuta proprio a raggiungere questo obiettivo</li>
 * </ul>
 * 
 * Accanto a questi 2 stati utente, ce ne e' un terzo che e' piuttosto virtuale: e' il 
 * "start state", cioe' lo stato in cui si trova l'applicazione all'inizio del suo ciclo di
 * vita; lo start state esiste solo per poter eseguire una prima transizione verso lo stato
 * idling in modo da eseguire determinate azioni.
 * 
 * @author Koldar
 * @version 1.0
 */
//TODO edit la javadoc quando si fa il refactoring!!! 
public interface UserStateListener extends NumberListener<Integer>{

	/**indica che l'utente non sta eseguendo nulla di particolare*/
	public static final int IDLE=0;
	/**indica che l'utente sta compiendo un'azione che modifica il grafo*/
	public static final int EDITING=1;
	/**indica che l'utente ha appena inizializzato l'applicazione*/
	public static final int START=2;
	
	/**rappresenta la lista di istruzioni da eseguire quando l'utente
	 * non sta modificando nulla
	 * 
	 * @param e
	 */
	@Range(inf = IDLE, sup = IDLE)
	public void idleStateAction(NumberEvent<Integer> e);
	
	/**rappresenta la lista di istruzioni da eseguire quando l'utente
	 * sta modificando qualcosa nel grafo
	 * 
	 * @param e
	 */
	@Range(inf = EDITING, sup = EDITING)
	public void EditingStateAction(NumberEvent<Integer> e);
	
	/**rappresenta la lista di istruzioni da eseguire quando l'utente
	 * ha appena avviato l'applicazione
	 * 
	 * @param e
	 */
	@Range(inf= START, sup= START)
	public void startStateAction(NumberEvent<Integer> e);
}
