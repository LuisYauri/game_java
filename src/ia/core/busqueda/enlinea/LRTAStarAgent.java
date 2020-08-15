package ia.core.busqueda.enlinea;

import java.util.HashMap;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.AccionNoOp;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.framework.FuncionPercepcionAEstado;
import ia.core.util.estructuradedatos.TwoKeyHashMap;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 4.24, page
 * 152.<br>
 * <br>
 * 
 * <pre>
 * function LRTA*-AGENT(s') returns an action
 *   inputs: s', a percept that identifies the current state
 *   persistent: result, a table, indexed by state and action, initially empty
 *               H, a table of cost estimates indexed by state, initially empty
 *               s, a, the previous state and action, initially null
 *           
 *   if GOAL-TEST(s') then return stop
 *   if s' is a new state (not in H) then H[s'] &lt;- h(s')
 *   if s is not null
 *     result[s, a] &lt;- s'
 *     H[s] &lt;-        min LRTA*-COST(s, b, result[s, b], H)
 *             b (element of) ACTIONS(s)
 *   a &lt;- an action b in ACTIONS(s') that minimizes LRTA*-COST(s', b, result[s', b], H)
 *   s &lt;- s'
 *   return a
 *   
 * function LRTA*-COST(s, a, s', H) returns a cost estimate
 *   if s' is undefined then return h(s)
 *   else return c(s, a, s') + H[s']
 * </pre>
 * 
 * Figure 4.24 LRTA*-AGENT selects an action according to the value of
 * neighboring states, which are updated as the agent moves about the state
 * space.<br>
 * <br>
 * <b>Note:</b> This algorithm fails to exit if the goal does not exist (e.g.
 * A<->B Goal=X), this could be an issue with the implementation. Comments
 * welcome.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class LRTAStarAgent extends AgenteAbstracto {

	private OnlineSearchProblem problem;
	private FuncionPercepcionAEstado ptsFunction;
	private FuncionHeuristica hf;
	// persistent: result, a table, indexed by state and action, initially empty
	private final TwoKeyHashMap<Object, Accion, Object> result = new TwoKeyHashMap<Object, Accion, Object>();
	// H, a table of cost estimates indexed by state, initially empty
	private final HashMap<Object, Double> H = new HashMap<Object, Double>();
	// s, a, the previous state and action, initially null
	private Object s = null;
	private Accion a = null;

	/**
	 * Constructs a LRTA* agent with the specified search problem, percept to
	 * state function, and heuristic function.
	 * 
	 * @param problem
	 *            an online search problem for this agent to solve.
	 * @param ptsFunction
	 *            a function which returns the problem state associated with a
	 *            given Percepcion.
	 * @param hf
	 *            heuristic function <em>h(n)</em>, which estimates the cost of
	 *            the cheapest path from the state at node <em>n</em> to a goal
	 *            state.
	 */
	public LRTAStarAgent(OnlineSearchProblem problem,
			FuncionPercepcionAEstado ptsFunction, FuncionHeuristica hf) {
		setProblem(problem);
		setPerceptToStateFunction(ptsFunction);
		setHeuristicFunction(hf);
	}

	/**
	 * Returns the search problem of this agent.
	 * 
	 * @return the search problem of this agent.
	 */
	public OnlineSearchProblem getProblem() {
		return problem;
	}

	/**
	 * Sets the search problem for this agent to solve.
	 * 
	 * @param problem
	 *            the search problem for this agent to solve.
	 */
	public void setProblem(OnlineSearchProblem problem) {
		this.problem = problem;
		init();
	}

	/**
	 * Returns the percept to state function of this agent.
	 * 
	 * @return the percept to state function of this agent.
	 */
	public FuncionPercepcionAEstado getPerceptToStateFunction() {
		return ptsFunction;
	}

	/**
	 * Sets the percept to state function of this agent.
	 * 
	 * @param ptsFunction
	 *            a function which returns the problem state associated with a
	 *            given Percepcion.
	 */
	public void setPerceptToStateFunction(FuncionPercepcionAEstado ptsFunction) {
		this.ptsFunction = ptsFunction;
	}

	/**
	 * Returns the heuristic function of this agent.
	 */
	public FuncionHeuristica getHeuristicFunction() {
		return hf;
	}

	/**
	 * Sets the heuristic function of this agent.
	 * 
	 * @param hf
	 *            heuristic function <em>h(n)</em>, which estimates the cost of
	 *            the cheapest path from the state at node <em>n</em> to a goal
	 *            state.
	 */
	public void setHeuristicFunction(FuncionHeuristica hf) {
		this.hf = hf;
	}

	// function LRTA*-AGENT(s') returns an action
	// inputs: s', a percept that identifies the current state
	@Override
	public Accion ejecutar(Percepcion psDelta) {
		Object sDelta = ptsFunction.getState(psDelta);
		// if GOAL-TEST(s') then return stop
		if (goalTest(sDelta)) {
			a = AccionNoOp.NO_OP;
		} else {
			// if s' is a new state (not in H) then H[s'] <- h(s')
			if (!H.containsKey(sDelta)) {
				H.put(sDelta, getHeuristicFunction().h(sDelta));
			}
			// if s is not null
			if (null != s) {
				// result[s, a] <- s'
				result.put(s, a, sDelta);

				// H[s] <- min LRTA*-COST(s, b, result[s, b], H)
				// b (element of) ACTIONS(s)
				double min = Double.MAX_VALUE;
				for (Accion b : actions(s)) {
					double cost = lrtaCost(s, b, result.get(s, b));
					if (cost < min) {
						min = cost;
					}
				}
				H.put(s, min);
			}
			// a <- an action b in ACTIONS(s') that minimizes LRTA*-COST(s', b,
			// result[s', b], H)
			double min = Double.MAX_VALUE;
			// Just in case no actions
			a = AccionNoOp.NO_OP;
			for (Accion b : actions(sDelta)) {
				double cost = lrtaCost(sDelta, b, result.get(sDelta, b));
				if (cost < min) {
					min = cost;
					a = b;
				}
			}
		}

		// s <- s'
		s = sDelta;

		if (a.esNoOp()) {
			// I'm either at the Goal or can't get to it,
			// which in either case I'm finished so just die.
			setVitalidad(false);
		}
		// return a
		return a;
	}

	//
	// PRIVATE METHODS
	//
	private void init() {
		setVitalidad(true);
		result.clear();
		H.clear();
		s = null;
		a = null;
	}

	private boolean goalTest(Object state) {
		return getProblem().isGoalState(state);
	}

	// function LRTA*-COST(s, a, s', H) returns a cost estimate
	private double lrtaCost(Object s, Accion action, Object sDelta) {
		// if s' is undefined then return h(s)
		if (null == sDelta) {
			return getHeuristicFunction().h(s);
		}
		// else return c(s, a, s') + H[s']
		return getProblem().getStepCostFunction().c(s, action, sDelta)
				+ H.get(sDelta);
	}

	private Set<Accion> actions(Object state) {
		return problem.getActionsFunction().actions(state);
	}
}
