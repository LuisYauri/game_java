package ia.core.busqueda.enlinea;

import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionCostoDePasoPorDefecto;
import ia.core.busqueda.framework.PruebaDeMeta;
import ia.core.busqueda.framework.FuncionCostoDePaso;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 147.<br>
 * <br>
 * An online search problem must be solved by an agent executing actions, rather
 * than by pure computation. We assume a deterministic and fully observable
 * environment (Chapter 17 relaxes these assumptions), but we stipulate that the
 * agent knows only the following: <br>
 * <ul>
 * <li>ACTIONS(s), which returns a list of actions allowed in state s;</li>
 * <li>The step-cost function c(s, a, s') - note that this cannot be used until
 * the agent knows that s' is the outcome; and</li>
 * <li>GOAL-TEST(s).</li>
 * </ul>
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class OnlineSearchProblem {

	protected FuncionAcciones actionsFunction;

	protected FuncionCostoDePaso stepCostFunction;

	protected PruebaDeMeta goalTest;

	/**
	 * Constructs an online search problem with the specified action function,
	 * goal test, and a default step cost function.
	 * 
	 * @param actionsFunction
	 *            ACTIONS(s), which returns a list of actions allowed in state s
	 * @param goalTest
	 *            GOAL-TEST(s), which the agent can apply to a single state
	 *            description to determine if it is a goal state
	 */
	public OnlineSearchProblem(FuncionAcciones actionsFunction,
			PruebaDeMeta goalTest) {
		this(actionsFunction, goalTest, new FuncionCostoDePasoPorDefecto());
	}

	/**
	 * Constructs an online search problem with the specified action function,
	 * goal test, and a default step cost function.
	 * 
	 * @param actionsFunction
	 *            ACTIONS(s), which returns a list of actions allowed in state s
	 * @param goalTest
	 *            GOAL-TEST(s), which the agent can apply to a single state
	 *            description to determine if it is a goal state
	 * @param stepCostFunction
	 *            the step-cost function c(s, a, s') - note that this cannot be
	 *            used until the agent knows that s' is the outcome
	 */
	public OnlineSearchProblem(FuncionAcciones actionsFunction,
			PruebaDeMeta goalTest, FuncionCostoDePaso stepCostFunction) {
		this.actionsFunction = actionsFunction;
		this.goalTest = goalTest;
		this.stepCostFunction = stepCostFunction;
	}

	/**
	 * Returns the action function of this online search problem.
	 * 
	 * @return the action function of this online search problem.
	 */
	public FuncionAcciones getActionsFunction() {
		return actionsFunction;
	}

	/**
	 * Returns <code>true</code> if the given state is a goal state.
	 * 
	 * @param state
	 *            an object representing a state
	 * 
	 * @return <code>true</code> if the given state is a goal state.
	 */
	public boolean isGoalState(Object state) {

		return goalTest.isGoalState(state);
	}

	/**
	 * Returns the step cost function of this online search problem.
	 * 
	 * @return the step cost function of this online search problem.
	 */
	public FuncionCostoDePaso getStepCostFunction() {
		return stepCostFunction;
	}

	//
	// PROTECTED METHODS
	//
	protected OnlineSearchProblem() {
	}
}