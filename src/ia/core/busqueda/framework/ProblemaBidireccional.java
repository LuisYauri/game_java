package ia.core.busqueda.framework;

/**
 * An interface describing a problem that can be tackled from both directions at
 * once (i.e InitialState<->Goal).
 * 
 * @author Ciaran O'Reilly
 * 
 */
public interface ProblemaBidireccional {
	Problema getOriginalProblem();

	Problema getReverseProblem();
}
