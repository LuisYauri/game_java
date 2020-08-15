package ia.core.logica.lpo.inferencia;

import ia.core.logica.lpo.bc.FOLKnowledgeBase;
import ia.core.logica.lpo.analizadorsint.sat.Sentence;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface InferenceProcedure {
	/**
	 * 
	 * @param kb
	 *            the knowledge base against which the query is to be made.
	 * @param query
	 *            the query to be answered.
	 * @return an InferenceResult.
	 */
	InferenceResult ask(FOLKnowledgeBase kb, Sentence query);
}
