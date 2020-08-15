package ia.core.entorno.aspiradora;

import java.util.LinkedHashSet;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.Modelo;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.EstadoDinamico;
import ia.core.agente.impl.AccionNoOp;
import ia.core.agente.impl.progagente.ProgramaAgenteReflejoBasadoEnModelo;
import ia.core.agente.impl.progagente.reglasimple.CondicionAND;
import ia.core.agente.impl.progagente.reglasimple.CondicionEQUAL;
import ia.core.agente.impl.progagente.reglasimple.Regla;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ModelBasedReflexVacuumAgent extends AgenteAbstracto {

	private static final String ATTRIBUTE_CURRENT_LOCATION = "currentLocation";
	private static final String ATTRIBUTE_CURRENT_STATE = "currentState";
	private static final String ATTRIBUTE_STATE_LOCATION_A = "stateLocationA";
	private static final String ATTRIBUTE_STATE_LOCATION_B = "stateLocationB";

	public ModelBasedReflexVacuumAgent() {
		super(new ProgramaAgenteReflejoBasadoEnModelo() {
			@Override
			protected void init() {
				setState(new EstadoDinamico());
				setRules(getRuleSet());
			}

			protected EstadoDinamico updateState(EstadoDinamico state,
					Accion anAction, Percepcion percept, Modelo model) {

				LocalVacuumEnvironmentPercept vep = (LocalVacuumEnvironmentPercept) percept;

				state.setAttribute(ATTRIBUTE_CURRENT_LOCATION,
						vep.getAgentLocation());
				state.setAttribute(ATTRIBUTE_CURRENT_STATE,
						vep.getLocationState());
				// Keep track of the state of the different locations
				if (VacuumEnvironment.LOCATION_A == vep.getAgentLocation()) {
					state.setAttribute(ATTRIBUTE_STATE_LOCATION_A,
							vep.getLocationState());
				} else {
					state.setAttribute(ATTRIBUTE_STATE_LOCATION_B,
							vep.getLocationState());
				}
				return state;
			}
		});
	}

	//
	// PRIVATE METHODS
	//
	private static Set<Regla> getRuleSet() {
		// Note: Using a LinkedHashSet so that the iteration order (i.e. implied
		// precedence) of rules can be guaranteed.
		Set<Regla> rules = new LinkedHashSet<Regla>();

		rules.add(new Regla(new CondicionAND(new CondicionEQUAL(
				ATTRIBUTE_STATE_LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new CondicionEQUAL(
				ATTRIBUTE_STATE_LOCATION_B,
				VacuumEnvironment.LocationState.Clean)), AccionNoOp.NO_OP));
		rules.add(new Regla(new CondicionEQUAL(ATTRIBUTE_CURRENT_STATE,
				VacuumEnvironment.LocationState.Dirty),
				VacuumEnvironment.ACTION_SUCK));
		rules.add(new Regla(new CondicionEQUAL(ATTRIBUTE_CURRENT_LOCATION,
				VacuumEnvironment.LOCATION_A),
				VacuumEnvironment.ACTION_MOVE_RIGHT));
		rules.add(new Regla(new CondicionEQUAL(ATTRIBUTE_CURRENT_LOCATION,
				VacuumEnvironment.LOCATION_B),
				VacuumEnvironment.ACTION_MOVE_LEFT));

		return rules;
	}
}
