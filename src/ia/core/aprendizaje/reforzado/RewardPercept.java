package ia.core.aprendizaje.reforzado;

import ia.core.agente.Percepcion;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 830.<br>
 * <br>
 * Our framework for agents regards the reward as part of the input percept, but
 * the agent must be "hardwired" to recognize that part as a reward rather than
 * as just another sensory input.
 * 
 * @author Ciaran O'Reilly
 * @author Ravi Mohan
 */
public interface RewardPercept extends Percepcion {
	/**
	 * 
	 * @return the reward part of the percept ('hardwired').
	 */
	double reward();
}
