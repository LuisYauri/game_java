package ia.prueba.core.unit.agent.impl.aprog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.AccionDinamica;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.agente.impl.AccionNoOp;
import ia.core.agente.impl.progagente.ProgramaAgenteDirigidoPorTabla;
import ia.prueba.core.unit.agent.impl.MockAgent;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class TableDrivenAgentProgramTest {

	private static final Accion ACTION_1 = new AccionDinamica("action1");
	private static final Accion ACTION_2 = new AccionDinamica("action2");
	private static final Accion ACTION_3 = new AccionDinamica("action3");

	private AgenteAbstracto agent;

	@Before
	public void setUp() {
		Map<List<Percepcion>, Accion> perceptSequenceActions = new HashMap<List<Percepcion>, Accion>();
		perceptSequenceActions.put(createPerceptSequence(new PercepcionDinamica(
				"key1", "value1")), ACTION_1);
		perceptSequenceActions.put(
				createPerceptSequence(new PercepcionDinamica("key1", "value1"),
						new PercepcionDinamica("key1", "value2")), ACTION_2);
		perceptSequenceActions.put(
				createPerceptSequence(new PercepcionDinamica("key1", "value1"),
						new PercepcionDinamica("key1", "value2"),
						new PercepcionDinamica("key1", "value3")), ACTION_3);

		agent = new MockAgent(new ProgramaAgenteDirigidoPorTabla(
				perceptSequenceActions));
	}

	@Test
	public void testExistingSequences() {
		Assert.assertEquals(ACTION_1,
				agent.ejecutar(new PercepcionDinamica("key1", "value1")));
		Assert.assertEquals(ACTION_2,
				agent.ejecutar(new PercepcionDinamica("key1", "value2")));
		Assert.assertEquals(ACTION_3,
				agent.ejecutar(new PercepcionDinamica("key1", "value3")));
	}

	@Test
	public void testNonExistingSequence() {
		Assert.assertEquals(ACTION_1,
				agent.ejecutar(new PercepcionDinamica("key1", "value1")));
		Assert.assertEquals(AccionNoOp.NO_OP,
				agent.ejecutar(new PercepcionDinamica("key1", "value3")));
	}

	private static List<Percepcion> createPerceptSequence(Percepcion... percepts) {
		List<Percepcion> perceptSequence = new ArrayList<Percepcion>();

		for (Percepcion p : percepts) {
			perceptSequence.add(p);
		}

		return perceptSequence;
	}
}
