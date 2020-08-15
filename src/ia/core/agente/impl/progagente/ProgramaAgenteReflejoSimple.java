package ia.core.agente.impl.progagente;

import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.ProgramaAgente;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.agente.impl.AccionNoOp;
import ia.core.agente.impl.ObjetoConAtributosDinamicos;
import ia.core.agente.impl.progagente.reglasimple.Regla;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 2.10, page
 * 49.<br>
 * <br>
 * 
 * <pre>
 * function SIMPLE-RELEX-AGENT(percept) returns an action
 *   persistent: rules, a set of condition-action rules
 *   
 *   state  <- INTERPRET-INPUT(percept);
 *   rule   <- RULE-MATCH(state, rules);
 *   action <- rule.ACTION;
 *   return action
 * </pre>
 * 
 * Figure 2.10 A simple reflex agent. It acts according to a rule whose
 * condition matches the current state, as defined by the percept.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * 
 */
public class ProgramaAgenteReflejoSimple implements ProgramaAgente {
	//
	// persistent: rules, a set of condition-action rules
	private Set<Regla> rules;

	/**
	 * Constructs a ProgramaAgenteReflejoSimple with a set of condition-action
	 * rules.
	 * 
	 * @param ruleSet
	 *            a set of condition-action rules
	 */
	public ProgramaAgenteReflejoSimple(Set<Regla> ruleSet) {
		rules = ruleSet;
	}

	//
	// START-ProgramaAgente

	// function SIMPLE-RELEX-AGENT(percept) returns an action
	@Override
	public Accion ejecutar(Percepcion percept) {

		// state <- INTERPRET-INPUT(percept);
		ObjetoConAtributosDinamicos state = interpretInput(percept);
		// rule <- RULE-MATCH(state, rules);
		Regla rule = ruleMatch(state, rules);
		// action <- rule.ACTION;
		// return action
		return ruleAction(rule);
	}

	// END-ProgramaAgente
	//

	//
	// PROTECTED METHODS
	//
	protected ObjetoConAtributosDinamicos interpretInput(Percepcion p) {
		return (PercepcionDinamica) p;
	}

	protected Regla ruleMatch(ObjetoConAtributosDinamicos state,
			Set<Regla> rulesSet) {
		for (Regla r : rulesSet) {
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
