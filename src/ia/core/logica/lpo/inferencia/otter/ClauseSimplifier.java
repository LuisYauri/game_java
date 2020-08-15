package ia.core.logica.lpo.inferencia.otter;

import ia.core.logica.lpo.bc.datos.Clause;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface ClauseSimplifier {
	Clause simplify(Clause c);
}
