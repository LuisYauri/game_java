package ia.core.logica.lpo.analizadorsint.sat;

import java.util.List;

import ia.core.logica.comun.ParseTreeNode;
import ia.core.logica.lpo.analizadorsint.FOLVisitor;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public interface FOLNode extends ParseTreeNode {
	String getSymbolicName();

	boolean isCompound();

	List<? extends FOLNode> getArgs();

	Object accept(FOLVisitor v, Object arg);

	FOLNode copy();
}
