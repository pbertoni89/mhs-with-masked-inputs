
package controller.validator;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import graph.comp.Flow;
import graph.comp.Node;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;

import toolkit.UserAction;
import toolkit.ValidatorMessage;

import controller.validator.error.IngoingFlowInInputNodeErrorManager;
import controller.validator.error.InputNodeLeadsToDirectOutputNodeErrorManager;
import controller.validator.error.LoopDetectedInGraphErrorManager;
import controller.validator.error.MultipleIngoingFlowInOutputNodeErrorManager;
import controller.validator.error.OutgoingFlowFromOutputNodeErrorManager;
import controller.validator.error.PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager;
import controller.validator.warning.ActionNodeNotFoundException;
import controller.validator.warning.ActionNodeNotFoundWarningManager;
import controller.validator.warning.IngoingFlowInActionNodeNotFoundWarningManager;
import controller.validator.warning.IngoingFlowInOutputNodeNotFoundWarningManager;
import controller.validator.warning.InputNodeNotFoundException;
import controller.validator.warning.InputNodeNotFoundWarningManager;
import controller.validator.warning.OutgoingFLowFromActionNodeNotFoundWarningManager;
import controller.validator.warning.OutgoingFlowFromInputNodeNotFoundWarningManager;
import controller.validator.warning.OutputNodeNotFoundException;
import controller.validator.warning.OutputNodeNotFoundWarningManager;


/**rappresenta il componente dedito a rilevare gli eventuali errori di consistenza grafo
 * che l'utente, nelle sua modifiche, pu� creare. Il Validator � pilotato dal controller
 * attraverso opportuni segnali che indicano quali verifiche sul grafo devono essere fatte
 * (i.e. rileva se ci sono cicli, rileva se un OutputNode ha un uscita eccetera). Il Validator
 * riceve gli input, elabora la sua risposta, quindi comunica al mittente i suoi risultati
 * tramite un pacchetto contenente un ValidatorMessage.
 * 
 * @author Koldar
 * @version 1.1
 * @see Controller
 *
 */
public final class Validator {
	
	/**contiene i vari manager dei vari errori*/
	private Vector<ValidatorErrorManager<?>> errorManagerArchive; 

