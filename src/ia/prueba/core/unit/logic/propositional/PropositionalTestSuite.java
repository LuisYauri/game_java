package ia.prueba.core.unit.logic.propositional;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.logic.propositional.inference.DPLLTest;
import ia.prueba.core.unit.logic.propositional.inference.PLFCEntailsTest;
import ia.prueba.core.unit.logic.propositional.inference.PLResolutionTest;
import ia.prueba.core.unit.logic.propositional.inference.TTEntailsTest;
import ia.prueba.core.unit.logic.propositional.kb.KnowledgeBaseTest;
import ia.prueba.core.unit.logic.propositional.kb.data.ClauseTest;
import ia.prueba.core.unit.logic.propositional.kb.data.ConvertToConjunctionOfClausesTest;
import ia.prueba.core.unit.logic.propositional.kb.data.LiteralTest;
import ia.prueba.core.unit.logic.propositional.kb.data.ModelTest;
import ia.prueba.core.unit.logic.propositional.parsing.ComplexSentenceTest;
import ia.prueba.core.unit.logic.propositional.parsing.ListTest;
import ia.prueba.core.unit.logic.propositional.parsing.PLLexerTest;
import ia.prueba.core.unit.logic.propositional.parsing.PLParserTest;
import ia.prueba.core.unit.logic.propositional.parsing.PropositionSymbolTest;
import ia.prueba.core.unit.logic.propositional.visitors.ConvertToCNFTest;
import ia.prueba.core.unit.logic.propositional.visitors.SymbolCollectorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DPLLTest.class, KnowledgeBaseTest.class, ModelTest.class,
		PLFCEntailsTest.class, PLResolutionTest.class, TTEntailsTest.class,
		ComplexSentenceTest.class, ListTest.class, PLLexerTest.class,
		PLParserTest.class, PropositionSymbolTest.class,
		ConvertToCNFTest.class, ClauseTest.class,
		ConvertToConjunctionOfClausesTest.class, LiteralTest.class,
		SymbolCollectorTest.class })
public class PropositionalTestSuite {

}
