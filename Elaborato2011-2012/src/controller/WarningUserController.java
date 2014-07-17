package controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import graph.state.CorrectModelState;
import graph.state.WarningModelState;

import toolkit.AutoRewindClip;
import toolkit.ValidatorMessage;
import controller.validator.ValidatorErrorException;
import controller.validator.ValidatorWarningException;
import controller.validator.warning.ActionNodeNotFoundException;
import controller.validator.warning.InputNodeNotFoundException;
import controller.validator.warning.OutputNodeNotFoundException;

/**rappresenta un Controller che necessita, per il suo corretto funzionamento,
 * di sapere se il grafo possiede o meno dei warning. Estendere questa classe significa
 * possodere una lista dei warning correnti di cui il grafo soffre.
 * 
 * @author Koldar
 * @version 1.2
 * @see Controller
 *
 */
public abstract class WarningUserController extends Controller{
	
	/**indica il nome del file wav rappresentante il suono di errore*/
	private static final String ERRORSOUNDNAME="ErrorSound.wav";
	/**contiene la lista di warning attualmente presenti nel grafo; questo potrebbe servire 
	 * per migliorare l'output del LogPanel nonché evitare rilevazioni di warning. All'inizio
	 * del software la lista dei warning ha 3 elementi: InputNodeNotFound,ActionNodeNotFound
	 * e OutputNodeNotFound; questo per essere in sincronia con il fatto che all'inizio
	 * lo schema editabile e' vuoto
	 */
	protected static List<ValidatorWarningException> warningList;
	/**rappresenta il suono che deve essere emanato quando l'utente crea un errore nel grafo corrente*/
	private static AutoRewindClip errorSound;
	
	static{
		//aggiunta della musica
		try {
			errorSound=new AutoRewindClip(WarningUserController.class,ERRORSOUNDNAME);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException error){
			errorSound=null;
			System.out.println("musica non supportata");
		} catch (NullPointerException error){
			errorSound=null;
			System.out.println("musica non supportata");
		}
		//creazione lista dei warning
		warningList=new Vector<ValidatorWarningException>(0,1);
		WarningUserController.warningList.add(new InputNodeNotFoundException());
		WarningUserController.warningList.add(new ActionNodeNotFoundException());
		WarningUserController.warningList.add(new OutputNodeNotFoundException());
	}

	protected WarningUserController(int _id) {
		super(_id);
	}
	
	/**dato un messaggio inviato dal Validator indicante gli errori che si sono manifestati
	 * durante i suoi controlli esegue le seguenti azioni:
	 * <ul>
	 *  <li>in caso di <strong>errore</strong> provvede ad inviare un messaggio all'utente</li>
	 *  <li>in caso di <strong>warning</strong> esegue l'azione richiesta dall'utente, aggiorna
	 *  la lista di warning ed avverte l'utente</li>
	 *  <li>in caso di <strong>azione corretta</strong> esegue l'azione ed avverte l'utente</li>
	 * </ul>
	 * 
	 * <p>il metodo inoltre scrive i suoi messaggi per l'utente sul log dell'applicazione <strong>sovrascivendo</strong>
	 * i messaggi scritti fino ad ora. Se si volessero vedere i messaggi creati da {@link #controlErrorinGraph(ValidatorMessage)}
	 * e' necessario non usare <tt>reprintln</tt> del LogPanel 
	 * 
	 * @param msg il messaggio generato dal Validator
	 * @throws NullStaticObjectException in caso che l'applicazione non sia stata ancora creata
	 */
	protected void controlErrorinGraph(ValidatorMessage msg) throws NullStaticObjectException{
		log.reprintln();
		if (msg.getErrorOccured()!=null){
			log.println(msg.getErrorOccured().getMessage());
			this.printOnLogWarningList();
			if (errorSound!=null){
				errorSound.start();
			}
		}else{
			if (msg.getWarningoccured().size()!=0){
				WarningUserController.warningList=msg.getWarningoccured();
				this.printOnLogWarningList();
				Controller.getModel().setModelState( new WarningModelState() );
			}else{
				Controller.getModel().setModelState( new CorrectModelState() );
			}
			this.executeValidatedAction();
			log.println("Azione eseguita con successo");
		}
	}
	
	/**stampa sul logPanel la lista di warning di cui il grafo soffre. Questo metodo non
	 * pulisce il LogPanel quindi tale operazione deve essere eseguita prima di richiamare questo metodo
	 * 
	 */
	protected void printOnLogWarningList(){
		for (ValidatorWarningException w: WarningUserController.warningList){
			log.println(w.getMessage());
		}
	}
	
	/**Incorpora la lista di istruzioni che devono essere eseguite qualora il programma
	 * intercetti un errore/warning nel grafo
	 * 
	 * @param error indica l'eventuale errore riscontrato nel grafo
	 * @param _warninglist indica l'eventuale lista di warning riscontrati nel grafo
	 * @throws NullStaticObjectException in caso in cui il model o l'application non siano ancora state collegate con questo controller
	 */
	protected void controlErrorinGraph(ValidatorErrorException error,ValidatorWarningException ..._warninglist) throws NullStaticObjectException{
		if (error!=null){
			log.reprintln(error.getMessage());
		}else{
			if (_warninglist!=null){
				WarningUserController.warningList.clear();
				for (int i=0;i<_warninglist.length;i++){
					WarningUserController.warningList.add(_warninglist[i]);
				}
				log.reprintln();
				for (int i=0;i<warningList.size();i++){
					log.println(warningList.get(i).getMessage());
				}
				Controller.getModel().setModelState( new WarningModelState() );
			}else{
				Controller.getModel().setModelState( new CorrectModelState() );
				log.reprintln("tutto ok. nessun errore/warning individuato.");
			}
			this.executeValidatedAction();
		}
	}
	/**rappresenta l'insieme delle istruzioni che devono essere eseguite quando il Validator ha
	 * confermato che nel grafo corrente non sono presenti errori. Questo vuol dire che l'azione
	 * da inserire in questo metodo coincide con l'azione descritta dalla classe includente il 
	 * metodo stesso. Il fatto poi che il Validator abbia dato il nulla osta non significa che
	 * il grafo sia esente di eventuali Warning.
	 * <p>l'azione eseguita dall'execute Action e' espressa dopo la verifica della correttezza del
	 * grafo. Quindi quando questo metodo viene eseguito il computer ha gia' aggiornato la validita'
	 * del grafo
	 * 
	 */
	public abstract void executeValidatedAction();

}
