package ia.prueba.core.unit.search.informed;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.VistaEntorno;
import ia.core.entorno.mapa.Map;
import ia.core.entorno.mapa.MapAgent;
import ia.core.entorno.mapa.MapEnvironment;
import ia.core.entorno.mapa.SimplifiedRoadMapOfPartOfRomania;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.informada.AStarEvaluationFunction;
import ia.core.busqueda.informada.RecursiveBestFirstSearch;
import ia.core.util.estructuradedatos.Point2D;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class RecursiveBestFirstSearchTest {

	StringBuffer envChanges;

	Map aMap;

	RecursiveBestFirstSearch recursiveBestFirstSearch;

	@Before
	public void setUp() {
		envChanges = new StringBuffer();

		aMap = new SimplifiedRoadMapOfPartOfRomania();

		FuncionHeuristica heuristicFunction = new FuncionHeuristica() {
			public double h(Object state) {
				Point2D pt1 = aMap.getPosition((String) state);
				Point2D pt2 = aMap
						.getPosition(SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
				return pt1.distance(pt2);
			}
		};

		recursiveBestFirstSearch = new RecursiveBestFirstSearch(
				new AStarEvaluationFunction(heuristicFunction));
	}

	@Test
	public void testStartingAtGoal() {
		MapEnvironment me = new MapEnvironment(aMap);
		MapAgent ma = new MapAgent(me.getMap(), me, recursiveBestFirstSearch,
				new String[] { SimplifiedRoadMapOfPartOfRomania.BUCHAREST });

		me.addAgent(ma, SimplifiedRoadMapOfPartOfRomania.BUCHAREST);
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"CurrentLocation=In(Bucharest), Goal=In(Bucharest):Action[name==NoOp]:METRIC[pathCost]=0.0:METRIC[maxRecursiveDepth]=0:METRIC[nodesExpanded]=0:Action[name==NoOp]:",
				envChanges.toString());
	}

	@Test
	public void testAIMA3eFigure3_27() {
		MapEnvironment me = new MapEnvironment(aMap);
		MapAgent ma = new MapAgent(me.getMap(), me, recursiveBestFirstSearch,
				new String[] { SimplifiedRoadMapOfPartOfRomania.BUCHAREST });

		me.addAgent(ma, SimplifiedRoadMapOfPartOfRomania.ARAD);
		me.addEnvironmentView(new TestEnvironmentView());
		me.pasoHastaTerminar();

		Assert.assertEquals(
				"CurrentLocation=In(Arad), Goal=In(Bucharest):Action[name==moveTo, location==Sibiu]:Action[name==moveTo, location==RimnicuVilcea]:Action[name==moveTo, location==Pitesti]:Action[name==moveTo, location==Bucharest]:METRIC[pathCost]=418.0:METRIC[maxRecursiveDepth]=4:METRIC[nodesExpanded]=6:Action[name==NoOp]:",
				envChanges.toString());
	}

	private class TestEnvironmentView implements VistaEntorno {
		public void notify(String msg) {
			envChanges.append(msg).append(":");
		}

		public void agentAdded(Agente agent, EstadoEntorno state) {
			// Nothing.
		}

		public void agentActed(Agente agent, Accion action,
				EstadoEntorno state) {
			envChanges.append(action).append(":");
		}
	}
}
