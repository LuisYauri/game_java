package ia.core.agente.impl.progagente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ia.core.agente.Accion;
import ia.core.agente.ProgramaAgente;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.AccionNoOp;
import ia.core.util.estructuradedatos.Table;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.7, page 47.<br>
 * <br>
 * 
 * <pre>
 * function TABLE-DRIVEN-AGENT(percept) returns an action
 *   persistent: percepts, a sequence, initially empty
 *               table, a table of actions, indexed by percept sequences, initially fully specified
 *           
 *   append percept to end of percepts
 *   action <- LOOKUP(percepts, table)
 *   return action
 * </pre>
 * 
 * Figure 2.7 The TABLE-DRIVEN-AGENT program is invoked for each new percept and
 * returns an action each time. It retains the complete percept sequence in
 * memory.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * 
 */
public class ProgramaAgenteDirigidoPorTabla implements ProgramaAgente {
	private List<Percepcion> percepts = new ArrayList<Percepcion>();

	private Table<List<Percepcion>, String, Accion> table;

	private static final String ACTION = "action";

	// persistent: percepts, a sequence, initially empty
	// table, a table of actions, indexed by percept sequences, initially fully
	// specified
	/**
	 * Constructs a ProgramaAgenteDirigidoPorTabla with a table of actions, indexed by
	 * percept sequences.
	 * 
	 * @param perceptSequenceActions
	 *            a table of actions, indexed by percept sequences
	 */
	public ProgramaAgenteDirigidoPorTabla(
			Map<List<Percepcion>, Accion> perceptSequenceActions) {

		List<List<Percepcion>> rowHeaders = new ArrayList<List<Percepcion>>(
				perceptSequenceActions.keySet());

		List<String> colHeaders = new ArrayList<String>();
		colHeaders.add(ACTION);

		table = new Table<List<Percepcion>, String, Accion>(rowHeaders, colHeaders);

		for (List<Percepcion> row : rowHeaders) {
			table.set(row, ACTION, perceptSequenceActions.get(row));
		}
	}

	//
	// START-ProgramaAgente

	// function TABLE-DRIVEN-AGENT(percept) returns an action
	public Accion ejecutar(Percepcion percept) {
		// append percept to end of percepts
		percepts.add(percept);

		// action <- LOOKUP(percepts, table)
		// return action
		return lookupCurrentAction();
	}

	// END-ProgramaAgente
	//

	//
	// PRIVATE METHODS
	//
	private Accion lookupCurrentAction() {
		Accion action = null;

		action = table.get(percepts, ACTION);
		if (null == action) {
			action = AccionNoOp.NO_OP;
		}

		return action;
	}
}
