package ia.core.logica.lpo.inferencia.otter.impldefecto;

import java.util.ArrayList;
import java.util.List;

import ia.core.logica.lpo.inferencia.Demodulation;
import ia.core.logica.lpo.inferencia.otter.ClauseSimplifier;
import ia.core.logica.lpo.bc.datos.Clause;
import ia.core.logica.lpo.analizadorsint.sat.TermEquality;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class DefaultClauseSimplifier implements ClauseSimplifier {

	private Demodulation demodulation = new Demodulation();
	private List<TermEquality> rewrites = new ArrayList<TermEquality>();

	public DefaultClauseSimplifier() {

	}

	public DefaultClauseSimplifier(List<TermEquality> rewrites) {
		this.rewrites.addAll(rewrites);
	}

	//
	// START-ClauseSimplifier
	public Clause simplify(Clause c) {
		Clause simplified = c;

		// Apply each of the rewrite rules to
		// the clause
		for (TermEquality te : rewrites) {
			Clause dc = simplified;
			// Keep applying the rewrite as many times as it
			// can be applied before moving on to the next one.
			while (null != (dc = demodulation.apply(te, dc))) {
				simplified = dc;
			}
		}

		return simplified;
	}

	// END-ClauseSimplifier
	//
}
