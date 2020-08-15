package ia.core.entorno.aspiradora;

import java.util.LinkedList;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.AccionNoOp;
import ia.core.busqueda.framework.FuncionPercepcionAEstado;
import ia.core.busqueda.nodeterministico.AndOrSearch;
import ia.core.busqueda.nodeterministico.IfStateThenPlan;
import ia.core.busqueda.nodeterministico.NondeterministicProblem;
import ia.core.busqueda.nodeterministico.Plan;

/**
 * This agent traverses the NondeterministicVacuumEnvironment using a
 * contingency plan. See page 135, AIMA3e.
 * 
 * @author Andrew Brown
 */
public class NondeterministicVacuumAgent extends AgenteAbstracto {
	private NondeterministicProblem problem;
	private FuncionPercepcionAEstado ptsFunction;
	private Plan contingencyPlan;
	private LinkedList<Object> stack = new LinkedList<Object>();

	public NondeterministicVacuumAgent(FuncionPercepcionAEstado ptsFunction) {
		setPerceptToStateFunction(ptsFunction);
	}

	/**
	 * Returns the search problem for this agent.
	 * 
	 * @return the search problem for this agent.
	 */
	public NondeterministicProblem getProblem() {
		return problem;
	}

	/**
	 * Sets the search problem for this agent to solve.
	 * 
	 * @param problem
	 *            the search problem for this agent to solve.
	 */
	public void setProblem(NondeterministicProblem problem) {
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
	 * Sets the percept to state functino of this agent.
	 * 
	 * @param ptsFunction
	 *            a function which returns the problem state associated with a
	 *            given Percepcion.
	 */
	public void setPerceptToStateFunction(FuncionPercepcionAEstado ptsFunction) {
		this.ptsFunction = ptsFunction;
	}

	/**
	 * Return the agent contingency plan
	 * 
	 * @return the plan the agent uses to clean the vacuum world
	 */
	public Plan getContingencyPlan() {
		if (this.contingencyPlan == null) {
			throw new RuntimeException("Contingency plan not set.");
		}
		return this.contingencyPlan;
	}

	/**
	 * Execute an action from the contingency plan
	 * 
	 * @param percept
	 * @return an action from the contingency plan.
	 */
	@Override
	public Accion ejecutar(Percepcion percept) {
		// check if goal state
		VacuumEnvironmentState state = (VacuumEnvironmentState) this
				.getPerceptToStateFunction().getState(percept);
		if (state.getLocationState(VacuumEnvironment.LOCATION_A) == VacuumEnvironment.LocationState.Clean
				&& state.getLocationState(VacuumEnvironment.LOCATION_B) == VacuumEnvironment.LocationState.Clean) {
			return AccionNoOp.NO_OP;
		}
		// check stack size
		if (this.stack.size() < 1) {
			if (this.contingencyPlan.size() < 1) {
				return AccionNoOp.NO_OP;
			} else {
				this.stack.push(this.getContingencyPlan().removeFirst());
			}
		}
		// pop...
		Object currentStep = this.stack.peek();
		// push...
		if (currentStep instanceof Accion) {
			return (Accion) this.stack.pop();
		} // case: next step is a plan
		else if (currentStep instanceof Plan) {
			Plan newPlan = (Plan) currentStep;
			if (newPlan.size() > 0) {
				this.stack.push(newPlan.removeFirst());
			} else {
				this.stack.pop();
			}
			return this.ejecutar(percept);
		} // case: next step is an if-then
		else if (currentStep instanceof IfStateThenPlan) {
			IfStateThenPlan conditional = (IfStateThenPlan) this.stack.pop();
			this.stack.push(conditional.ifStateMatches(percept));
			return this.ejecutar(percept);
		} // case: ignore next step if null
		else if (currentStep == null) {
			this.stack.pop();
			return this.ejecutar(percept);
		} else {
			throw new RuntimeException("Unrecognized contingency plan step.");
		}
	}

	//
	// PRIVATE METHODS
	//
	private void init() {
		setVitalidad(true);
		stack.clear();
		AndOrSearch andOrSearch = new AndOrSearch();
		this.contingencyPlan = andOrSearch.search(this.problem);
	}
}
