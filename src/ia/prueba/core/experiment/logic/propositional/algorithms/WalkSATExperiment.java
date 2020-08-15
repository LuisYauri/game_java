package ia.prueba.core.experiment.logic.propositional.algorithms;

import org.junit.Test;

import ia.core.logica.proposicional.inferencia.WalkSAT;
import ia.core.logica.proposicional.bc.KnowledgeBase;
import ia.core.logica.proposicional.bc.datos.Model;
import ia.core.logica.proposicional.analizadorsint.PLParser;
import ia.core.logica.proposicional.visitantes.ConvertToConjunctionOfClauses;

/**
 * @author Ravi Mohan
 * 
 */
public class WalkSATExperiment {
	
	private PLParser parser = new PLParser();

	// NOT REALLY A JUNIT TESTCASE BUT written as one to allow easy execution
	@Test
	public void testWalkSat() {
		WalkSAT walkSAT = new WalkSAT();
		Model m = walkSAT.walkSAT(ConvertToConjunctionOfClauses.convert(parser.parse("A & B"))
				.getClauses(), 0.5, 1000);
		if (m == null) {
			System.out.println("failure");
		} else {
			m.print();
		}
	}

	@Test
	public void testWalkSat2() {
		WalkSAT walkSAT = new WalkSAT();
		Model m = walkSAT.walkSAT(ConvertToConjunctionOfClauses.convert(parser.parse("A & ~B"))
				.getClauses(), 0.5, 1000);
		if (m == null) {
			System.out.println("failure");
		} else {
			m.print();
		}
	}

	@Test
	public void testAIMAExample() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.tell("P => Q");
		kb.tell("L & M => P");
		kb.tell("B & L => M");
		kb.tell("A & P => L");
		kb.tell("A & B => L");
		kb.tell("A");
		kb.tell("B");
		WalkSAT walkSAT = new WalkSAT();
		Model m = walkSAT.walkSAT(ConvertToConjunctionOfClauses.convert(kb.asSentence())
				.getClauses(), 0.5, 1000);
		if (m == null) {
			System.out.println("failure");
		} else {
			m.print();
		}
	}
}