	/**costruisce un nuovo validator che contiene gi� di suo tutte le routine per
	 * gestire i vari errori.
	 */
	//TODO un giorno gli errori e i warning dovranno essere creati dal Main ed eliminare quei fastidiosi ID! >.<"
	public Validator(){
		super();
		//ERRORS
		this.errorManagerArchive=new Vector<ValidatorErrorManager<?>>(0,1);
		this.errorManagerArchive.add(IngoingFlowInInputNodeErrorManager.IDERROR,new IngoingFlowInInputNodeErrorManager());
		this.errorManagerArchive.add(OutgoingFlowFromOutputNodeErrorManager.IDERROR,new OutgoingFlowFromOutputNodeErrorManager());
		this.errorManagerArchive.add(PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager.IDERROR,new PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager());
		this.errorManagerArchive.add(LoopDetectedInGraphErrorManager.IDERROR,new LoopDetectedInGraphErrorManager());
		this.errorManagerArchive.add(MultipleIngoingFlowInOutputNodeErrorManager.IDERROR,new MultipleIngoingFlowInOutputNodeErrorManager());
		//MI ERO INVENTATO UN ERRORE XD this.errorManagerArchive.add(MultitpleOutgoingFlowFromInputNodeErrorManager.IDERROR,new MultitpleOutgoingFlowFromInputNodeErrorManager());
		//MI ERO INVENTATO UN ERRORE XD this.errorManagerArchive.add(MultipleIngoingFlowInActionNodeErrorManager.IDERROR,new MultipleIngoingFlowInActionNodeErrorManager());
		this.errorManagerArchive.add(InputNodeLeadsToDirectOutputNodeErrorManager.IDERROR,new InputNodeLeadsToDirectOutputNodeErrorManager());
		//WARNING
		this.errorManagerArchive.add(ActionNodeNotFoundWarningManager.IDWARNING,new ActionNodeNotFoundWarningManager());
		this.errorManagerArchive.add(IngoingFlowInActionNodeNotFoundWarningManager.IDWARNING,new IngoingFlowInActionNodeNotFoundWarningManager());
		this.errorManagerArchive.add(IngoingFlowInOutputNodeNotFoundWarningManager.IDWARNING,new IngoingFlowInOutputNodeNotFoundWarningManager());
		this.errorManagerArchive.add(InputNodeNotFoundWarningManager.IDWARNING,new InputNodeNotFoundWarningManager());
		this.errorManagerArchive.add(OutgoingFLowFromActionNodeNotFoundWarningManager.IDWARNING,new OutgoingFLowFromActionNodeNotFoundWarningManager());
		this.errorManagerArchive.add(OutgoingFlowFromInputNodeNotFoundWarningManager.IDWARNING,new OutgoingFlowFromInputNodeNotFoundWarningManager());
		this.errorManagerArchive.add(OutputNodeNotFoundWarningManager.IDWARNING,new OutputNodeNotFoundWarningManager());
	}
	
	
	/**controlla se i comandi eseguiti dall'utente possono condurre ad una condizione di errore
	 * per il grafo. Un controller passa al validator una serie di boolean che indica quali
	 * controlli desidera che vengano eseguiti. A seconda di quali boolean sono settati a TRUE,
	 * il validator esegue vari controlli: quindi <strong>ad ogni controllo � associato un boolean
	 * di richiesta</strong>. Il validator esegue i seguenti passi:
	 * <ul>
	 *  <li>esegue una copia del grafo</li>
	 *  <li>applica l'istruzione dell'utente a prescindere dalla sua esattezza sulla copia</li>
	 *  <li>esegue la serie di controlli sulla copia</li>
	 *  <li>in caso di errore, si premura di avvertire il Controller richiedente</li>
	 *  <li>elimina la copia</li>
	 * </ul>
	 * di seguito viene esposta la tabella che associa ad ogni boolean il relativo rilevatore di errore/warning:
	 * <p>
	 * <table border="1">
	 *  <tr>
	 *   <td>posizione boolean</td>
	 *   <td>nome rilevatore</td>
	 *   <td>livello di rilevazione</td>
	 *  </tr>
	 *  <tr>
	 *   <td>1</td>
	 *   <td>IngoingFlowInInputNodeErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>2</td>
	 *   <td>OutgoingFlowFromOutputNodeErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>3</td>
	 *   <td>PredecessorOfOutputNodeHasMultipleSuccessorsErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>4</td>
	 *   <td>LoopDetectedInGraphErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>5</td>
	 *   <td>MultipleIngoingFlowInOutputNodeErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>6</td>
	 *   <td>InputNodeLeadsToDirectOutputNodeErrorManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>7</td>
	 *   <td>ActionNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>8</td>
	 *   <td>IngoingFlowInActionNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>9</td>
	 *   <td>IngoingFlowInOutputNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>10</td>
	 *   <td>InputNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>11</td>
	 *   <td>OutgoingFLowFromActionNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>12</td>
	 *   <td>OutgoingFlowFromInputNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 *  <tr>
	 *   <td>13</td>
	 *   <td>OutputNodeNotFoundWarningManager</td>
	 *   <td></td>
	 *  </tr>
	 * </table>
	 * 
	 * 
	 * @param values la lista di boolean che specificano la richiesta di particolari controlli
	 * @return un messaggio contenente la lista di Warning rilevati ed eventualmente l'errore che ha bloccato il validator
	 * @throws ParametersRequiredMismatchException in caso in cui i boolean passati non siano pari al numero di controlli totali disponibili
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */ 
	public ValidatorMessage ValidateUserAction(List<ValidatorWarningException> warninglist, UserAction action,DirectedGraph<Node,Flow> _currentgraph,boolean ...values) throws ParametersRequiredMismatchException, InstantiationException, IllegalAccessException{
		//aggiunta parametri corretti???
		if (values.length!=this.errorManagerArchive.size()){
			throw new ParametersRequiredMismatchException("boolean richiesti: "+this.errorManagerArchive.size()+" boolean passati: "+values.length);
		}
		
		//copia del grafo precedente
		DirectedGraph<Node,Flow> copy=new DefaultDirectedGraph<Node,Flow>(Flow.class);
		Iterator<Node> vertexiterator=_currentgraph.vertexSet().iterator();
		Iterator<Flow> flowiterator=_currentgraph.edgeSet().iterator();
		while (vertexiterator.hasNext()){
			copy.addVertex(vertexiterator.next());
		}
		while(flowiterator.hasNext()){
			Flow f=flowiterator.next();
			copy.addEdge((Node)f.getSource(),(Node)f.getTarget());
		}
		
		ValidatorMessage ret=new ValidatorMessage();
		//TODO modificare quando vorremmo inserire, oltre al elimina nodo, anche l'elimina flusso!
		//esecuzione azione richiesta dall'utente
		if (action!=null){
			if (action.isToerase()&&(action.getNodeToBeEdit()!=null)){
				copy.removeVertex(action.getNodeToBeEdit());
			}else{
				if (action.getNodeToBeEdit()!=null){
					copy.addVertex(action.getNodeToBeEdit());
				}
				if ((action.getStartFlow()!=null)&&(action.getEndFlow()!=null)){
					copy.addEdge(action.getStartFlow(),action.getEndFlow());
				}
			}
		}
		/*ci sono 2 possibilit�: il metodo nella seconda possibilit� � in grado di intercettare
		 * errori solamente se il grafo non e' vuoto... cosa succede se il grafo � vuoto? semplicemente
		 * il while{} non viene eseguito, non viene intercettato alcun errore (e quindi nessun InputNotFound,
		 * ActionNotFound e OutputNotFound)! e' evidente che e' un errore, quindi in tale caso devo prevedere
		 * un if! */
		if (!copy.vertexSet().iterator().hasNext()){//il grafo e' vuoto
			ret.addWarning(new InputNodeNotFoundException());
			ret.addWarning(new ActionNodeNotFoundException());
			ret.addWarning(new OutputNodeNotFoundException());
			return ret;
		}
		//controllo di tutte le gestioni (in caso in cui il grafo copy � normale
		for (int i=0;i<this.errorManagerArchive.size();i++){
			if ((values[i])||(warninglist.contains(this.errorManagerArchive.get(i).getExceptionthrown().newInstance()))){
				Iterator<Node> iterator=copy.vertexSet().iterator();
				while (iterator.hasNext()){
					try {
						this.errorManagerArchive.get(i).specificHandler(iterator.next(), copy,!iterator.hasNext());
					} catch (ValidatorException error) {
						if (error instanceof ValidatorErrorException){
							ret.setErrorOccured((ValidatorErrorException) error);
							return ret;
						}
						if (error instanceof ValidatorWarningException){
							ret.addWarning((ValidatorWarningException) error);
						}
					}
				}
			}
		}
		return ret;
	}
}
