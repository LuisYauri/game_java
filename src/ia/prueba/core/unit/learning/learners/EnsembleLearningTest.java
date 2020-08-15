package ia.prueba.core.unit.learning.learners;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.aprendizaje.framework.DataSet;
import ia.core.aprendizaje.framework.DataSetFactory;
import ia.core.aprendizaje.framework.Learner;
import ia.core.aprendizaje.inductivo.DecisionTree;
import ia.core.aprendizaje.aprendices.AdaBoostLearner;
import ia.core.aprendizaje.aprendices.StumpLearner;

/**
 * @author Ravi Mohan
 * 
 */
public class EnsembleLearningTest {

	private static final String YES = "Yes";

	@Test
	public void testAdaBoostEnablesCollectionOfStumpsToClassifyDataSetAccurately()
			throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		List<DecisionTree> stumps = DecisionTree.getStumpsFor(ds, YES, "No");
		List<Learner> learners = new ArrayList<Learner>();
		for (Object stump : stumps) {
			DecisionTree sl = (DecisionTree) stump;
			StumpLearner stumpLearner = new StumpLearner(sl, "No");
			learners.add(stumpLearner);
		}
		AdaBoostLearner learner = new AdaBoostLearner(learners, ds);
		learner.train(ds);
		int[] result = learner.test(ds);
		Assert.assertEquals(12, result[0]);
		Assert.assertEquals(0, result[1]);
	}
}
