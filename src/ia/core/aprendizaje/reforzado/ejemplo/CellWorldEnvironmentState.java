package ia.core.aprendizaje.reforzado.ejemplo;

import java.util.HashMap;
import java.util.Map;

import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.entorno.mundocelda.Cell;

/**
 * An implementation of the EstadoEntorno interface for a Cell World.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class CellWorldEnvironmentState implements EstadoEntorno {
	private Map<Agente, CellWorldPercept> agentLocations = new HashMap<Agente, CellWorldPercept>();

	/**
	 * Default Constructor.
	 */
	public CellWorldEnvironmentState() {
	}

	/**
	 * Reset the environment state to its default state.
	 */
	public void reset() {
		agentLocations.clear();
	}

	/**
	 * Set an agent's location within the cell world environment.
	 * 
	 * @param anAgent
	 *            the agents whose location is to be tracked.
	 * @param location
	 *            the location for the agent in the cell world environment.
	 */
	public void setAgentLocation(Agente anAgent, Cell<Double> location) {
		CellWorldPercept percept = agentLocations.get(anAgent);
		if (null == percept) {
			percept = new CellWorldPercept(location);
			agentLocations.put(anAgent, percept);
		} else {
			percept.setCell(location);
		}
	}

	/**
	 * Get the location of an agent within the cell world environment.
	 * 
	 * @param anAgent
	 *            the agent whose location is being queried.
	 * @return the location of the agent within the cell world environment.
	 */
	public Cell<Double> getAgentLocation(Agente anAgent) {
		return agentLocations.get(anAgent).getCell();
	}

	/**
	 * Get a percept for an agent, representing what it senses within the cell
	 * world environment.
	 * 
	 * @param anAgent
	 *            the agent a percept is being queried for.
	 * @return a percept for the agent, representing what it senses within the
	 *         cell world environment.
	 */
	public CellWorldPercept getPerceptFor(Agente anAgent) {
		return agentLocations.get(anAgent);
	}
}