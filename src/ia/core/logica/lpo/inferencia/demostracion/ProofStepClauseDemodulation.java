package ia.core.logica.lpo.inferencia.demostracion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ia.core.logica.lpo.bc.datos.Clause;
import ia.core.logica.lpo.analizadorsint.sat.TermEquality;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ProofStepClauseDemodulation extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	private Clause demodulated = null;
	private Clause origClause = null;
	private TermEquality assertion = null;

	public ProofStepClauseDemodulation(Clause demodulated, Clause origClause,
			TermEquality assertion) {
		this.demodulated = demodulated;
		this.origClause = origClause;
		this.assertion = assertion;
		this.predecessors.add(origClause.getProofStep());
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		return demodulated.toString();
	}

	@Override
	public String getJustification() {
		return "Demodulation: " + origClause.getProofStep().getStepNumber()
				+ ", [" + assertion + "]";
	}
	// END-ProofStep
	//
}
