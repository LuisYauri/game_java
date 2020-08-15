package ia.prueba.core.unit.logic.propositional.inference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.logica.proposicional.inferencia.PLFCEntails;
import ia.core.logica.proposicional.bc.KnowledgeBase;
import ia.core.logica.proposicional.analizadorsint.PLParser;
import ia.core.logica.proposicional.analizadorsint.sat.PropositionSymbol;
/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * 
 */
public class PLFCEntailsTest {
	private PLParser parser;
	private PLFCEntails plfce;

	@Before
	public void setUp() {
		parser = new PLParser();
		plfce = new PLFCEntails();
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
		PropositionSymbol q = (PropositionSymbol) parser.parse("Q");
		
		Assert.assertEquals(true, plfce.plfcEntails(kb, q));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testKBWithNonDefiniteClauses() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.tell("P => Q");
		kb.tell("L & M => P");
		kb.tell("B & L => M");
		kb.tell("~A & P => L"); // Not a definite clause
		kb.tell("A & B => L");
		kb.tell("A");
		kb.tell("B");
		PropositionSymbol q = (PropositionSymbol) parser.parse("Q");
		
		Assert.assertEquals(true, plfce.plfcEntails(kb, q));
	}
}