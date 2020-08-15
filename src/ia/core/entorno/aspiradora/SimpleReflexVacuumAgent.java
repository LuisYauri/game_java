package ia.core.entorno.aspiradora;

import java.util.LinkedHashSet;
import java.util.Set;

import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.progagente.ProgramaAgenteReflejoSimple;
import ia.core.agente.impl.progagente.reglasimple.CondicionEQUAL;
import ia.core.agente.impl.progagente.reglasimple.Regla;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class SimpleReflexVacuumAgent extends AgenteAbstracto {

	public SimpleReflexVacuumAgent() {
		super(new ProgramaAgenteReflejoSimple(getRuleSet()));
	}

	//
	// PRIVATE METHODS
	//
	private static Set<Regla> getRuleSet() {
		// Note: Using a LinkedHashSet so that the iteration order (i.e. implied
		// precedence) of rules can be guaranteed.
		Set<Regla> rules = new LinkedHashSet<Regla>();

		// Rules based on REFLEX-VACUUM-AGENT:
		// Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.8,
		// page 48.

		rules.add(new Regla(new CondicionEQUAL(LocalVacuumEnvironmentPercept.ATTRIBUTE_STATE,
				VacuumEnvironment.LocationState.Dirty),
				VacuumEnvironment.ACTION_SUCK));
		rules.add(new Regla(new CondicionEQUAL(
				LocalVacuumEnvironmentPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment.LOCATION_A),
				VacuumEnvironment.ACTION_MOVE_RIGHT));
		rules.add(new Regla(new CondicionEQUAL(
				LocalVacuumEnvironmentPercept.ATTRIBUTE_AGENT_LOCATION,
				VacuumEnvironment.LOCATION_B),
				VacuumEnvironment.ACTION_MOVE_LEFT));

		return rules;
	}
}
