package ia.prueba.core.unit.probability.bayes.approx;

import org.junit.Assert;
import org.junit.Test;

import ia.core.probabilidad.ProbabilityModel;
import ia.core.probabilidad.RandomVariable;
import ia.core.probabilidad.bayes.BayesianNetwork;
import ia.core.probabilidad.bayes.aproximado.GibbsAsk;
import ia.core.probabilidad.ejemplo.BayesNetExampleFactory;
import ia.core.probabilidad.ejemplo.ExampleRV;
import ia.core.probabilidad.proposicion.AssignmentProposition;
import ia.core.util.MockRandomizer;

/**
 * 
 * @author Ciaran O'Reilly
 * @author Ravi Mohan
 */
public class GibbsAskTest {
	public static final double DELTA_THRESHOLD = ProbabilityModel.DEFAULT_ROUNDING_THRESHOLD;

	@Test
	public void testGibbsAsk_basic() {
		BayesianNetwork bn = BayesNetExampleFactory
				.constructCloudySprinklerRainWetGrassNetwork();
		AssignmentProposition[] e = new AssignmentProposition[] { new AssignmentProposition(
				ExampleRV.SPRINKLER_RV, Boolean.TRUE) };
		MockRandomizer r = new MockRandomizer(new double[] { 0.5, 0.5, 0.5,
				0.5, 0.5, 0.5, 0.6, 0.5, 0.5, 0.6, 0.5, 0.5 });

		GibbsAsk ga = new GibbsAsk(r);

		double[] estimate = ga.gibbsAsk(
				new RandomVariable[] { ExampleRV.RAIN_RV }, e, bn, 3)
				.getValues();

		Assert.assertArrayEquals(new double[] { 0.3333333333333333,
				0.6666666666666666 }, estimate, DELTA_THRESHOLD);
	}
}
