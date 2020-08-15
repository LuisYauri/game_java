package ia.core.probabilidad.pdm.impl;

import java.util.HashMap;
import java.util.Map;

import ia.core.agente.Accion;
import ia.core.probabilidad.pdm.Policy;

/**
 * Default implementation of the Policy interface using an underlying Map to
 * look up an action associated with a state.
 * 
 * @param <S>
 *            the state type.
 * @param <A>
 *            the action type.
 * 
 * @author Ciaran O'Reilly
 */
public class LookupPolicy<S, A extends Accion> implements Policy<S, A> {
	private Map<S, A> policy = new HashMap<S, A>();

	public LookupPolicy(Map<S, A> aPolicy) {
		policy.putAll(aPolicy);
	}

	//
	// START-Policy
	@Override
	public A action(S s) {
		return policy.get(s);
	}

	// END-Policy
	//
}
