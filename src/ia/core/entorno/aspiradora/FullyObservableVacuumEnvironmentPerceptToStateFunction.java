package ia.core.entorno.aspiradora;

import ia.core.agente.Percepcion;
import ia.core.busqueda.framework.FuncionPercepcionAEstado;

/**
 * Map fully observable state percepts to their corresponding state
 * representation.
 * 
 * @author Andrew Brown
 */
public class FullyObservableVacuumEnvironmentPerceptToStateFunction implements
		FuncionPercepcionAEstado {

	/**
	 * Default Constructor.
	 */
	public FullyObservableVacuumEnvironmentPerceptToStateFunction() {

	}

	@Override
	public Object getState(Percepcion p) {
		// Note: VacuumEnvironmentState implements
		// FullyObservableVacuumEnvironmentPercept
		return (VacuumEnvironmentState) p;
	}
}
