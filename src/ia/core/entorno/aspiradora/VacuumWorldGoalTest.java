package ia.core.entorno.aspiradora;

import ia.core.agente.Agente;
import ia.core.busqueda.framework.PruebaDeMeta;

/**
 * Tests for goals states
 * 
 * @author Andrew Brown
 */
public class VacuumWorldGoalTest implements PruebaDeMeta {

	private Agente agent;

	/**
	 * Constructor
	 * 
	 * @param agent
	 */
	public VacuumWorldGoalTest(Agente agent) {
		this.agent = agent;
	}

	/**
	 * Tests whether the search has identified a goal state
	 * 
	 * @param state
	 * @return true if the state is a goal state, false otherwise.
	 */
	@Override
	public boolean isGoalState(Object state) {
		// setup
		VacuumEnvironmentState vacEnvState = (VacuumEnvironmentState) state;
		String currentLocation = vacEnvState.getAgentLocation(this.agent);
		String adjacentLocation = (currentLocation
				.equals(VacuumEnvironment.LOCATION_A)) ? VacuumEnvironment.LOCATION_B
				: VacuumEnvironment.LOCATION_A;
		// test goal state
		if (VacuumEnvironment.LocationState.Clean != vacEnvState
				.getLocationState(currentLocation)) {
			return false;
		} else if (VacuumEnvironment.LocationState.Clean != vacEnvState
				.getLocationState(adjacentLocation)) {
			return false;
		} else {
			return true;
		}
	}
}