package ia.prueba.core.unit.probability.temporal.generic;

import org.junit.Before;
import org.junit.Test;

import ia.core.probabilidad.ejemplo.GenericTemporalModelFactory;
import ia.core.probabilidad.temporal.generico.ForwardBackward;
import ia.prueba.core.unit.probability.temporal.CommonForwardBackwardTest;

/**
 * 
 * @author Ciaran O'Reilly
 *
 */
public class ForwardBackwardTest extends CommonForwardBackwardTest {

	//
	private ForwardBackward uw = null;

	@Before
	public void setUp() {
		uw = new ForwardBackward(
				GenericTemporalModelFactory.getUmbrellaWorldTransitionModel(),
				GenericTemporalModelFactory.getUmbrellaWorld_Xt_to_Xtm1_Map(),
				GenericTemporalModelFactory.getUmbrellaWorldSensorModel());
	}

	@Test
	public void testForwardStep_UmbrellaWorld() {
		super.testForwardStep_UmbrellaWorld(uw);
	}

	@Test
	public void testBackwardStep_UmbrellaWorld() {
		super.testBackwardStep_UmbrellaWorld(uw);
	}

	@Test
	public void testForwardBackward_UmbrellaWorld() {
		super.testForwardBackward_UmbrellaWorld(uw);
	}
}
