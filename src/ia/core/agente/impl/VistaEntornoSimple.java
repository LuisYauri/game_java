package ia.core.agente.impl;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.VistaEntorno;

/**
 * Simple environment view which uses the standard output stream to inform about
 * relevant events.
 * 
 * @author Ruediger Lunde
 */
public class VistaEntornoSimple implements VistaEntorno {
	@Override
	public void agentActed(Agente agent, Accion action,
			EstadoEntorno resultingState) {
		System.out.println("Agent acted: " + action.toString());
	}

	@Override
	public void agentAdded(Agente agent, EstadoEntorno resultingState) {
		System.out.println("Agent added.");
	}

	@Override
	public void notify(String msg) {
		System.out.println("Message: " + msg);
	}
}
