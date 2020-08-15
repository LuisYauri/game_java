package ia.core.aprendizaje.aprendices;

import ia.core.aprendizaje.framework.DataSet;
import ia.core.aprendizaje.inductivo.DecisionTree;

/**
 * @author Ravi Mohan
 * 
 */
public class StumpLearner extends DecisionTreeLearner {

	public StumpLearner(DecisionTree sl, String unable_to_classify) {
		super(sl, unable_to_classify);
	}

	@Override
	public void train(DataSet ds) {
		// System.out.println("Stump learner training");
		// do nothing the stump is not inferred from the dataset
	}
}
