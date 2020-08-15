package ia.core.probabilidad.bayes;

import ia.core.probabilidad.CategoricalDistribution;
import ia.core.probabilidad.RandomVariable;
import ia.core.probabilidad.proposicion.AssignmentProposition;

/**
 * General interface to be implemented by Bayesian Inference algorithms.
 * 
 * @author Ciaran O'Reilly
 */
public interface BayesInference {
	/**
	 * @param X
	 *            the query variables.
	 * @param observedEvidence
	 *            observed values for variables E.
	 * @param bn
	 *            a Bayes net with variables {X} &cup; E &cup; Y /* Y = hidden
	 *            variables
	 * @return a distribution over the query variables.
	 */
	CategoricalDistribution ask(final RandomVariable[] X,
			final AssignmentProposition[] observedEvidence,
			final BayesianNetwork bn);
}
