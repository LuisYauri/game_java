package ia.prueba.core.unit.search.informed;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.puzleocho.EightPuzzleBoard;
import ia.core.entorno.puzleocho.EightPuzzleFunctionFactory;
import ia.core.entorno.puzleocho.EightPuzzleGoalTest;
import ia.core.entorno.puzleocho.ManhattanHeuristicFunction;
import ia.core.entorno.mapa.ExtendableMap;
import ia.core.entorno.mapa.Map;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MapStepCostFunction;
import ia.core.entorno.mapa.SimplifiedRoadMapOfPartOfRomania;
import ia.core.entorno.mapa.StraightLineDistanceHeuristicFunction;
import ia.core.busqueda.framework.PruebaMetaPorDefecto;
import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.BusquedaCola;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.framework.BusquedaDeArbol;
import ia.core.busqueda.informada.AStarSearch;

public class AStarSearchTest {

	@Test
	public void testAStarSearch() {
		// added to narrow down bug report filed by L.N.Sudarshan of
		// Thoughtworks and Xin Lu of UCI
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
			Busqueda search = new AStarSearch(new BusquedaDeGrafo(),
					new ManhattanHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			Assert.assertEquals(23, agent.getActions().size());
			Assert.assertEquals("926",
					agent.getInstrumentation().getProperty("nodesExpanded"));
			Assert.assertEquals("534",
					agent.getInstrumentation().getProperty("queueSize"));
			Assert.assertEquals("535",
					agent.getInstrumentation().getProperty("maxQueueSize"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception thrown");
		}
	}

	@Test
	public void testAIMA3eFigure3_15() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.SIBIU,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new AStarSearch(new BusquedaDeGrafo(),
				new StraightLineDistanceHeuristicFunction(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(
				"[Action[name==moveTo, location==RimnicuVilcea], Action[name==moveTo, location==Pitesti], Action[name==moveTo, location==Bucharest]]",
				actions.toString());
		Assert.assertEquals("278.0",
				search.getMetrics().get(BusquedaCola.METRIC_PATH_COST));
	}

	@Test
	public void testAIMA3eFigure3_24() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new AStarSearch(new BusquedaDeArbol(),
				new StraightLineDistanceHeuristicFunction(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		Assert.assertEquals(
				"[Action[name==moveTo, location==Sibiu], Action[name==moveTo, location==RimnicuVilcea], Action[name==moveTo, location==Pitesti], Action[name==moveTo, location==Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(4, agent.getActions().size());
		Assert.assertEquals("5",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("10",
				agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("11",
				agent.getInstrumentation().getProperty("maxQueueSize"));
	}

	@Test
	public void testAIMA3eFigure3_24_using_GraphSearch() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.ARAD,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new AStarSearch(new BusquedaDeGrafo(),
				new StraightLineDistanceHeuristicFunction(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST, romaniaMap));
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		Assert.assertEquals(
				"[Action[name==moveTo, location==Sibiu], Action[name==moveTo, location==RimnicuVilcea], Action[name==moveTo, location==Pitesti], Action[name==moveTo, location==Bucharest]]",
				agent.getActions().toString());
		Assert.assertEquals(4, agent.getActions().size());
		Assert.assertEquals("5",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("4",
				agent.getInstrumentation().getProperty("queueSize"));
		Assert.assertEquals("6",
				agent.getInstrumentation().getProperty("maxQueueSize"));
	}

	@Test
	public void testCheckFrontierPathCost() throws Exception {
		ExtendableMap map = new ExtendableMap();
		map.addBidirectionalLink("start", "b", 2.5);
		map.addBidirectionalLink("start", "c", 1.0);
		map.addBidirectionalLink("b", "d", 2.0);
		map.addBidirectionalLink("c", "d", 4.0);
		map.addBidirectionalLink("c", "e", 1.0);
		map.addBidirectionalLink("d", "goal", 1.0);
		map.addBidirectionalLink("e", "goal", 5.0);
		Problema problem = new Problema("start",
				MapFunctionFactory.getActionsFunction(map),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						"goal"), new MapStepCostFunction(map));

		FuncionHeuristica hf = new FuncionHeuristica() {
			public double h(Object state) {
				return 0; // Don't have one for this test
			}
		};
		Busqueda search = new AStarSearch(new BusquedaDeGrafo(), hf);
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(
				"[Action[name==moveTo, location==b], Action[name==moveTo, location==d], Action[name==moveTo, location==goal]]",
				actions.toString());
		Assert.assertEquals("5.5",
				search.getMetrics().get(BusquedaCola.METRIC_PATH_COST));
	}
}
