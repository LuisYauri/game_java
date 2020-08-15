package ia.core.logica.lpo.inferencia.rastro;

/**
 * @author Ciaran O'Reilly
 * 
 */
public interface FOLModelEliminationTracer {
	void reset();

	void increment(int depth, int noFarParents);
}
