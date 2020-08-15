package ia.core.entorno.aspiradora;

import java.util.Random;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EntornoAbstracto;
import ia.core.agente.impl.AccionDinamica;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): pg 58.<br>
 * <br>
 * Let the world contain just two locations. Each location may or may not
 * contain dirt, and the agent may be in one location or the other. There are 8
 * possible world states, as shown in Figure 3.2. The agent has three possible
 * actions in this version of the vacuum world: <em>Left</em>, <em>Right</em>,
 * and <em>Suck</em>. Assume for the moment, that sucking is 100% effective. The
 * goal is to clean up all the dirt.
 * 
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class VacuumEnvironment extends EntornoAbstracto {
	// Allowable Actions within the Vacuum Environment
	public static final Accion ACTION_MOVE_LEFT = new AccionDinamica("Left");
	public static final Accion ACTION_MOVE_RIGHT = new AccionDinamica("Right");
	public static final Accion ACTION_SUCK = new AccionDinamica("Suck");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	public enum LocationState {
		Clean, Dirty
	};

	//
	protected VacuumEnvironmentState envState = null;
	protected boolean isDone = false;

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed at random.
	 */
	public VacuumEnvironment() {
		Random r = new Random();
		envState = new VacuumEnvironmentState(
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty,
				0 == r.nextInt(2) ? LocationState.Clean : LocationState.Dirty);
	}

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed as specified.
	 * 
	 * @param locAState
	 *            the initial state of location A, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 * @param locBState
	 *            the initial state of location B, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 */
	public VacuumEnvironment(LocationState locAState, LocationState locBState) {
		envState = new VacuumEnvironmentState(locAState, locBState);
	}

	@Override
	public EstadoEntorno getCurrentState() {
		return envState;
	}

	@Override
	public EstadoEntorno executeAction(Agente a, Accion agentAction) {

		if (ACTION_MOVE_RIGHT == agentAction) {
			envState.setAgentLocation(a, LOCATION_B);
			updatePerformanceMeasure(a, -1);
		} else if (ACTION_MOVE_LEFT == agentAction) {
			envState.setAgentLocation(a, LOCATION_A);
			updatePerformanceMeasure(a, -1);
		} else if (ACTION_SUCK == agentAction) {
			if (LocationState.Dirty == envState.getLocationState(envState
					.getAgentLocation(a))) {
				envState.setLocationState(envState.getAgentLocation(a),
						LocationState.Clean);
				updatePerformanceMeasure(a, 10);
			}
		} else if (agentAction.esNoOp()) {
			// In the Vacuum Environment we consider things done if
			// the agent generates a NoOp.
			isDone = true;
		}

		return envState;
	}

	@Override
	public Percepcion getPerceptSeenBy(Agente anAgent) {
		if (anAgent instanceof NondeterministicVacuumAgent) {
    		// Note: implements FullyObservableVacuumEnvironmentPercept
    		return new VacuumEnvironmentState(this.envState);
    	}
		String agentLocation = envState.getAgentLocation(anAgent);
		return new LocalVacuumEnvironmentPercept(agentLocation,
				envState.getLocationState(agentLocation));
	}

	@Override
	public boolean estaHecha() {
		return super.estaHecha() || isDone;
	}

	@Override
	public void agregarAgent(Agente a) {
		int idx = new Random().nextInt(2);
		envState.setAgentLocation(a, idx == 0 ? LOCATION_A : LOCATION_B);
		super.agregarAgent(a);
	}

	public void addAgent(Agente a, String location) {
		// Ensure the agent state information is tracked before
		// adding to super, as super will notify the registered
		// EnvironmentViews that is was added.
		envState.setAgentLocation(a, location);
		super.agregarAgent(a);
	}

	public LocationState getLocationState(String location) {
		return envState.getLocationState(location);
	}

	public String getAgentLocation(Agente a) {
		return envState.getAgentLocation(a);
	}
}