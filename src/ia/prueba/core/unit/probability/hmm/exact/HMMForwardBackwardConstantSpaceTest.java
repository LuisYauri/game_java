package ia.prueba.core.unit.probability.hmm.exact;

import org.junit.Before;
import org.junit.Test;

import ia.core.probabilidad.ejemplo.HMMExampleFactory;
import ia.core.probabilidad.mmo.exacto.HMMForwardBackwardConstantSpace;
import ia.prueba.core.unit.probability.temporal.CommonForwardBackwardTest;

/**
 * 
 * @author Ciaran O'Reilly
 *
 */
public class HMMForwardBackwardConstantSpaceTest extends
		CommonForwardBackwardTest {

	//
	private HMMForwardBackwardConstantSpace uw = null;

	@Before
	public void setUp() {
		uw = new HMMForwardBackwardConstantSpace(
				HMMExampleFactory.getUmbrellaWorldModel());
	}

	@Test
	public void testForwardBackward_UmbrellaWorld() {
		super.testForwardBackward_UmbrellaWorld(uw);
	}
}
