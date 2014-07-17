package alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import alg.sets.InternalSubset;
import graph.comp.Diagnosticable;

/** Un insieme di metodi di utilità, pensato per alleggerire la classe DiagnosisInspector.
 *  Contenente alcune operazioni di utilizzo pubblico.
 * @author patrizio   */
public class Utilities {
	
	/** Restituisce, dato un insieme di Subsets , l'insieme dei nodi che è unione di tutti i nodi dei subset elemento dell'insieme originale.
	 *  Questo metodo è specifico per i SubSet e quindi tratta, come elementi atomici, i nodi Diagnosticable.  
	 *  Future implementazioni possono generalizzare da Diagnosticable a Node.
	 *
	 * @param setOfSubSets
	 * @return
	 */
	protected static Set<Diagnosticable> unionIS(Set<InternalSubset> setOfSubSets ) {
		
		Set<Diagnosticable> union = new HashSet<Diagnosticable>() ;
		Iterator<InternalSubset> it = setOfSubSets.iterator();
		while( it.hasNext() ) 
			union.addAll( it.next().getOwnSet() );
		 
		return union;
	}
//_____________________________________________________________________________________________________________________________________
	/** Restituisce, dato un insieme, l'insieme delle sue parti (POWER SET). */
	public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
	    Set<Set<T>> sets = new HashSet<Set<T>>();
	    if (originalSet.isEmpty()) {
	        sets.add(new HashSet<T>());
	        return sets;
	    }
	    List<T> list = new ArrayList<T>(originalSet);
	    T head = list.get(0);
	    Set<T> rest = new HashSet<T>(list.subList(1, list.size())); 
	    for (Set<T> set : powerSet(rest)) {
	        Set<T> newSet = new HashSet<T>();
	        newSet.add(head);
	        newSet.addAll(set);
	        sets.add(newSet);
	        sets.add(set);
	    }           
	    return sets;
	}
//_____________________________________________________________________________________________________________________________________	
	/** Partendo da una collezione di insiemi, genera tutti e soli gli insiemi che sono Minimal Hitting Set della collezione. 
	 *  Versione ottimizzata dell'algoritmo, basata sulle osservazioni 1,2,3,4 di cui al pdf Esempi_Elaborato.  */
	protected static <T> Set<Set<T>> generateMHS( Vector<Set<T>> _collection ) {
		
		/** Copia locale dei dati*/
		Vector<Set<T>> collection = _collection;
		/** Set di Set da restituire */
		Set<Set<T>> setOfMHS = new HashSet<Set<T>>();
		/** Insieme dei singoletti distinti costituenti gli insiemi della collectionReadyForDiagnosis. */
		Set<T> singletons = new HashSet<T>();
		/** Insieme dei singoletti che sono HS (ed essendo singoletti, anche MHS). */
		Set<T> singletonHS = new HashSet<T>();
		/** Matrice booleana NxM, dove il valore a_i,j vale 1 sse l'elemento j appartiene all'insieme i. */
		boolean[][] matrix;
		/** numero degli insiemi che appartengono alla collezione.*/
		int N = collection.size();
		/** numero di elementi distinti che compaiono nell'unione degli insiemi della collezione. */
		int M;

		Iterator<Set<T>> itCollection = collection.iterator();
		while ( itCollection.hasNext() )
			singletons.addAll( itCollection.next() );
		M = singletons.size();
		
		/** eredita l'indicizzazione, mentre è inutile l'opzione della molteplicità dei componenti.
		 *  future implementazioni: individuare una classe migliore per descriverne l'essenza. */
		Vector<T> vectorColumns = setToVector(singletons); 
		/** è solo una reference, per bellezza concettuale. */
		Vector<Set<T>> vectorRows = collection;

		matrix = generatePresenceMatrix(vectorRows, vectorColumns);
		//Eliminare i singoletti che sono HS, in base all'osservazione 3)
		for(int j=0; j<M; j++) {
			boolean ok = true;
			for(int i=0; i<N; i++)
				if(matrix[i][j] == false)
					ok = false;
			if(ok) { //singleton è HS
				singletonHS.add(vectorColumns.get(j));
				vectorColumns.remove(j);
			}
		}
		//ricostruisco la matrice
		matrix = generatePresenceMatrix(vectorRows, vectorColumns);
		//genero il powerSet dei singoletti RIMASTI 
		Set<Set<T>>  powerSetOfSets = powerSet( vectorToSet(vectorColumns) ); 
			//rimuovo l'insieme vuoto
			powerSetOfSets.remove(new HashSet<T>());
			//rimuovo i singleton (devono diventare dei set)
			for(int h=0; h < vectorColumns.size(); h++) {
				Set<T> tempSingletonSet = new HashSet<T>();
				tempSingletonSet.add(vectorColumns.get(h));
				powerSetOfSets.remove( tempSingletonSet );
				}			
		// ordinare il powerSet in base alla dimensione dei suoi elementi mi permette di 
		// implementare l'algoritmo brute force ottimizzato.		
		List<Set<T>> asList = Arrays.asList(sortSetsBySize(powerSetOfSets));		
		Vector<Set<T>> vectorPowerSet = new Vector<Set<T>>( asList ) ;
		
		//è coerente con quel 2^M-M-1 individuato dal metodo, 
		//poichè 2^M è cardPowerSet, M è cardSingletons, 1 è l'insieme vuoto.
		int cardinalityPowerSet = vectorPowerSet.size(); 
		//main cycle
		for(int k=0; k<cardinalityPowerSet ; k++) {
			Set<T> s_i = vectorPowerSet.get(k);
			//in accordo all'osservazione 4), non considero i sottoinsiemi di cardinalità > N.
			if ( s_i.size() <= N ) {

				boolean isHS = true;
				int r=0;
				boolean covered; //indica se la riga è coperta o meno
				
				while( r<vectorRows.size() && isHS ) { //sto scorrendo le righe, per settare covered
					covered = false;
					int c=0;
					while ( c<vectorColumns.size() && !covered ) { 
						if ( s_i.contains(vectorColumns.get(c)) && matrix[r][c] )
							covered = true;
						c++;
					 }
					if (!covered)
						isHS = false;
					r++;
				}
				if(isHS) {
					boolean included = false; //misura se esiste un h in setOfMHS incluso propriamente in s_i
					Iterator<Set<T>> itSetOfMHS = setOfMHS.iterator(); 
					Set<T> h = new HashSet<T>(); 
					while( itSetOfMHS.hasNext() && !included ) {
						h = itSetOfMHS.next();
						if ( s_i.containsAll(h) && !s_i.equals(h) )
							included = true;
					}
					if(!included)
						setOfMHS.add(s_i);
				}
			 }
		}
		//aggiunta finale dei MSH singoletti
		Iterator<T> itSingletonHS = singletonHS.iterator();
		while (itSingletonHS.hasNext()) {
			Set<T> tempAddingSingletonHS = new HashSet<T>();
			tempAddingSingletonHS.add( itSingletonHS.next() );
			setOfMHS.add( tempAddingSingletonHS );
		}
		return setOfMHS;
	}
	
