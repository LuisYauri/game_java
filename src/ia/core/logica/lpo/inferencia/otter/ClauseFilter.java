package ia.core.logica.lpo.inferencia.otter;

import java.util.Set;

import ia.core.logica.lpo.bc.datos.Clause;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface ClauseFilter {
	Set<Clause> filter(Set<Clause> clauses);
}
