package ia.core.logica.lpo.inferencia.demostracion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ia.core.logica.lpo.bc.datos.Clause;
import ia.core.logica.lpo.analizadorsint.sat.Sentence;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class ProofStepClauseClausifySentence extends AbstractProofStep {
	private List<ProofStep> predecessors = new ArrayList<ProofStep>();
	private Clause clausified = null;

	public ProofStepClauseClausifySentence(Clause clausified,
			Sentence origSentence) {
		this.clausified = clausified;
		this.predecessors.add(new ProofStepPremise(origSentence));
	}

	//
	// START-ProofStep
	@Override
	public List<ProofStep> getPredecessorSteps() {
		return Collections.unmodifiableList(predecessors);
	}

	@Override
	public String getProof() {
		return clausified.toString();
	}

	@Override
	public String getJustification() {
		return "Clausified " + predecessors.get(0).getStepNumber();
	}
	// END-ProofStep
	//
}
