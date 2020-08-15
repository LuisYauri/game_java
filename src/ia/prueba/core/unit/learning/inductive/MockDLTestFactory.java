package ia.prueba.core.unit.learning.inductive;

import java.util.List;

import ia.core.aprendizaje.framework.DataSet;
import ia.core.aprendizaje.inductivo.DLTest;
import ia.core.aprendizaje.inductivo.DLTestFactory;

/**
 * @author Ravi Mohan
 * 
 */
public class MockDLTestFactory extends DLTestFactory {

	private List<DLTest> tests;

	public MockDLTestFactory(List<DLTest> tests) {
		this.tests = tests;
	}

	@Override
	public List<DLTest> createDLTestsWithAttributeCount(DataSet ds, int i) {
		return tests;
	}
}
