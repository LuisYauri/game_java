package ia.prueba.core.unit.logic.fol;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.logica.lpo.PredicateCollector;
import ia.core.logica.lpo.dominio.DomainFactory;
import ia.core.logica.lpo.analizadorsint.FOLParser;
import ia.core.logica.lpo.analizadorsint.sat.Predicate;
import ia.core.logica.lpo.analizadorsint.sat.Sentence;

/**
 * @author Ravi Mohan
 * 
 */
public class PredicateCollectorTest {
	PredicateCollector collector;

	FOLParser parser;

	@Before
	public void setUp() {
		collector = new PredicateCollector();
		parser = new FOLParser(DomainFactory.weaponsDomain());
	}

	@Test
	public void testSimpleSentence() {
		Sentence s = parser.parse("(Missile(x) => Weapon(x))");
		List<Predicate> predicates = collector.getPredicates(s);
		Assert.assertNotNull(predicates);
	}
}