//_____________________________________________________________________________________________________________________________________		
	/** Dato un set di sets, il cui tipo è irrilevante,
	 *  restituisce l'array dei set componenti, ordinato in base alla loro cardinalità crescente.
	 * @param setOfSets
	 * @return arrayS
	 */
	public static <T> Set<T>[] sortSetsBySize(Set<Set<T>> setOfSets) {
		int i = 0;
		@SuppressWarnings("unchecked")
		Set<T> arrayS[] = new HashSet[setOfSets.size()];
		Iterator<Set<T>> itSetOfSets = setOfSets.iterator();
		while(itSetOfSets.hasNext()) {
			arrayS[i] = itSetOfSets.next();
			i++;
		 }
		Arrays.sort(arrayS, new SetComparatorBySize<Set<T>>() );
		return arrayS;
	}
//_____________________________________________________________________________________________________________________________________	
	/** Genera una matrice NxM dove il valore a_i,j 
	 * 		vale 1 se l'elemento j appartiene all'insieme i
	 * 		vale 0 else.                                        
	 *  In base a un booleano, decide se stampare o meno la matrice.  */
	protected static <T> boolean[][] generatePresenceMatrix( Vector<Set <T>> vectorRows, Vector<T> vectorColumns ) {
		int N = vectorRows.size();
		int M = vectorColumns.size();
		boolean[][] matrix = new boolean[N][M];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if (vectorRows.get(i).contains( vectorColumns.get(j) ) )
					matrix[i][j] = true;
				else
					matrix[i][j] = false;
		return matrix;
	}

//_____________________________________________________________________________________________________________________________________	
	/** Restituisce il vector contenente il set passato come parametro. Metodo nato da problemi di cast. */
	public static <T> Vector<T> setToVector( Set<T> set ) {
		Vector<T> vector = new Vector<T>();
		Iterator<T> itTemp = set.iterator();
		while( itTemp.hasNext() ) 
			vector.add(itTemp.next());
		return vector;
	}
	
	/** Restituisce il set contenente il vector passato come parametro. Metodo nato da problemi di cast. 
	 *  Chiaramente gli elementi duplicati verranno persi. */
	public static <T> Set<T> vectorToSet( Vector<T> vector ) {
		Set<T> set = new HashSet<T>();
		Iterator<T> itTemp = vector.iterator();
		while( itTemp.hasNext() ) 
			set.add(itTemp.next());
		return set;
	}
//_____________________________________________________________________________________________________________________________________
	/** Dati N sets in ingresso, restituisce il set dei sets uguale al loro prodotto cartesiano.
		@throws Qualora N < 2, lancia IllegalArgumentException 
		@return Il prodotto cartesiano. */
	public static <T> Set<Set<T>> cartesianProduct(List<Set<T>> sets) {
		
	    if (sets.size() < 2)
	        throw new IllegalArgumentException(
	                "Can't have a product of fewer than two sets (got " + sets.size() + ")");
	    return _cartesianProduct(0, sets);
	}
	/** @see Metodo di appoggio per cartesianProduct. */
	private static <T> Set<Set<T>> _cartesianProduct(int index, List<Set<T>> sets) {
		
	    Set<Set<T>> ret = new HashSet<Set<T>>();
	    
	    if (index == sets.size())
	        ret.add(new HashSet<T>());
	    else {
	        for (T obj : sets.get(index)) {
	            for (Set<T> set : _cartesianProduct(index+1, sets)) {
	                set.add(obj);
	                ret.add(set);
	            }
	        }
	    }
	    return ret;
	}
}//end of class