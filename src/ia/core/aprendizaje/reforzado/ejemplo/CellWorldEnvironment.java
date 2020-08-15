package ia.core.aprendizaje.reforzado.ejemplo;

import java.util.LinkedHashSet;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EntornoAbstracto;
import ia.core.entorno.mundocelda.Cell;
import ia.core.entorno.mundocelda.CellWorldAction;
import ia.core.probabilidad.pdm.TransitionProbabilityFunction;
import ia.core.util.Randomizer;

/**
 * Implementation of the Cell World Environment, supporting the execution of
 * trials for reinforcement learning agents.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class CellWorldEnvironment extends EntornoAbstracto {
	private Cell<Double> startingCell = null;
	private Set<Cell<Double>> allStates = new LinkedHashSet<Cell<Double>>();
	private TransitionProbabilityFunction<Cell<Double>, CellWorldAction> tpf;
	private Randomizer r = null;
	private CellWorldEnvironmentState currentState = new CellWorldEnvironmentState();

	/**
	 * Constructor.
	 * 
	 * @param startingCell
	 *            the cell that agent(s) are to start from at the beginning of
	 *            each trial within the environment.
	 * @param allStates
	 *            all the possible states in this environment.
	 * @param tpf
	 *            the transition probability function that simulates how the
	 *            environment is meant to behave in response to an agent action.
	 * @param r
	 *            a Randomizer used to sample actions that are actually to be
	 *            executed based on the transition probabilities for actions.
	 */
	public CellWorldEnvironment(Cell<Double> startingCell,
			Set<Cell<Double>> allStates,
			TransitionProbabilityFunction<Cell<Double>, CellWorldAction> tpf,
			Randomizer r) {
		this.startingCell = startingCell;
		this.allStates.addAll(allStates);
		this.tpf = tpf;
		this.r = r;
	}

	/**
	 * Execute N trials.
	 * 
	 * @param n
	 *            the number of trials to ejecutar.
	 */
	public void executeTrials(int n) {
		for (int i = 0; i < n; i++) {
			executeTrial();
		}
	}

	/**
	 * Execute a single trial.
	 */
	public void executeTrial() {
		currentState.reset();
		for (Agente a : agents) {
			a.setVitalidad(true);
			currentState.setAgentLocation(a, startingCell);
		}
		pasoHastaTerminar();
	}

	@Override
	public EstadoEntorno getCurrentState() {
		return currentState;
	}

	@Override
	public EstadoEntorno executeAction(Agente agent, Accion action) {
		if (!action.esNoOp()) {
			Cell<Double> s = currentState.getAgentLocation(agent);
			double probabilityChoice = r.nextDouble();
			double total = 0;
			boolean set = false;
			for (Cell<Double> sDelta : allStates) {
				total += tpf.probability(sDelta, s, (CellWorldAction) action);
				if (total > 1.0) {
					throw new IllegalStateException("Bad probability calculation.");
				}
				if (total > probabilityChoice) {
					currentState.setAgentLocation(agent, sDelta);
					set = true;
					break;
				}
			}
			if (!set) {
				throw new IllegalStateException("Failed to simulate the action="+action+" correctly from s="+s);
			}
		}

		return currentState;
	}

	@Override
	public Percepcion getPerceptSeenBy(Agente anAgent) {
		return currentState.getPerceptFor(anAgent);
	}
}