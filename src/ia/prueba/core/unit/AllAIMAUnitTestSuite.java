package ia.prueba.core.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.agent.AgentTestSuite;
import ia.prueba.core.unit.environment.EnvironmentTestSuite;
import ia.prueba.core.unit.learning.LearningTestSuite;
import ia.prueba.core.unit.logic.LogicTestSuite;
import ia.prueba.core.unit.probability.ProbabilityTestSuite;
import ia.prueba.core.unit.search.SearchTestSuite;
import ia.prueba.core.unit.util.UtilTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AgentTestSuite.class, EnvironmentTestSuite.class,
		LearningTestSuite.class, LogicTestSuite.class,
		ProbabilityTestSuite.class, SearchTestSuite.class, UtilTestSuite.class })
public class AllAIMAUnitTestSuite {
}
