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
public class ProofStepChainCancellation extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	private Chain cancellation = null;
	private Chain cancellationOf = null;
	private Map<Variable, Term> subst = null;

	public ProofStepChainCancellation(Chain cancellation, Chain cancellationOf,
			Map<Variable, Term> subst) {
		this.cancellation = cancellation;
		this.cancellationOf = cancellationOf;
		this.subst = subst;
		this.predecessors.add(cancellationOf.getProofStep());
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		return cancellation.toString();
	}

	@Override
	public String getJustification() {
		return "Cancellation: " + cancellationOf.getProofStep().getStepNumber()
				+ " " + subst;
	}
	// END-ProofStep
	//
}
