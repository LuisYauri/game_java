package ia.prueba.core.unit.logic.propositional.parsing;

import org.junit.Test;

import ia.core.logica.proposicional.analizadorsint.sat.ComplexSentence;
import ia.core.logica.proposicional.analizadorsint.sat.Connective;
import ia.core.logica.proposicional.analizadorsint.sat.PropositionSymbol;
import ia.core.logica.proposicional.analizadorsint.sat.Sentence;

public class ComplexSentenceTest {
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_1() {
		new ComplexSentence(null, new Sentence[] {new PropositionSymbol("A"), new PropositionSymbol("B")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_2() {
		new ComplexSentence(Connective.NOT, (Sentence[]) null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_3() {
		new ComplexSentence(Connective.NOT, new Sentence[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_4() {
		new ComplexSentence(Connective.NOT, new Sentence[] {new PropositionSymbol("A"), new PropositionSymbol("B")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_5() {
		new ComplexSentence(Connective.AND, new Sentence[]{new PropositionSymbol("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_6() {
		new ComplexSentence(Connective.AND, new Sentence[]{new PropositionSymbol("A"), new PropositionSymbol("B"), new PropositionSymbol("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_7() {
		new ComplexSentence(Connective.OR, new Sentence[]{new PropositionSymbol("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_8() {
		new ComplexSentence(Connective.OR, new Sentence[]{new PropositionSymbol("A"), new PropositionSymbol("B"), new PropositionSymbol("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_9() {
		new ComplexSentence(Connective.IMPLICATION, new Sentence[]{new PropositionSymbol("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_10() {
		new ComplexSentence(Connective.IMPLICATION, new Sentence[]{new PropositionSymbol("A"), new PropositionSymbol("B"), new PropositionSymbol("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_11() {
		new ComplexSentence(Connective.BICONDITIONAL, new Sentence[]{new PropositionSymbol("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_12() {
		new ComplexSentence(Connective.BICONDITIONAL, new Sentence[]{new PropositionSymbol("A"), new PropositionSymbol("B"), new PropositionSymbol("C")});
	}
}