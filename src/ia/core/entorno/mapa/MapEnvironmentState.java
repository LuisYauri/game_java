package ia.core.entorno.mapa;

import java.util.HashMap;

import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.util.estructuradedatos.Pair;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class MapEnvironmentState implements EstadoEntorno {
	private java.util.Map<Agente, Pair<String, Double>> agentLocationAndTravelDistance = new HashMap<Agente, Pair<String, Double>>();

	public MapEnvironmentState() {

	}

	public String getAgentLocation(Agente a) {
		Pair<String, Double> locAndTDistance = agentLocationAndTravelDistance
				.get(a);
		if (null == locAndTDistance) {
			return null;
		}
		return locAndTDistance.getFirst();
	}

	public Double getAgentTravelDistance(Agente a) {
		Pair<String, Double> locAndTDistance = agentLocationAndTravelDistance
				.get(a);
		if (null == locAndTDistance) {
			return null;
		}
		return locAndTDistance.getSecond();
	}

	public void setAgentLocationAndTravelDistance(Agente a, String location,
			Double travelDistance) {
		agentLocationAndTravelDistance.put(a, new Pair<String, Double>(
				location, travelDistance));
	}
}
