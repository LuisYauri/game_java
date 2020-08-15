package ia.prueba.core.unit.learning.framework;

import java.util.ArrayList;
import java.util.List;

import ia.core.aprendizaje.framework.DataSetSpecification;

/**
 * @author Ravi Mohan
 * 
 */
public class MockDataSetSpecification extends DataSetSpecification {

	public MockDataSetSpecification(String targetAttributeName) {
		setTarget(targetAttributeName);
	}

	@Override
	public List<String> getAttributeNames() {
		return new ArrayList<String>();
	}
}
