package ia.prueba.core.unit.environment.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.VistaEntorno;
import ia.core.entorno.mapa.ExtendableMap;
import ia.core.entorno.mapa.MapAgent;
import ia.core.entorno.mapa.MapEnvironment;
import ia.core.busqueda.noinformada.UniformCostSearch;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class MapAgentTest {

	ExtendableMap aMap;

	StringBuffer envChanges;

	@Before
	public void setUp() {
		aMap = new ExtendableMap();
		aMap.addBidirectionalLink("A", "B", 5.0);
		aMap.addBidirectionalLink("A", "C", 6.0);
		aMap.addBidirectionalLink("B", "C", 4.0);
		aMap.addBidirectionalLink("C", "D", 7.0);
		aMap.addUnidirectionalLink("B", "E", 14.0);

		envChanges = new StringBuffer();
	}

	@Test
	public void testAlreadyAtGoal() {
		MapEnvironment me = new MapEnvironment(aMap);
		MapAgent ma = new MapAgent(me.getMap(), me, new UniformCostSearch(),
				new String[] { "A" });
		me.addAgent(ma, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"CurrentLocation=In(A), Goal=In(A):Action[name==NoOp]:METRIC[pathCost]=0.0:METRIC[maxQueueSize]=1:METRIC[queueSize]=0:METRIC[nodesExpanded]=0:Action[name==NoOp]:",
				envChanges.toString());
	}

	@Test
	public void testNormalSearch() {
		MapEnvironment me = new MapEnvironment(aMap);
		MapAgent ma = new MapAgent(me.getMap(), me, new UniformCostSearch(),
				new String[] { "D" });
		me.addAgent(ma, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"CurrentLocation=In(A), Goal=In(D):Action[name==moveTo, location==C]:Action[name==moveTo, location==D]:METRIC[pathCost]=13.0:METRIC[maxQueueSize]=2:METRIC[queueSize]=1:METRIC[nodesExpanded]=3:Action[name==NoOp]:",
				envChanges.toString());
	}

	@Test
	public void testNoPath() {
		MapEnvironment me = new MapEnvironment(aMap);
		MapAgent ma = new MapAgent(me.getMap(), me, new UniformCostSearch(),
				new String[] { "A" });
		me.addAgent(ma, "E");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"CurrentLocation=In(E), Goal=In(A):Action[name==NoOp]:METRIC[pathCost]=0:METRIC[maxQueueSize]=1:METRIC[queueSize]=0:METRIC[nodesExpanded]=1:Action[name==NoOp]:",
				envChanges.toString());
	}

	private class TestEnvironmentView implements VistaEntorno {
		public void notify(String msg) {
			envChanges.append(msg).append(":");
		}

		public void agentAdded(Agente agent, EstadoEntorno state) {
			// Nothing
		}

		public void agentActed(Agente agent, Accion action,
				EstadoEntorno state) {
			envChanges.append(action).append(":");
		}
	}
}
