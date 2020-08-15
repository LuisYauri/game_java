package ia.prueba.core.unit.logic.propositional.visitors;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.logica.proposicional.analizadorsint.PLParser;
import ia.core.logica.proposicional.analizadorsint.sat.Sentence;
import ia.core.logica.proposicional.analizadorsint.sat.PropositionSymbol;
import ia.core.logica.proposicional.visitantes.SymbolCollector;

/**
 * @author Ravi Mohan
 * 
 */
public class SymbolCollectorTest {
	private PLParser parser;

	@Before
	public void setUp() {
		parser = new PLParser();
	}

	@Test
	public void testCollectSymbolsFromComplexSentence() {
		Sentence sentence = (Sentence) parser.parse("(~B11 | P12 | P21) & (B11 | ~P12) & (B11 | ~P21)");
		Set<PropositionSymbol> s = SymbolCollector.getSymbolsFrom(sentence);
		Assert.assertEquals(3, s.size());
		Sentence b11 =  parser.parse("B11");
		Sentence p21 =  parser.parse("P21");
		Sentence p12 =  parser.parse("P12");
		Assert.assertTrue(s.contains(b11));
		Assert.assertTrue(s.contains(p21));
		Assert.assertTrue(s.contains(p12));
	}
}