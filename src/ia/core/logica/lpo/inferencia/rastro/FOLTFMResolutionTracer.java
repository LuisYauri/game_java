package ia.core.logica.lpo.inferencia.rastro;

import java.util.Set;

import ia.core.logica.lpo.inferencia.InferenceResult;
import ia.core.logica.lpo.bc.datos.Clause;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface FOLTFMResolutionTracer {
	void stepStartWhile(Set<Clause> clauses, int totalNoClauses,
			int totalNoNewCandidateClauses);

	void stepOuterFor(Clause i);

	void stepInnerFor(Clause i, Clause j);

	void stepResolved(Clause iFactor, Clause jFactor, Set<Clause> resolvents);

	void stepFinished(Set<Clause> clauses, InferenceResult result);
}
