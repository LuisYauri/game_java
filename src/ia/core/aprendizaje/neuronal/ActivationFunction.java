package ia.core.aprendizaje.neuronal;

/**
 * @author Ravi Mohan
 * 
 */
public interface ActivationFunction {
	double activation(double parameter);

	double deriv(double parameter);
}
