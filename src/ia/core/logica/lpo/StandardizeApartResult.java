package ia.core.logica.lpo;

import java.util.Map;

import ia.core.logica.lpo.analizadorsint.sat.Sentence;
import ia.core.logica.lpo.analizadorsint.sat.Term;
import ia.core.logica.lpo.analizadorsint.sat.Variable;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class StandardizeApartResult {
	private Sentence originalSentence = null;
	private Sentence standardized = null;
	private Map<Variable, Term> forwardSubstitution = null;
	private Map<Variable, Term> reverseSubstitution = null;

	public StandardizeApartResult(Sentence originalSentence,
			Sentence standardized, Map<Variable, Term> forwardSubstitution,
			Map<Variable, Term> reverseSubstitution) {
		this.originalSentence = originalSentence;
		this.standardized = standardized;
		this.forwardSubstitution = forwardSubstitution;
		this.reverseSubstitution = reverseSubstitution;
	}

	public Sentence getOriginalSentence() {
		return originalSentence;
	}

	public Sentence getStandardized() {
		return standardized;
	}

	public Map<Variable, Term> getForwardSubstitution() {
		return forwardSubstitution;
	}

	public Map<Variable, Term> getReverseSubstitution() {
		return reverseSubstitution;
	}
}
