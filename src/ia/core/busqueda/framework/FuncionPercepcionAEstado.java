package ia.core.busqueda.framework;

import ia.core.agente.Percepcion;

/**
 * This interface is to define how to Map a Percepcion to a State representation
 * for a problem solver within a specific environment. This arises in the
 * description of the Online Search algorithms from Chapter 4.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public interface FuncionPercepcionAEstado {

	/**
	 * Get the problem state associated with a Percepcion.
	 * 
	 * @param p
	 *            the percept to be transformed to a problem state.
	 * @return a problem state derived from the Percepcion p.
	 */
	Object getState(Percepcion p);
}
