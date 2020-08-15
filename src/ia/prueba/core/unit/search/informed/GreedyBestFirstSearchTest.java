package ia.prueba.core.unit.search.informed;

import org.junit.Assert;
import org.junit.Test;

import ia.core.entorno.puzleocho.EightPuzzleBoard;
import ia.core.entorno.puzleocho.EightPuzzleFunctionFactory;
import ia.core.entorno.puzleocho.EightPuzzleGoalTest;
import ia.core.entorno.puzleocho.ManhattanHeuristicFunction;
import ia.core.entorno.mapa.Map;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MapStepCostFunction;
import ia.core.entorno.mapa.SimplifiedRoadMapOfPartOfRomania;
import ia.core.entorno.mapa.StraightLineDistanceHeuristicFunction;
import ia.core.busqueda.framework.PruebaMetaPorDefecto;
import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.framework.BusquedaDeArbol;
import ia.core.busqueda.informada.GreedyBestFirstSearch;

public class GreedyBestFirstSearchTest {

	@Test
	public void testGreedyBestFirstSearch() {
		try {
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {2,0,5,6,4,8,3,7,1});
			// EightPuzzleBoard extreme = new EightPuzzleBoard(new int[]
			// {0,8,7,6,5,4,3,2,1});
			EightPuzzleBoard board = new EightPuzzleBoard(new int[] { 7, 1, 8,
					0, 4, 6, 2, 3, 5 });

			Problema problem = new Problema(board,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					new EightPuzzleGoalTest());
			Busqueda search = new GreedyBestFirstSearch(new BusquedaDeGrafo(),
					new ManhattanHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			Assert.assertEquals(49, agent.getActions().size());
			Assert.assertEquals("197",
					agent.getInstrumentation().getProperty("nodesExpanded"));
			Assert.assertEquals("140",
					agent.getInstrumentation().getProperty("queueSize"));
			Assert.assertEquals("141",
					agent.getInstrumentation().getProperty("maxQueueSize"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown.");
		}
	}

	@Test
	public void testAIMA3eFigure3_23() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new GreedyBestFirstSearch(new BusquedaDeArbol(),
				new StraightLineDistanceHeuristicFunction(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		Assert.assertEquals(
				"[Action[name==moveTo, location==Sibiu], Action[name==moveTo, location==Fagaras], Action[name==moveTo, location==Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(3, agent.getActions().size());
		Assert.assertEquals("3",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("6",
				agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("7",
				agent.getInstrumentation().getProperty("maxQueueSize"));
	}

	@Test
	public void testAIMA3eFigure3_23_using_GraphSearch() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new GreedyBestFirstSearch(new BusquedaDeGrafo(),
				new StraightLineDistanceHeuristicFunction(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		Assert.assertEquals(
				"[Action[name==moveTo, location==Sibiu], Action[name==moveTo, location==Fagaras], Action[name==moveTo, location==Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(3, agent.getActions().size());
		Assert.assertEquals("3",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("4",
				agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("5",
				agent.getInstrumentation().getProperty("maxQueueSize"));
	}
}
