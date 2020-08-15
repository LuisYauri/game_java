package ia.core.entorno.mapa;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EntornoAbstracto;
import ia.core.agente.impl.PercepcionDinamica;

/**
 * Represents the environment a MapAgent can navigate.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class MapEnvironment extends EntornoAbstracto {

	private Map map = null;
	private MapEnvironmentState state = new MapEnvironmentState();

	public MapEnvironment(Map map) {
		this.map = map;
	}

	public void addAgent(Agente a, String startLocation) {
		// Ensure the agent state information is tracked before
		// adding to super, as super will notify the registered
		// EnvironmentViews that is was added.
		state.setAgentLocationAndTravelDistance(a, startLocation, 0.0);
		super.agregarAgent(a);
	}

	public String getAgentLocation(Agente a) {
		return state.getAgentLocation(a);
	}

	public Double getAgentTravelDistance(Agente a) {
		return state.getAgentTravelDistance(a);
	}

	@Override
	public EstadoEntorno getCurrentState() {
		return state;
	}

	@Override
	public EstadoEntorno executeAction(Agente agent, Accion a) {

		if (!a.esNoOp()) {
			MoveToAction act = (MoveToAction) a;

			String currLoc = getAgentLocation(agent);
			Double distance = map.getDistance(currLoc, act.getToLocation());
			if (distance != null) {
				double currTD = getAgentTravelDistance(agent);
				state.setAgentLocationAndTravelDistance(agent,
						act.getToLocation(), currTD + distance);
			}
		}

		return state;
	}

	@Override
	public Percepcion getPerceptSeenBy(Agente anAgent) {
		return new PercepcionDinamica(DynAttributeNames.PERCEPT_IN,
				getAgentLocation(anAgent));
	}

	public Map getMap() {
		return map;
	}
}