package ia.core.busqueda.framework;

import java.util.ArrayList;
import java.util.List;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.Estado;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.AccionNoOp;
import ia.core.util.Util;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 3.1, page 67.<br>
 * <br>
 * 
 * <pre>
 * function SIMPLE-PROBLEM-SOLVING-AGENT(percept) returns an action
 *   persistent: seq, an action sequence, initially empty
 *               state, some description of the current world state
 *               goal, a goal, initially null
 *               problem, a problem formulation
 *           
 *   state &lt;- UPDATE-STATE(state, percept)
 *   if seq is empty then
 *     goal    &lt;- FORMULATE-GOAL(state)
 *     problem &lt;- FORMULATE-PROBLEM(state, goal)
 *     seq     &lt;- SEARCH(problem)
 *     if seq = failure then return a null action
 *   action &lt;- FIRST(seq)
 *   seq &lt;- REST(seq)
 *   return action
 * </pre>
 * 
 * Figure 3.1 A simple problem-solving agent. It first formulates a goal and a
 * problem, searches for a sequence of actions that would solve the problem, and
 * then executes the actions one at a time. When this is complete, it formulates
 * another goal and starts over.<br>
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public abstract class AgenteSolucionadorDeProblemaSimple extends AgenteAbstracto {

	// seq, an action sequence, initially empty
	private List<Accion> seq = new ArrayList<Accion>();

	//
	private boolean formulateGoalsIndefinitely = true;

	private int maxGoalsToFormulate = 1;

	private int goalsFormulated = 0;

	/**
	 * Constructs a simple problem solving agent which will formulate goals
	 * indefinitely.
	 */
	public AgenteSolucionadorDeProblemaSimple() {
		formulateGoalsIndefinitely = true;
	}

	/**
	 * Constructs a simple problem solving agent which will formulate, at
	 * maximum, the specified number of goals.
	 * 
	 * @param maxGoalsToFormulate
	 *            the maximum number of goals this agent is to formulate.
	 */
	public AgenteSolucionadorDeProblemaSimple(int maxGoalsToFormulate) {
		formulateGoalsIndefinitely = false;
		this.maxGoalsToFormulate = maxGoalsToFormulate;
	}

	// function SIMPLE-PROBLEM-SOLVING-AGENT(percept) returns an action
	@Override
	public Accion ejecutar(Percepcion p) {
		Accion action = AccionNoOp.NO_OP;

		// state <- UPDATE-STATE(state, percept)
		updateState(p);
		// if seq is empty then do
		if (0 == seq.size()) {
			if (formulateGoalsIndefinitely
					|| goalsFormulated < maxGoalsToFormulate) {
				if (goalsFormulated > 0) {
					notifyViewOfMetrics();
				}
				// goal <- FORMULATE-GOAL(state)
				Object goal = formulateGoal();
				goalsFormulated++;
				// problem <- FORMULATE-PROBLEM(state, goal)
				Problema problem = formulateProblem(goal);
				// seq <- SEARCH(problem)
				seq.addAll(search(problem));
				if (0 == seq.size()) {
					// Unable to identify a path
					seq.add(AccionNoOp.NO_OP);
				}
			} else {
				// Agent no longer wishes to
				// achieve any more goals
				setVitalidad(false);
				notifyViewOfMetrics();
			}
		}

		if (seq.size() > 0) {
			// action <- FIRST(seq)
			action = Util.first(seq);
			// seq <- REST(seq)
			seq = Util.rest(seq);
		}

		return action;
	}

	//
	// PROTECTED METHODS
	//
	protected abstract Estado updateState(Percepcion p);

	protected abstract Object formulateGoal();

	protected abstract Problema formulateProblem(Object goal);

	protected abstract List<Accion> search(Problema problem);

	protected abstract void notifyViewOfMetrics();
}