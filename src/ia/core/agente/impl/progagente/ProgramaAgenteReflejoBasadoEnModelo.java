package ia.core.agente.impl.progagente;

import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.ProgramaAgente;
import ia.core.agente.Modelo;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EstadoDinamico;
import ia.core.agente.impl.AccionNoOp;
import ia.core.agente.impl.progagente.reglasimple.Regla;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.12, page
 * 51.<br>
 * <br>
 * 
 * <pre>
 * function MODEL-BASED-REFLEX-AGENT(percept) returns an action
 *   persistent: state, the agent's current conception of the world state
 *               model, a description of how the next state depends on current state and action
 *               rules, a set of condition-action rules
 *               action, the most recent action, initially none
 *               
 *   state  <- UPDATE-STATE(state, action, percept, model)
 *   rule   <- RULE-MATCH(state, rules)
 *   action <- rule.ACTION
 *   return action
 * </pre>
 * 
 * Figure 2.12 A model-based reflex agent. It keeps track of the current state
 * of the world using an internal model. It then chooses an action in the same
 * way as the reflex agent.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * 
 */
public abstract class ProgramaAgenteReflejoBasadoEnModelo implements ProgramaAgente {
	//
	// persistent: state, the agent's current conception of the world state
	private EstadoDinamico state = null;

	// model, a description of how the next state depends on current state and
	// action
	private Modelo model = null;

	// rules, a set of condition-action rules
	private Set<Regla> rules = null;

	// action, the most recent action, initially none
	private Accion action = null;

	public ProgramaAgenteReflejoBasadoEnModelo() {
		init();
	}

	/**
	 * Set the agent's current conception of the world state.
	 * 
	 * @param state
	 *            the agent's current conception of the world state.
	 */
	public void setState(EstadoDinamico state) {
		this.state = state;
	}

	/**
	 * Set the program's description of how the next state depends on the state
	 * and action.
	 * 
	 * @param model
	 *            a description of how the next state depends on the current
	 *            state and action.
	 */
	public void setModel(Modelo model) {
		this.model = model;
	}

	/**
	 * Set the program's condition-action rules
	 * 
	 * @param ruleSet
	 *            a set of condition-action rules
	 */
	public void setRules(Set<Regla> ruleSet) {
		rules = ruleSet;
	}

	//
	// START-ProgramaAgente

	// function MODEL-BASED-REFLEX-AGENT(percept) returns an action
	public Accion ejecutar(Percepcion percept) {
		// state <- UPDATE-STATE(state, action, percept, model)
		state = updateState(state, action, percept, model);
		// rule <- RULE-MATCH(state, rules)
		Regla rule = ruleMatch(state, rules);
		// action <- rule.ACTION
		action = ruleAction(rule);
		// return action
		return action;
	}

	// END-ProgramaAgente
	//

	//
	// PROTECTED METHODS
	//

	/**
	 * Realizations of this class should implement the init() method so that it
	 * calls the setState(), setModel(), and setRules() method.
	 */
	protected abstract void init();

	protected abstract EstadoDinamico updateState(EstadoDinamico state,
			Accion action, Percepcion percept, Modelo model);

	protected Regla ruleMatch(EstadoDinamico state, Set<Regla> rules) {
		for (Regla r : rules) {
			if (r.evaluate(state)) {
				return r;
			}
		}
		return null;
	}

	protected Accion ruleAction(Regla r) {
		return null == r ? AccionNoOp.NO_OP : r.getAction();
	}
}
