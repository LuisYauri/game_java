package ia.core.logica.lpo.analizadorsint.sat;

import java.util.List;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface AtomicSentence extends Sentence {
	List<Term> getArgs();

	AtomicSentence copy();
}
