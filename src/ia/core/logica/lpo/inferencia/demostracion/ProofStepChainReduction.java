package ia.core.logica.lpo.inferencia.demostracion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ia.core.logica.lpo.bc.datos.Chain;
import ia.core.logica.lpo.analizadorsint.sat.Term;
import ia.core.logica.lpo.analizadorsint.sat.Variable;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ProofStepChainReduction extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	private Chain reduction = null;
	private Chain nearParent, farParent = null;
	private Map<Variable, Term> subst = null;

	public ProofStepChainReduction(Chain reduction, Chain nearParent,
			Chain farParent, Map<Variable, Term> subst) {
		this.reduction = reduction;
		this.nearParent = nearParent;
		this.farParent = farParent;
		this.subst = subst;
		this.predecessors.add(farParent.getProofStep());
		this.predecessors.add(nearParent.getProofStep());
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		return reduction.toString();
	}

	@Override
	public String getJustification() {
		return "Reduction: " + nearParent.getProofStep().getStepNumber() + ","
				+ farParent.getProofStep().getStepNumber() + " " + subst;
	}
	// END-ProofStep
	//
}
