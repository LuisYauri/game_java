package ia.core.logica.lpo.inferencia.demostracion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ia.core.logica.lpo.bc.datos.Clause;
import ia.core.logica.lpo.bc.datos.Literal;
import ia.core.logica.lpo.analizadorsint.sat.Term;
import ia.core.logica.lpo.analizadorsint.sat.Variable;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ProofStepClauseFactor extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	private Clause factor = null;
	private Clause factorOf = null;
	private Literal lx = null;
	private Literal ly = null;
	private Map<Variable, Term> subst = new LinkedHashMap<Variable, Term>();
	private Map<Variable, Term> renameSubst = new LinkedHashMap<Variable, Term>();

	public ProofStepClauseFactor(Clause factor, Clause factorOf, Literal lx,
			Literal ly, Map<Variable, Term> subst,
			Map<Variable, Term> renameSubst) {
		this.factor = factor;
		this.factorOf = factorOf;
		this.lx = lx;
		this.ly = ly;
		this.subst.putAll(subst);
		this.renameSubst.putAll(renameSubst);
		this.predecessors.add(factorOf.getProofStep());
	}

	//
	// START-ProofStep
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(predecessors);
	}

	public String getProof() {
		return factor.toString();
	}

	public String getJustification() {
		return "Factor of " + factorOf.getProofStep().getStepNumber() + "  ["
				+ lx + ", " + ly + "], subst=" + subst + ", renaming="
				+ renameSubst;
	}
	// END-ProofStep
	//
}
