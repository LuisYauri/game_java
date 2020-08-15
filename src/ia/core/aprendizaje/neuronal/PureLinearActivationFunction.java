package ia.core.aprendizaje.neuronal;

/**
 * @author Ravi Mohan
 * 
 */
public class PureLinearActivationFunction implements ActivationFunction {

	public double activation(double parameter) {
		return parameter;
	}

	public double deriv(double parameter) {

		return 1;
	}
}
