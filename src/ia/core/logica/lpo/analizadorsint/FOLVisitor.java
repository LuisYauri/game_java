package ia.core.logica.lpo.analizadorsint;

import ia.core.logica.lpo.analizadorsint.sat.ConnectedSentence;
import ia.core.logica.lpo.analizadorsint.sat.Constant;
import ia.core.logica.lpo.analizadorsint.sat.Function;
import ia.core.logica.lpo.analizadorsint.sat.NotSentence;
import ia.core.logica.lpo.analizadorsint.sat.Predicate;
import ia.core.logica.lpo.analizadorsint.sat.QuantifiedSentence;
import ia.core.logica.lpo.analizadorsint.sat.TermEquality;
import ia.core.logica.lpo.analizadorsint.sat.Variable;

/**
 * @author Ravi Mohan
 * 
 */
public interface FOLVisitor {
	public Object visitPredicate(Predicate p, Object arg);

	public Object visitTermEquality(TermEquality equality, Object arg);

	public Object visitVariable(Variable variable, Object arg);

	public Object visitConstant(Constant constant, Object arg);

	public Object visitFunction(Function function, Object arg);

	public Object visitNotSentence(NotSentence sentence, Object arg);

	public Object visitConnectedSentence(ConnectedSentence sentence, Object arg);

	public Object visitQuantifiedSentence(QuantifiedSentence sentence,
			Object arg);
}
