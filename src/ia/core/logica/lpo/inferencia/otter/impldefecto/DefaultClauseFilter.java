package ia.core.logica.lpo.inferencia.otter.impldefecto;

import java.util.Set;

import ia.core.logica.lpo.inferencia.otter.ClauseFilter;
import ia.core.logica.lpo.bc.datos.Clause;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class DefaultClauseFilter implements ClauseFilter {
	public DefaultClauseFilter() {

	}

	//
	// START-ClauseFilter
	public Set<Clause> filter(Set<Clause> clauses) {
		return clauses;
	}

	// END-ClauseFilter
	//
}
