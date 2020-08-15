package ia.prueba.core.unit.search.uninformed;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.nreinas.TableroNReinas;
import ia.core.entorno.nreinas.NQueensFunctionFactory;
import ia.core.entorno.nreinas.NQueensGoalTest;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.framework.BusquedaDeArbol;
import ia.core.busqueda.noinformada.BreadthFirstSearch;

public class BreadthFirstSearchTest {

	@Test
	public void testBreadthFirstSuccesfulSearch() throws Exception {
		Problema problem = new Problema(new TableroNReinas(8),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		Busqueda search = new BreadthFirstSearch(new BusquedaDeArbol());
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		List<Accion> actions = agent.getActions();
		assertCorrectPlacement(actions);
		Assert.assertEquals("1665",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("8.0",
				agent.getInstrumentation().getProperty("pathCost"));
	}

	@Test
	public void testBreadthFirstUnSuccesfulSearch() throws Exception {
		Problema problem = new Problema(new TableroNReinas(3),
				NQueensFunctionFactory.getIActionsFunction(),
				NQueensFunctionFactory.getResultFunction(),
				new NQueensGoalTest());
		Busqueda search = new BreadthFirstSearch(new BusquedaDeArbol());
		AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
		List<Accion> actions = agent.getActions();
		Assert.assertEquals(0, actions.size());
		Assert.assertEquals("6",
				agent.getInstrumentation().getProperty("nodesExpanded"));
		Assert.assertEquals("0",
				agent.getInstrumentation().getProperty("pathCost"));
	}

	//
	// PRIVATE METHODS
	//
	private void assertCorrectPlacement(List<Accion> actions) {
		Assert.assertEquals(8, actions.size());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 0 , 0 ) ]", actions
						.get(0).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 1 , 4 ) ]", actions
						.get(1).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 2 , 7 ) ]", actions
						.get(2).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 3 , 5 ) ]", actions
						.get(3).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 4 , 2 ) ]", actions
						.get(4).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 5 , 6 ) ]", actions
						.get(5).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 6 , 1 ) ]", actions
						.get(6).toString());
		Assert.assertEquals(
				"Action[name==placeQueenAt, location== ( 7 , 3 ) ]", actions
						.get(7).toString());
	}
}
