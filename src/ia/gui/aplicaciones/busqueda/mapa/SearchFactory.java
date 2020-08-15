package ia.gui.aplicaciones.busqueda.mapa;

import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.framework.BusquedaCola;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.BusquedaDeArbol;
import ia.core.busqueda.informada.AStarEvaluationFunction;
import ia.core.busqueda.informada.AStarSearch;
import ia.core.busqueda.informada.GreedyBestFirstSearch;
import ia.core.busqueda.informada.RecursiveBestFirstSearch;
import ia.core.busqueda.local.HillClimbingSearch;
import ia.core.busqueda.noinformada.BreadthFirstSearch;
import ia.core.busqueda.noinformada.DepthFirstSearch;
import ia.core.busqueda.noinformada.IterativeDeepeningSearch;
import ia.core.busqueda.noinformada.UniformCostSearch;

/**
 * Useful factory for configuring search objects. Implemented as a singleton.
 * @author Ruediger Lunde
 */
public class SearchFactory {

	/** Busqueda strategy: Depth first search. */
	public final static int DF_SEARCH = 0;
	/** Busqueda strategy: Depth first search. */
	public final static int BF_SEARCH = 1;
	/** Busqueda strategy: Iterative deepening search. */
	public final static int ID_SEARCH = 2;
	/** Busqueda strategy: Uniform cost search. */
	public final static int UC_SEARCH = 3;
	/** Busqueda strategy: Greedy best first search. */
	public final static int GBF_SEARCH = 4;
	/** Busqueda strategy: A* search. */
	public final static int ASTAR_SEARCH = 5;
	/** Busqueda strategy: Recursive best first search. */
	public final static int RBF_SEARCH = 6;
	/** Busqueda strategy: Hill climbing search. */
	public final static int HILL_SEARCH = 7;

	/** Busqueda mode: tree search. */
	public final static int TREE_SEARCH = 0;
	/** Busqueda mode: graph search. */
	public final static int GRAPH_SEARCH = 1;

	/** Contains the only existing instance. */
	private static SearchFactory instance;

	/** Invisible constructor. */
	private SearchFactory() {
	};

	/** Provides access to the factory. Implemented with lazy instantiation. */
	public static SearchFactory getInstance() {
		if (instance == null)
			instance = new SearchFactory();
		return instance;
	}

	/**
	 * Returns the names of all search strategies, which are supported by this
	 * factory. The indices correspond to the parameter values of method
	 * {@link #createSearch(int, int, HeuristicFunction)}.
	 */
	public String[] getSearchStrategyNames() {
		return new String[] { "Depth First", "Breadth First",
				"Iterative Deepening", "Uniform Cost", "Greedy Best First",
				"A*", "Recursive Best First", "Hill Climbing" };
	}

	/**
	 * Returns the names of all search modes, which are supported by this
	 * factory. The indices correspond to the parameter values of method
	 * {@link #createSearch(int, int, HeuristicFunction)}.
	 */
	public String[] getSearchModeNames() {
		return new String[] { "Tree Search", "Graph Search" };
	}

	/**
	 * Creates a search instance.
	 * 
	 * @param strategy
	 *            search strategy. See static constants.
	 * @param mode
	 *            search mode: {@link #TREE_SEARCH} or {@link #GRAPH_SEARCH}
	 * 
	 */
	public Busqueda createSearch(int strategy, int mode, FuncionHeuristica hf) {
		BusquedaCola qs = null;
		Busqueda result = null;
		switch (mode) {
		case TREE_SEARCH:
			qs = new BusquedaDeArbol();
			break;
		case GRAPH_SEARCH:
			qs = new BusquedaDeGrafo();
		}
		switch (strategy) {
		case DF_SEARCH:
			result = new DepthFirstSearch(qs);
			break;
		case BF_SEARCH:
			result = new BreadthFirstSearch(qs);
			break;
		case ID_SEARCH:
			result = new IterativeDeepeningSearch();
			break;
		case UC_SEARCH:
			result = new UniformCostSearch(qs);
			break;
		case GBF_SEARCH:
			result = new GreedyBestFirstSearch(qs, hf);
			break;
		case ASTAR_SEARCH:
			result = new AStarSearch(qs, hf);
			break;
		case RBF_SEARCH:
			result = new RecursiveBestFirstSearch(new AStarEvaluationFunction(
					hf));
			break;
		case HILL_SEARCH:
			result = new HillClimbingSearch(hf);
			break;
		}
		return result;
	}
}
