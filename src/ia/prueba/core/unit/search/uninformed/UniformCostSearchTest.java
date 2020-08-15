package ia.prueba.core.unit.search.uninformed;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.mapa.ExtendableMap;
import ia.core.entorno.mapa.Map;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MapStepCostFunction;
import ia.core.entorno.mapa.SimplifiedRoadMapOfPartOfRomania;
import ia.core.entorno.nreinas.TableroNReinas;
import ia.core.entorno.nreinas.NQueensFunctionFactory;
import ia.core.entorno.nreinas.NQueensGoalTest;
import ia.core.busqueda.framework.PruebaMetaPorDefecto;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.BusquedaCola;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.noinformada.UniformCostSearch;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class UniformCostSearchTest {

	@Test
	public void testUniformCostSuccesfulSearch() throws Exception {
		Problema problem = new Problema(new TableroNReinas(8),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		Busqueda search = new UniformCostSearch();
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(8, actions.size());

		Assert.assertEquals("1965",
				agent.getInstrumentation().getProperty("nodesExpanded"));

		Assert.assertEquals("8.0",
				agent.getInstrumentation().getProperty("pathCost"));
	}

	@Test
	public void testUniformCostUnSuccesfulSearch() throws Exception {
		Problema problem = new Problema(new TableroNReinas(3),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		Busqueda search = new UniformCostSearch();
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(0, actions.size());

		Assert.assertEquals("6",
				agent.getInstrumentation().getProperty("nodesExpanded"));

		// Will be 0 as did not reach goal state.
		Assert.assertEquals("0",
				agent.getInstrumentation().getProperty("pathCost"));
	}

	@Test
	public void testAIMA3eFigure3_15() throws Exception {
		Map romaniaMap = new SimplifiedRoadMapOfPartOfRomania();
		Problema problem = new Problema(SimplifiedRoadMapOfPartOfRomania.SIBIU,
				MapFunctionFactory.getActionsFunction(romaniaMap),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						SimplifiedRoadMapOfPartOfRomania.BUCHAREST),
				new MapStepCostFunction(romaniaMap));

		Busqueda search = new UniformCostSearch();
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(
				"[Action[name==moveTo, location==RimnicuVilcea], Action[name==moveTo, location==Pitesti], Action[name==moveTo, location==Bucharest]]",
				actions.toString());
		Assert.assertEquals("278.0",
				search.getMetrics().get(BusquedaCola.METRIC_PATH_COST));
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

		Busqueda search = new UniformCostSearch();
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);

		List<Accion> actions = agent.getActions();

		Assert.assertEquals(
				"[Action[name==moveTo, location==b], Action[name==moveTo, location==d], Action[name==moveTo, location==goal]]",
				actions.toString());
		Assert.assertEquals("5.5",
				search.getMetrics().get(BusquedaCola.METRIC_PATH_COST));
	}
}
