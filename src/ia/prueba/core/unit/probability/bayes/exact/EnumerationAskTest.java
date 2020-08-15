package ia.prueba.core.unit.probability.bayes.exact;

import org.junit.Before;

import ia.core.probabilidad.bayes.exacto.EnumerationAsk;

/**
 * 
 * @author Ciaran O'Reilly
 * @author Ravi Mohan
 */
public class EnumerationAskTest extends BayesianInferenceTest {

	@Before
	public void setUp() {
		bayesInference = new EnumerationAsk();
	}
}
