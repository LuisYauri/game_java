package ia.core.entorno.aspiradora;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.VistaEntorno;

public class VacuumEnvironmentViewActionTracker implements VistaEntorno {
	private StringBuilder actions = null;

	public VacuumEnvironmentViewActionTracker(StringBuilder envChanges) {
		this.actions = envChanges;
	}

	//
	// START-VistaEntorno
	public void notify(String msg) {
		// Do nothing by default.
	}

	public void agentAdded(Agente agent, EstadoEntorno state) {
		// Do nothing by default.
	}

	public void agentActed(Agente agent, Accion action, EstadoEntorno state) {
		actions.append(action);
	}

	// END-VistaEntorno
	//
}
