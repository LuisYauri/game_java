package ia.prueba.core.unit.probability.bayes.exact;

import org.junit.Before;

import ia.core.probabilidad.bayes.exacto.EliminationAsk;

/**
 * 
 * @author Ciaran O'Reilly
 */
public class EliminationAskTest extends BayesianInferenceTest {

	@Before
	public void setUp() {
		bayesInference = new EliminationAsk();
	}
}
