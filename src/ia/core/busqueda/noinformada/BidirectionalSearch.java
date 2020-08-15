package ia.core.busqueda.noinformada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ia.core.agente.Accion;
import ia.core.busqueda.framework.ProblemaBidireccional;
import ia.core.busqueda.framework.BusquedaDeGrafo;
import ia.core.busqueda.framework.Metricas;
import ia.core.busqueda.framework.Nodo;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.UtilidadesDeBusqueda;
import ia.core.util.estructuradedatos.FIFOQueue;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 90.<br>
 * <br>
 * Bidirectional search.<br>
 * <br>
 * <b>Note:</b> Based on the description of this algorithm i.e. 'Bidirectional
 * search is implemented by replacing the goal test with a check to see whether
 * the frontiers of the two searches intersect;', it is possible for the
 * searches to pass each other's frontiers by, in particular if the problem is
 * not fully reversible (i.e. unidirectional links on a graph), and could
 * instead intersect at the explored set.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class BidirectionalSearch implements Busqueda {
	public enum SearchOutcome {
		PATH_FOUND_FROM_ORIGINAL_PROBLEM, PATH_FOUND_FROM_REVERSE_PROBLEM, PATH_FOUND_BETWEEN_PROBLEMS, PATH_NOT_FOUND
	};

	protected Metricas metrics;

	private SearchOutcome searchOutcome = SearchOutcome.PATH_NOT_FOUND;

	private static final String NODES_EXPANDED = "nodesExpanded";

	private static final String QUEUE_SIZE = "queueSize";

	private static final String MAX_QUEUE_SIZE = "maxQueueSize";

	private static final String PATH_COST = "pathCost";

	public BidirectionalSearch() {
		metrics = new Metricas();
	}

	public List<Accion> search(Problema p) throws Exception {

		assert (p instanceof ProblemaBidireccional);

		searchOutcome = SearchOutcome.PATH_NOT_FOUND;

		clearInstrumentation();

		Problema op = ((ProblemaBidireccional) p).getOriginalProblem();
		Problema rp = ((ProblemaBidireccional) p).getReverseProblem();

		CachedStateQueue<Nodo> opFrontier = new CachedStateQueue<Nodo>();
		CachedStateQueue<Nodo> rpFrontier = new CachedStateQueue<Nodo>();

		BusquedaDeGrafo ogs = new BusquedaDeGrafo();
		BusquedaDeGrafo rgs = new BusquedaDeGrafo();
		// Ensure the instrumentation for these
		// are cleared down as their values
		// are used in calculating the overall
		// bidirectional metrics.
		ogs.clearInstrumentation();
		rgs.clearInstrumentation();

		Nodo opNode = new Nodo(op.getInitialState());
		Nodo rpNode = new Nodo(rp.getInitialState());
		opFrontier.insert(opNode);
		rpFrontier.insert(rpNode);

		setQueueSize(opFrontier.size() + rpFrontier.size());
		setNodesExpanded(ogs.getNodesExpanded() + rgs.getNodesExpanded());

		while (!(opFrontier.isEmpty() && rpFrontier.isEmpty())) {
			// Determine the nodes to work with and expand their fringes
			// in preparation for testing whether or not the two
			// searches meet or one or other is at the GOAL.
			if (!opFrontier.isEmpty()) {
				opNode = opFrontier.pop();
				opFrontier.addAll(ogs.getResultingNodesToAddToFrontier(opNode,
						op));
			} else {
				opNode = null;
			}
			if (!rpFrontier.isEmpty()) {
				rpNode = rpFrontier.pop();
				rpFrontier.addAll(rgs.getResultingNodesToAddToFrontier(rpNode,
						rp));
			} else {
				rpNode = null;
			}

			setQueueSize(opFrontier.size() + rpFrontier.size());
			setNodesExpanded(ogs.getNodesExpanded() + rgs.getNodesExpanded());

			//
			// First Check if either frontier contains the other's state
			if (null != opNode && null != rpNode) {
				Nodo popNode = null;
				Nodo prpNode = null;
				if (opFrontier.containsNodeBasedOn(rpNode.getState())) {
					popNode = opFrontier.getNodeBasedOn(rpNode.getState());
					prpNode = rpNode;
				} else if (rpFrontier.containsNodeBasedOn(opNode.getState())) {
					popNode = opNode;
					prpNode = rpFrontier.getNodeBasedOn(opNode.getState());
					// Need to also check whether or not the nodes that
					// have been taken off the frontier actually represent the
					// same state, otherwise there are instances whereby
					// the searches can pass each other by
				} else if (opNode.getState().equals(rpNode.getState())) {
					popNode = opNode;
					prpNode = rpNode;
				}
				if (null != popNode && null != prpNode) {
					List<Accion> actions = retrieveActions(op, rp, popNode,
							prpNode);
					// It may be the case that it is not in fact possible to
					// traverse from the original node to the goal node based on
					// the reverse path (i.e. unidirectional links: e.g.
					// InitialState(A)<->C<-Goal(B) )
					if (null != actions) {
						return actions;
					}
				}
			}

			//
			// Check if the original problem is at the GOAL state
			if (null != opNode && UtilidadesDeBusqueda.isGoalState(op, opNode)) {
				// No need to check return value for null here
				// as an action path discovered from the goal
				// is guaranteed to exist
				return retrieveActions(op, rp, opNode, null);
			}
			//
			// Check if the reverse problem is at the GOAL state
			if (null != rpNode && UtilidadesDeBusqueda.isGoalState(rp, rpNode)) {
				List<Accion> actions = retrieveActions(op, rp, null, rpNode);
				// It may be the case that it is not in fact possible to
				// traverse from the original node to the goal node based on
				// the reverse path (i.e. unidirectional links: e.g.
				// InitialState(A)<-Goal(B) )
				if (null != actions) {
					return actions;
				}
			}
		}

		// Empty List can indicate already at Goal
		// or unable to find valid set of actions
		return new ArrayList<Accion>();
	}

	/**
	 * Returns PATH_FOUND_FROM_ORIGINAL_PROBLEM if the path was found from the
	 * initial state, PATH_FOUND_FROM_REVERSE_PROBLEM if the path was found from
	 * a goal, PATH_FOUND_FROM_BETWEEN_PROBLEMS if a branch from the initial
	 * state met a branch from a goal state, or PATH_NOT_FOUND if no path from
	 * the initial state to a goal state was found.
	 * 
	 * @return PATH_FOUND_FROM_ORIGINAL_PROBLEM if the path was found from the
	 *         initial state, PATH_FOUND_FROM_REVERSE_PROBLEM if the path was
	 *         found from a goal, PATH_FOUND_FROM_BETWEEN_PROBLEMS if a branch
	 *         from the initial state met a branch from a goal state, or
	 *         PATH_NOT_FOUND if no path from the initial state to a goal state
	 *         was found.
	 */
	public SearchOutcome getSearchOutcome() {
		return searchOutcome;
	}

	/**
	 * Returns all the metrics of the search.
	 * 
	 * @return all the metrics of the search.
	 */
	public Metricas getMetrics() {
		return metrics;
	}

	/**
	 * Sets all metrics to zero.
	 */
	public void clearInstrumentation() {
		metrics.set(NODES_EXPANDED, 0);
		metrics.set(QUEUE_SIZE, 0);
		metrics.set(MAX_QUEUE_SIZE, 0);
		metrics.set(PATH_COST, 0.0);
	}

	/**
	 * Returns the number of nodes expanded.
	 * 
	 * @return the number of nodes expanded.
	 */
	public int getNodesExpanded() {
		return metrics.getInt(NODES_EXPANDED);
	}

	/**
	 * Sets the number of nodes expanded.
	 * 
	 * @param nodesExpanded
	 *            the number of nodes expanded
	 */
	public void setNodesExpanded(int nodesExpanded) {
		metrics.set(NODES_EXPANDED, nodesExpanded);
	}

	/**
	 * Returns the queue size.
	 * 
	 * @return the queue size.
	 */
	public int getQueueSize() {
		return metrics.getInt(QUEUE_SIZE);
	}

	/**
	 * Sets the queue size and adjusts the maximum queue size if the specified
	 * size is greater than the current maximum.
	 * 
	 * @param queueSize
	 *            the number of items in the queue.
	 */
	public void setQueueSize(int queueSize) {
		metrics.set(QUEUE_SIZE, queueSize);
		int maxQSize = metrics.getInt(MAX_QUEUE_SIZE);
		if (queueSize > maxQSize) {
			metrics.set(MAX_QUEUE_SIZE, queueSize);
		}
	}

	/**
	 * Returns the maximum queue size.
	 * 
	 * @return the maximum queue size.
	 */
	public int getMaxQueueSize() {
		return metrics.getInt(MAX_QUEUE_SIZE);
	}

	/**
	 * Returns the cost of the path from the initial state to a goal state.
	 * 
	 * @return the cost of the path from the initial state to a goal state.
	 */
	public double getPathCost() {
		return metrics.getDouble(PATH_COST);
	}

	/**
	 * Sets the cost of the path from the initial state to a goal state.
	 * 
	 * @param pathCost
	 *            the cost of the path from the initial state to a goal state.
	 */
	public void setPathCost(Double pathCost) {
		metrics.set(PATH_COST, pathCost);
	}

	//
	// PRIVATE METHODS
	//
	private List<Accion> retrieveActions(Problema op, Problema rp,
			Nodo originalPath, Nodo reversePath) {
		List<Accion> actions = new ArrayList<Accion>();

		if (null == reversePath) {
			// This is the simple case whereby the path has been found
			// from the original problem first
			setPathCost(originalPath.getPathCost());
			searchOutcome = SearchOutcome.PATH_FOUND_FROM_ORIGINAL_PROBLEM;
			actions = UtilidadesDeBusqueda.actionsFromNodes(originalPath
					.getPathFromRoot());
		} else {
			List<Nodo> nodePath = new ArrayList<Nodo>();
			Object originalState = null;
			if (null != originalPath) {
				nodePath.addAll(originalPath.getPathFromRoot());
				originalState = originalPath.getState();
			}
			// Only append the reverse path if it is not the
			// GOAL state from the original problem (if you don't
			// you could end up appending a partial reverse path
			// that looks back on its initial state)
			if (!UtilidadesDeBusqueda.isGoalState(op, reversePath)) {
				List<Nodo> rpath = reversePath.getPathFromRoot();
				for (int i = rpath.size() - 1; i >= 0; i--) {
					// Ensure do not include the node from the reverse path
					// that is the one that potentially overlaps with the
					// original path (i.e. if started in goal state or where
					// they meet in the middle).
					if (!rpath.get(i).getState().equals(originalState)) {
						nodePath.add(rpath.get(i));
					}
				}
			}

			if (!canTraversePathFromOriginalProblem(op, nodePath, actions)) {
				// This is where it is possible to get to the initial state
				// from the goal state (i.e. reverse path) but not the other way
				// round, null returned to indicate an invalid path found from
				// the reverse problem
				return null;
			}

			if (null == originalPath) {
				searchOutcome = SearchOutcome.PATH_FOUND_FROM_REVERSE_PROBLEM;
			} else {
				// Need to ensure that where the original and reverse paths
				// overlap, as they can link based on their fringes, that
				// the reverse path is actually capable of connecting to
				// the previous node in the original path (if not root).
				if (canConnectToOriginalFromReverse(rp, originalPath,
						reversePath)) {
					searchOutcome = SearchOutcome.PATH_FOUND_BETWEEN_PROBLEMS;
				} else {
					searchOutcome = SearchOutcome.PATH_FOUND_FROM_ORIGINAL_PROBLEM;
				}
			}
		}

		return actions;
	}

	private boolean canTraversePathFromOriginalProblem(Problema op,
			List<Nodo> path, List<Accion> actions) {
		boolean rVal = true;
		double pc = 0.0;

		for (int i = 0; i < (path.size() - 1); i++) {
			Object currentState = path.get(i).getState();
			Object nextState = path.get(i + 1).getState();
			boolean found = false;
			for (Accion a : op.getActionsFunction().actions(currentState)) {
				Object isNext = op.getResultFunction().result(currentState, a);
				if (nextState.equals(isNext)) {
					found = true;
					pc += op.getStepCostFunction()
							.c(currentState, a, nextState);
					actions.add(a);
					break;
				}
			}

			if (!found) {
				rVal = false;
				break;
			}
		}

		setPathCost(true == rVal ? pc : 0.0);

		return rVal;
	}

	private boolean canConnectToOriginalFromReverse(Problema rp,
			Nodo originalPath, Nodo reversePath) {
		boolean rVal = true;

		// Only need to test if not already at root
		if (!originalPath.isRootNode()) {
			rVal = false;
			for (Accion a : rp.getActionsFunction().actions(
					reversePath.getState())) {
				Object nextState = rp.getResultFunction().result(
						reversePath.getState(), a);
				if (originalPath.getParent().getState().equals(nextState)) {
					rVal = true;
					break;
				}
			}
		}

		return rVal;
	}
}

class CachedStateQueue<E> extends FIFOQueue<E> {
	private static final long serialVersionUID = 1;
	//
	private Map<Object, Nodo> cachedState = new HashMap<Object, Nodo>();

	public CachedStateQueue() {
		super();
	}

	public CachedStateQueue(Collection<? extends E> c) {
		super(c);
	}

	public boolean containsNodeBasedOn(Object state) {
		return cachedState.containsKey(state);
	}

	public Nodo getNodeBasedOn(Object state) {
		return cachedState.get(state);
	}

	//
	// START-Queue
	public E pop() {
		E popped = super.pop();
		cachedState.remove(((Nodo) popped).getState());
		return popped;
	}

	// END-Queue
	//

	// Note: This is called by FIFOQueue.insert()->LinkedList.offer();
	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added) {
			cachedState.put(((Nodo) element).getState(), (Nodo) element);
		}
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E element : c) {
			cachedState.put(((Nodo) element).getState(), (Nodo) element);
		}
		return super.addAll(c);
	}
}
