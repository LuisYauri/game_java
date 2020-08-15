package ia.core.busqueda.nodeterministico;

import ia.core.busqueda.framework.*;

/**
 * Non-deterministic problems may have multiple results for a given state and
 action; this class handles these results by mimicking Problem and replacing
 FuncionResultado (one result) with ResultsFunction (a set of results).
 * 
 * @author Andrew Brown
 */
public class NondeterministicProblem {

	protected Object initialState;
	protected FuncionAcciones actionsFunction;
	protected FuncionResultado resultFunction;
	protected PruebaDeMeta goalTest;
	protected FuncionCostoDePaso stepCostFunction;
	protected ResultsFunction resultsFunction;

	/**
	 * Constructor
	 * 
	 * @param initialState
	 * @param actionsFunction
	 * @param resultsFunction
	 * @param goalTest
	 */
	public NondeterministicProblem(Object initialState,
			FuncionAcciones actionsFunction, ResultsFunction resultsFunction,
			PruebaDeMeta goalTest) {
		this(initialState, actionsFunction, resultsFunction, goalTest,
				new FuncionCostoDePasoPorDefecto());
	}

	/**
	 * Constructor
	 * 
	 * @param initialState
	 * @param actionsFunction
	 * @param resultsFunction
	 * @param goalTest
	 * @param stepCostFunction
	 */
	public NondeterministicProblem(Object initialState,
			FuncionAcciones actionsFunction, ResultsFunction resultsFunction,
			PruebaDeMeta goalTest, FuncionCostoDePaso stepCostFunction) {
		this.initialState = initialState;
		this.actionsFunction = actionsFunction;
		this.resultsFunction = resultsFunction;
		this.goalTest = goalTest;
		this.stepCostFunction = stepCostFunction;
	}

	/**
	 * Returns the initial state of the agent.
	 * 
	 * @return the initial state of the agent.
	 */
	public Object getInitialState() {
		return initialState;
	}

	/**
	 * Returns <code>true</code> if the given state is a goal state.
	 * 
	 * @return <code>true</code> if the given state is a goal state.
	 */
	public boolean isGoalState(Object state) {
		return goalTest.isGoalState(state);
	}

	/**
	 * Returns the goal test.
	 * 
	 * @return the goal test.
	 */
	public PruebaDeMeta getGoalTest() {
		return goalTest;
	}

	/**
	 * Returns the description of the possible actions available to the agent.
	 * 
	 * @return the description of the possible actions available to the agent.
	 */
	public FuncionAcciones getActionsFunction() {
		return actionsFunction;
	}

	/**
	 * Return the description of what each action does.
	 * 
	 * @return the description of what each action does.
	 */
	public ResultsFunction getResultsFunction() {
		return this.resultsFunction;
	}

	/**
	 * Returns the path cost function.
	 * 
	 * @return the path cost function.
	 */
	public FuncionCostoDePaso getStepCostFunction() {
		return stepCostFunction;
	}
}
