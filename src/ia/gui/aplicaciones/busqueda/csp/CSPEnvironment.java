package ia.gui.aplicaciones.busqueda.csp;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EntornoAbstracto;
import ia.core.busqueda.csp.Assignment;
import ia.core.busqueda.csp.CSP;

/**
 * Simple environment which maintains a CSP and an assignment. The state
 * is modified by executing {@link StateChangeAction}s.
 * @author Ruediger Lunde
 */
public class CSPEnvironment extends EntornoAbstracto {
	CSP csp;
	Assignment assignment;

	public void init(CSP csp) {
		this.csp = csp;
		assignment = null;
	}
	
	public CSP getCSP() {
		return csp;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	/** Executes the provided action and returns null. */
	@Override
	public EstadoEntorno executeAction(Agente agent, Accion action) {
		if (action instanceof StateChangeAction) {
			StateChangeAction a = (StateChangeAction) action;
			if (a.updateCSP())
				csp = a.getCSP();
			if (a.updateAssignment())
				assignment = a.getAssignment();
			if (agent == null)
				updateEnvironmentViewsAgentActed(agent, action, null);
		}
		return null;
	}

	/** Returns null. */
	@Override
	public EstadoEntorno getCurrentState() {
		return null;
	}

	/** Returns null. */
	@Override
	public Percepcion getPerceptSeenBy(Agente anAgent) {
		return null;
	}

	/** Action to modify the CSP environment state. */
	public static class StateChangeAction implements Accion {
		private CSP csp;
		private Assignment assignment;
		
		/** Update the domains of the CSP. */
		public StateChangeAction(CSP csp) {
			this.csp = csp;
		}

		/** Update the current assignment. */
		public StateChangeAction(Assignment assignment, CSP csp) {
			this.csp = csp;
			this.assignment = assignment;
		}
		
		public boolean updateCSP() {
			return csp != null;
		}

		public CSP getCSP() {
			return csp;
		}

		public boolean updateAssignment() {
			return assignment != null;
		}

		public Assignment getAssignment() {
			return assignment;
		}
		
		@Override
		public boolean esNoOp() {
			return false;
		}
		
		public String toString() {
			return "State Change "
			+ (updateAssignment() ? assignment : "(Domain Reduction)");
		}
	}
}
