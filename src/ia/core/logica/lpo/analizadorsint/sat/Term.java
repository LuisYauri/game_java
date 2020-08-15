package ia.core.logica.lpo.analizadorsint.sat;

import java.util.List;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public interface Term extends FOLNode {
	List<Term> getArgs();

	Term copy();
}
