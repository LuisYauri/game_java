package ia.core.entorno.aspiradora;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.busqueda.framework.FuncionAcciones;

/**
 * Specifies the actions available to the agent at state s
 * 
 * @author Andrew Brown
 */
public class VacuumWorldActions implements FuncionAcciones {

	private static final Set<Accion> _actions;
	static {
		Set<Accion> actions = new HashSet<Accion>();
		actions.add(VacuumEnvironment.ACTION_SUCK);
		actions.add(VacuumEnvironment.ACTION_MOVE_LEFT);
		actions.add(VacuumEnvironment.ACTION_MOVE_RIGHT);
		// Ensure cannot be modified.
		_actions = Collections.unmodifiableSet(actions);
	}

	/**
	 * Returns possible actions given this state
	 * 
	 * @param s
	 * @return possible actions given this state.
	 */
	@Override
	public Set<Accion> actions(Object s) {
		return _actions;
	}
}
