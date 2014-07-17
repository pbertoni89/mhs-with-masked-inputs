package alg;

import java.util.Comparator;
import java.util.Set;

/** Un comparatore tra Set generici, per ordinarli in accordo alla loro cardinalità.
 * @author patrizio
 * @param <E> 
 * */
public class  SetComparatorBySize<E> implements Comparator<E>{

	@SuppressWarnings("unchecked")
	public int compare(Object set1, Object set2) {

		if( ((Set<E>)set1).size() < ((Set<E>)set2).size() )
			return -1;
		else
		if( ((Set<E>)set1).size() > ((Set<E>)set2).size() )
			return 1;
		else
			return 0;
		
		//System.out.println("CAST. comparing size("+ ((Set<E>) set1).toString() +")= "+((Set<E>) set1).size()+
		//		                     " with size("+ ((Set<E>) set2).toString() +")= "+((Set<E>) set2).size()+" || "+ value );
	}
}
