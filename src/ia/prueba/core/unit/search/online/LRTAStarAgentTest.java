package ia.prueba.core.unit.search.online;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.VistaEntorno;
import ia.core.entorno.mapa.ExtendableMap;
import ia.core.entorno.mapa.MapEnvironment;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MapStepCostFunction;
import ia.core.busqueda.framework.PruebaMetaPorDefecto;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.enlinea.LRTAStarAgent;
import ia.core.busqueda.enlinea.OnlineSearchProblem;

public class LRTAStarAgentTest {
	ExtendableMap aMap;

	StringBuffer envChanges;

	FuncionHeuristica hf;

	@Before
	public void setUp() {
		aMap = new ExtendableMap();
		aMap.addBidirectionalLink("A", "B", 4.0);
		aMap.addBidirectionalLink("B", "C", 4.0);
		aMap.addBidirectionalLink("C", "D", 4.0);
		aMap.addBidirectionalLink("D", "E", 4.0);
		aMap.addBidirectionalLink("E", "F", 4.0);
		hf = new FuncionHeuristica() {
			public double h(Object state) {
				return 1;
			}
		};

		envChanges = new StringBuffer();
	}

	@Test
	public void testAlreadyAtGoal() {
		MapEnvironment me = new MapEnvironment(aMap);
		LRTAStarAgent agent = new LRTAStarAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("A"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction(), hf);
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals("Action[name==NoOp]->", envChanges.toString());
	}

	@Test
	public void testNormalSearch() {
		MapEnvironment me = new MapEnvironment(aMap);
		LRTAStarAgent agent = new LRTAStarAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("F"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction(), hf);
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==moveTo, location==B]->Action[name==moveTo, location==A]->Action[name==moveTo, location==B]->Action[name==moveTo, location==C]->Action[name==moveTo, location==B]->Action[name==moveTo, location==C]->Action[name==moveTo, location==D]->Action[name==moveTo, location==C]->Action[name==moveTo, location==D]->Action[name==moveTo, location==E]->Action[name==moveTo, location==D]->Action[name==moveTo, location==E]->Action[name==moveTo, location==F]->Action[name==NoOp]->",
				envChanges.toString());
	}

	@Test
	public void testNoPath() {
		MapEnvironment me = new MapEnvironment(aMap);
		LRTAStarAgent agent = new LRTAStarAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("G"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction(), hf);
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		// Note: Will search forever if no path is possible,
		// Therefore restrict the number of steps to something
		// reasonablbe, against which to test.
		me.paso(14);

		Assert.assertEquals(
				"Action[name==moveTo, location==B]->Action[name==moveTo, location==A]->Action[name==moveTo, location==B]->Action[name==moveTo, location==C]->Action[name==moveTo, location==B]->Action[name==moveTo, location==C]->Action[name==moveTo, location==D]->Action[name==moveTo, location==C]->Action[name==moveTo, location==D]->Action[name==moveTo, location==E]->Action[name==moveTo, location==D]->Action[name==moveTo, location==E]->Action[name==moveTo, location==F]->Action[name==moveTo, location==E]->",
				envChanges.toString());
	}

	private class TestEnvironmentView implements VistaEntorno {
		public void notify(String msg) {
			envChanges.append(msg).append("->");
		}

		public void agentAdded(Agente agent, EstadoEntorno state) {
			// Nothing.
		}

		public void agentActed(Agente agent, Accion action,
				EstadoEntorno state) {
			envChanges.append(action).append("->");
		}
	}
}
