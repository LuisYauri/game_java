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
import ia.core.busqueda.enlinea.OnlineDFSAgent;
import ia.core.busqueda.enlinea.OnlineSearchProblem;

public class OnlineDFSAgentTest {

	ExtendableMap aMap;

	StringBuffer envChanges;

	@Before
	public void setUp() {
		aMap = new ExtendableMap();
		aMap.addBidirectionalLink("A", "B", 5.0);
		aMap.addBidirectionalLink("A", "C", 6.0);
		aMap.addBidirectionalLink("B", "D", 4.0);
		aMap.addBidirectionalLink("B", "E", 7.0);
		aMap.addBidirectionalLink("D", "F", 4.0);
		aMap.addBidirectionalLink("D", "G", 8.0);

		envChanges = new StringBuffer();
	}

	@Test
	public void testAlreadyAtGoal() {
		MapEnvironment me = new MapEnvironment(aMap);
		OnlineDFSAgent agent = new OnlineDFSAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("A"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction());
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals("Action[name==NoOp]->", envChanges.toString());
	}

	@Test
	public void testNormalSearch() {
		MapEnvironment me = new MapEnvironment(aMap);
		OnlineDFSAgent agent = new OnlineDFSAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("G"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction());
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==moveTo, location==B]->Action[name==moveTo, location==A]->Action[name==moveTo, location==C]->Action[name==moveTo, location==A]->Action[name==moveTo, location==C]->Action[name==moveTo, location==A]->Action[name==moveTo, location==B]->Action[name==moveTo, location==D]->Action[name==moveTo, location==B]->Action[name==moveTo, location==E]->Action[name==moveTo, location==B]->Action[name==moveTo, location==E]->Action[name==moveTo, location==B]->Action[name==moveTo, location==D]->Action[name==moveTo, location==F]->Action[name==moveTo, location==D]->Action[name==moveTo, location==G]->Action[name==NoOp]->",
				envChanges.toString());
	}

	@Test
	public void testNoPath() {
		aMap = new ExtendableMap();
		aMap.addBidirectionalLink("A", "B", 1.0);
		MapEnvironment me = new MapEnvironment(aMap);
		OnlineDFSAgent agent = new OnlineDFSAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("X"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction());
		me.addAgent(agent, "A");
		me.addEnvironmentView(new TestEnvironmentView());

		me.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==moveTo, location==B]->Action[name==moveTo, location==A]->Action[name==moveTo, location==B]->Action[name==moveTo, location==A]->Action[name==NoOp]->",
				envChanges.toString());
	}

	@Test
	public void testAIMA3eFig4_19() {
		aMap = new ExtendableMap();
		aMap.addBidirectionalLink("1,1", "1,2", 1.0);
		aMap.addBidirectionalLink("1,1", "2,1", 1.0);
		aMap.addBidirectionalLink("2,1", "3,1", 1.0);
		aMap.addBidirectionalLink("2,1", "2,2", 1.0);
		aMap.addBidirectionalLink("3,1", "3,2", 1.0);
		aMap.addBidirectionalLink("2,2", "2,3", 1.0);
		aMap.addBidirectionalLink("3,2", "3,3", 1.0);
		aMap.addBidirectionalLink("2,3", "1,3", 1.0);

		MapEnvironment me = new MapEnvironment(aMap);
		OnlineDFSAgent agent = new OnlineDFSAgent(new OnlineSearchProblem(
				MapFunctionFactory.getActionsFunction(aMap),
				new PruebaMetaPorDefecto("3,3"), new MapStepCostFunction(aMap)),
				MapFunctionFactory.getPerceptToStateFunction());
		me.addAgent(agent, "1,1");
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==moveTo, location==1,2]->Action[name==moveTo, location==1,1]->Action[name==moveTo, location==2,1]->Action[name==moveTo, location==1,1]->Action[name==moveTo, location==2,1]->Action[name==moveTo, location==2,2]->Action[name==moveTo, location==2,1]->Action[name==moveTo, location==3,1]->Action[name==moveTo, location==2,1]->Action[name==moveTo, location==3,1]->Action[name==moveTo, location==3,2]->Action[name==moveTo, location==3,1]->Action[name==moveTo, location==3,2]->Action[name==moveTo, location==3,3]->Action[name==NoOp]->",
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
