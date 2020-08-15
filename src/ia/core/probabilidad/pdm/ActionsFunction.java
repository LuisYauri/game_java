package ia.core.probabilidad.pdm;

import java.util.Set;

import ia.core.agente.Accion;

/**
 * An interface for MDP action functions.
 * 
 * @param <S>
 *            the state type.
 * @param <A>
 *            the action type.
 * 
 * @author Ciaran O'Reilly
 * @author Ravi Mohan
 */
public interface ActionsFunction<S, A extends Accion> {
	/**
	 * Get the set of actions for state s.
	 * 
	 * @param s
	 *            the state.
	 * @return the set of actions for state s.
	 */
	Set<A> actions(S s);
}
