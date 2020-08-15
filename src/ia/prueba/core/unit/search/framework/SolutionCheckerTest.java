package ia.prueba.core.unit.search.framework;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.mapa.Map;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MapStepCostFunction;
import ia.core.entorno.mapa.SimplifiedRoadMapOfPartOfRomania;
import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.framework.RevisorDeSolucion;
import ia.core.busqueda.noinformada.BreadthFirstSearch;

public class SolutionCheckerTest {

	@Test
	public void testMultiGoalProblem() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new DualMapGoalTest(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST,
						SimplifiedRoadMapOfPartOfRomania.HIRSOVA),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new BreadthFirstSearch(new BusquedaDeGrafo());

		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		Assert.assertEquals(
				"[Action[name==moveTo, location==Sibiu], Action[name==moveTo, location==Fagaras], Action[name==moveTo, location==Bucharest], Action[name==moveTo, location==Urziceni], Action[name==moveTo, location==Hirsova]]",
				agent.getActions().toString());
		Assert.assertEquals(5, agent.getActions().size());
		Assert.assertEquals("14",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("1",
				agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("5",
				agent.getInstrumentation().getProperty("maxQueueSize"));
	}

	class DualMapGoalTest implements RevisorDeSolucion {
		public String goalState1 = null;
		public String goalState2 = null;

		private Set<String> goals = new HashSet<String>();

		public DualMapGoalTest(String goalState1, String goalState2) {
			this.goalState1 = goalState1;
			this.goalState2 = goalState2;
			goals.add(goalState1);
			goals.add(goalState2);
		}

		public boolean isGoalState(Object state) {
			return goalState1.equals(state) || goalState2.equals(state);
		}

		public boolean isAcceptableSolution(List<Accion> actions, Object goal) {
			goals.remove(goal);
			return goals.isEmpty();
		}
	}
}
