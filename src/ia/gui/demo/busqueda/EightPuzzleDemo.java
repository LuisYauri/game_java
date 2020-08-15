package ia.gui.demo.busqueda;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import ia.core.agente.Accion;
import ia.core.entorno.puzleocho.EightPuzzleBoard;
import ia.core.entorno.puzleocho.EightPuzzleFunctionFactory;
import ia.core.entorno.puzleocho.EightPuzzleGoalTest;
import ia.core.entorno.puzleocho.ManhattanHeuristicFunction;
import ia.core.entorno.puzleocho.MisplacedTilleHeuristicFunction;
import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteDeBusqueda;
import ia.core.busqueda.informada.AStarSearch;
import ia.core.busqueda.informada.GreedyBestFirstSearch;
import ia.core.busqueda.local.SimulatedAnnealingSearch;
import ia.core.busqueda.noinformada.DepthLimitedSearch;
import ia.core.busqueda.noinformada.IterativeDeepeningSearch;

/**
 * @author Ravi Mohan
 * 
 */

public class EightPuzzleDemo {
	static EightPuzzleBoard boardWithThreeMoveSolution = new EightPuzzleBoard(
			new int[] { 1, 2, 5, 3, 4, 0, 6, 7, 8 });;

	static EightPuzzleBoard random1 = new EightPuzzleBoard(new int[] { 1, 4, 2,
			7, 5, 8, 3, 0, 6 });

	static EightPuzzleBoard extreme = new EightPuzzleBoard(new int[] { 0, 8, 7,
			6, 5, 4, 3, 2, 1 });

	public static void main(String[] args) {
		eightPuzzleDLSDemo();
		eightPuzzleIDLSDemo();
		eightPuzzleGreedyBestFirstDemo();
		eightPuzzleGreedyBestFirstManhattanDemo();
		eightPuzzleAStarDemo();
		eightPuzzleAStarManhattanDemo();
		eightPuzzleSimulatedAnnealingDemo();
	}

	private static void eightPuzzleDLSDemo() {
		System.out.println("\nEightPuzzleDemo recursive DLS (9) -->");
		try {
			Problema problem = new Problema(boardWithThreeMoveSolution, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Busqueda search = new DepthLimitedSearch(9);
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleIDLSDemo() {
		System.out.println("\nEightPuzzleDemo Iterative DLS -->");
		try {
			Problema problem = new Problema(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Busqueda search = new IterativeDeepeningSearch();
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleGreedyBestFirstDemo() {
		System.out
				.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)-->");
		try {
			Problema problem = new Problema(boardWithThreeMoveSolution,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					new EightPuzzleGoalTest());
			Busqueda search = new GreedyBestFirstSearch(new BusquedaDeGrafo(),
					new MisplacedTilleHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out
				.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)-->");
		try {
			Problema problem = new Problema(boardWithThreeMoveSolution,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					new EightPuzzleGoalTest());
			Busqueda search = new GreedyBestFirstSearch(new BusquedaDeGrafo(),
					new ManhattanHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleAStarDemo() {
		System.out
				.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)-->");
		try {
			Problema problem = new Problema(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Busqueda search = new AStarSearch(new BusquedaDeGrafo(),
					new MisplacedTilleHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing  Search -->");
		try {
			Problema problem = new Problema(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
					new ManhattanHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out
				.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)-->");
		try {
			Problema problem = new Problema(random1, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), new EightPuzzleGoalTest());
			Busqueda search = new AStarSearch(new BusquedaDeGrafo(),
					new ManhattanHeuristicFunction());
			AgenteDeBusqueda agent = new AgenteDeBusqueda(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}

	}

	private static void printActions(List<Accion> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

}