package ia.prueba.core.unit.learning.inductive;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.aprendizaje.framework.DataSet;
import ia.core.aprendizaje.framework.DataSetFactory;
import ia.core.aprendizaje.framework.Example;
import ia.core.aprendizaje.inductivo.DLTest;
import ia.core.aprendizaje.inductivo.DLTestFactory;

/**
 * @author Ravi Mohan
 * 
 */
public class DLTestTest {

	@Test
	public void testDecisionList() throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		List<DLTest> dlTests = new DLTestFactory()
				.createDLTestsWithAttributeCount(ds, 1);
		Assert.assertEquals(26, dlTests.size());
	}

	@Test
	public void testDLTestMatchSucceedsWithMatchedExample() throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		Example e = ds.getExample(0);
		DLTest test = new DLTest();
		test.add("type", "French");
		Assert.assertTrue(test.matches(e));
	}

	@Test
	public void testDLTestMatchFailsOnMismatchedExample() throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		Example e = ds.getExample(0);
		DLTest test = new DLTest();
		test.add("type", "Thai");
		Assert.assertFalse(test.matches(e));
	}

	@Test
	public void testDLTestMatchesEvenOnMismatchedTargetAttributeValue()
			throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		Example e = ds.getExample(0);
		DLTest test = new DLTest();
		test.add("type", "French");
		Assert.assertTrue(test.matches(e));
	}

	@Test
	public void testDLTestReturnsMatchedAndUnmatchedExamplesCorrectly()
			throws Exception {
		DataSet ds = DataSetFactory.getRestaurantDataSet();
		DLTest test = new DLTest();
		test.add("type", "Burger");

		DataSet matched = test.matchedExamples(ds);
		Assert.assertEquals(4, matched.size());

		DataSet unmatched = test.unmatchedExamples(ds);
		Assert.assertEquals(8, unmatched.size());
	}
}
