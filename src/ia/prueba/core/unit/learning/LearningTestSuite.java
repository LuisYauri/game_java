package ia.prueba.core.unit.learning;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.learning.framework.DataSetTest;
import ia.prueba.core.unit.learning.framework.InformationAndGainTest;
import ia.prueba.core.unit.learning.inductive.DLTestTest;
import ia.prueba.core.unit.learning.inductive.DecisionListTest;
import ia.prueba.core.unit.learning.learners.DecisionTreeTest;
import ia.prueba.core.unit.learning.learners.EnsembleLearningTest;
import ia.prueba.core.unit.learning.learners.LearnerTest;
import ia.prueba.core.unit.learning.neural.BackPropagationTest;
import ia.prueba.core.unit.learning.neural.LayerTest;
import ia.prueba.core.unit.learning.reinforcement.agent.PassiveADPAgentTest;
import ia.prueba.core.unit.learning.reinforcement.agent.PassiveTDAgentTest;
import ia.prueba.core.unit.learning.reinforcement.agent.QLearningAgentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DataSetTest.class, InformationAndGainTest.class,
		DecisionListTest.class, DLTestTest.class, DecisionTreeTest.class,
		EnsembleLearningTest.class, LearnerTest.class,
		BackPropagationTest.class, LayerTest.class,
		PassiveADPAgentTest.class, PassiveTDAgentTest.class,
		QLearningAgentTest.class })
public class LearningTestSuite {

}
