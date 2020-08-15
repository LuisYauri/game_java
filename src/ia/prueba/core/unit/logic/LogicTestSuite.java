package ia.prueba.core.unit.logic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.logic.fol.FOLTestSuite;
import ia.prueba.core.unit.logic.propositional.PropositionalTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ FOLTestSuite.class, PropositionalTestSuite.class })
public class LogicTestSuite {

}
