package ia.core.entorno.aspiradora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.agente.impl.progagente.ProgramaAgenteDirigidoPorTabla;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.3, page 36.<br>
 * <br>
 * Figure 2.3 Partial tabulation of a simple agent function for the
 * vacuum-cleaner world shown in Figure 2.2.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class TableDrivenVacuumAgent extends AgenteAbstracto {

	public TableDrivenVacuumAgent() {
		super(new ProgramaAgenteDirigidoPorTabla(getPerceptSequenceActions()));
	}

	//
	// PRIVATE METHODS
	//
	private static Map<List<Percepcion>, Accion> getPerceptSequenceActions() {
		Map<List<Percepcion>, Accion> perceptSequenceActions = new HashMap<List<Percepcion>, Accion>();

		// NOTE: While this particular table could be setup simply
		// using a few loops, the intent is to show how quickly a table
		// based approach grows and becomes unusable.
		List<Percepcion> ps;
		//
		// Level 1: 4 states
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);

		//
		// Level 2: 4x4 states
		// 1
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 2
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 3
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 4
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);

		//
		// Level 3: 4x4x4 states
		// 1-1
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 1-2
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 1-3
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 1-4
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 2-1
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 2-2
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 2-3
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 2-4
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 3-1
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 3-2
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 3-3
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 3-4
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 4-1
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 4-2
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 4-3
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		// 4-4
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_RIGHT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_A,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Clean));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_MOVE_LEFT);
		ps = createPerceptSequence(new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty), new LocalVacuumEnvironmentPercept(
				VacuumEnvironment.LOCATION_B,
				VacuumEnvironment.LocationState.Dirty));
		perceptSequenceActions.put(ps, VacuumEnvironment.ACTION_SUCK);

		//
		// Level 4: 4x4x4x4 states
		// ...

		return perceptSequenceActions;
	}

	private static List<Percepcion> createPerceptSequence(Percepcion... percepts) {
		List<Percepcion> perceptSequence = new ArrayList<Percepcion>();

		for (Percepcion p : percepts) {
			perceptSequence.add(p);
		}

		return perceptSequence;
	}
}
