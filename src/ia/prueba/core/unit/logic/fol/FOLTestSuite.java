package ia.prueba.core.unit.logic.fol;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.logic.fol.inference.DemodulationTest;
import ia.prueba.core.unit.logic.fol.inference.FOLBCAskTest;
import ia.prueba.core.unit.logic.fol.inference.FOLFCAskTest;
import ia.prueba.core.unit.logic.fol.inference.FOLModelEliminationTest;
import ia.prueba.core.unit.logic.fol.inference.FOLOTTERLikeTheoremProverTest;
import ia.prueba.core.unit.logic.fol.inference.FOLTFMResolutionTest;
import ia.prueba.core.unit.logic.fol.inference.ParamodulationTest;
import ia.prueba.core.unit.logic.fol.kb.FOLKnowledgeBaseTest;
import ia.prueba.core.unit.logic.fol.kb.data.ChainTest;
import ia.prueba.core.unit.logic.fol.kb.data.ClauseTest;
import ia.prueba.core.unit.logic.fol.parsing.FOLLexerTest;
import ia.prueba.core.unit.logic.fol.parsing.FOLParserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DemodulationTest.class, FOLBCAskTest.class,
		FOLFCAskTest.class, FOLModelEliminationTest.class,
		FOLOTTERLikeTheoremProverTest.class, FOLTFMResolutionTest.class,
		ParamodulationTest.class, ChainTest.class, ClauseTest.class,
		FOLKnowledgeBaseTest.class, FOLLexerTest.class, FOLParserTest.class,
		CNFConverterTest.class, PredicateCollectorTest.class,
		SubstVisitorTest.class, SubsumptionEliminationTest.class,
		UnifierTest.class, VariableCollectorTest.class })
public class FOLTestSuite {

}
