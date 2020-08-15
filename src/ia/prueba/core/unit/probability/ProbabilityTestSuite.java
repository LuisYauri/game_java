package ia.prueba.core.unit.probability;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.probability.bayes.approx.GibbsAskTest;
import ia.prueba.core.unit.probability.bayes.approx.LikelihoodWeightingTest;
import ia.prueba.core.unit.probability.bayes.approx.ParticleFilterTest;
import ia.prueba.core.unit.probability.bayes.approx.PriorSampleTest;
import ia.prueba.core.unit.probability.bayes.approx.RejectionSamplingTest;
import ia.prueba.core.unit.probability.bayes.exact.EliminationAskTest;
import ia.prueba.core.unit.probability.bayes.exact.EnumerationAskTest;
import ia.prueba.core.unit.probability.bayes.impl.CPTTest;
import ia.prueba.core.unit.probability.bayes.model.FiniteBayesModelTest;
import ia.prueba.core.unit.probability.full.FullJointProbabilityModelTest;
import ia.prueba.core.unit.probability.hmm.exact.FixedLagSmoothingTest;
import ia.prueba.core.unit.probability.hmm.exact.HMMForwardBackwardConstantSpaceTest;
import ia.prueba.core.unit.probability.hmm.exact.HMMForwardBackwardTest;
import ia.prueba.core.unit.probability.mdp.MarkovDecisionProcessTest;
import ia.prueba.core.unit.probability.mdp.PolicyIterationTest;
import ia.prueba.core.unit.probability.mdp.ValueIterationTest;
import ia.prueba.core.unit.probability.temporal.generic.ForwardBackwardTest;
import ia.prueba.core.unit.probability.util.ProbUtilTest;
import ia.prueba.core.unit.probability.util.ProbabilityTableTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( { GibbsAskTest.class, LikelihoodWeightingTest.class,
		ParticleFilterTest.class, PriorSampleTest.class,
		RejectionSamplingTest.class, EliminationAskTest.class,
		EnumerationAskTest.class, CPTTest.class, FiniteBayesModelTest.class,
		FullJointProbabilityModelTest.class, FixedLagSmoothingTest.class,
		HMMForwardBackwardConstantSpaceTest.class,
		HMMForwardBackwardTest.class, MarkovDecisionProcessTest.class,
		PolicyIterationTest.class, ValueIterationTest.class,
		ForwardBackwardTest.class, ProbUtilTest.class,
		ProbabilityTableTest.class })
public class ProbabilityTestSuite {

}
