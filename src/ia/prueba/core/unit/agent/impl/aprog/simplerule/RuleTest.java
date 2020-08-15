package ia.prueba.core.unit.agent.impl.aprog.simplerule;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.agente.impl.AccionDinamica;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.agente.impl.progagente.reglasimple.CondicionAND;
import ia.core.agente.impl.progagente.reglasimple.CondicionEQUAL;
import ia.core.agente.impl.progagente.reglasimple.CondicionNOT;
import ia.core.agente.impl.progagente.reglasimple.CondicionOR;
import ia.core.agente.impl.progagente.reglasimple.Regla;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class RuleTest {

	private static final Accion ACTION_INITIATE_BRAKING = new AccionDinamica(
			"initiate-braking");
	private static final Accion ACTION_EMERGENCY_BRAKING = new AccionDinamica(
			"emergency-braking");
	//
	private static final String ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING = "car-in-front-is-braking";
	private static final String ATTRIBUTE_CAR_IN_FRONT_IS_INDICATING = "car-in-front-is-indicating";
	private static final String ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING = "car-in-front-tires-smoking";

	@Test
	public void testEQUALRule() {
		Regla r = new Regla(new CondicionEQUAL(ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING,
				true), ACTION_INITIATE_BRAKING);

		Assert.assertEquals(ACTION_INITIATE_BRAKING, r.getAction());

		Assert.assertEquals(
				"if car-in-front-is-braking==true then Action[name==initiate-braking].",
				r.toString());

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, false)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_INDICATING, true)));
	}

	@Test
	public void testNOTRule() {
		Regla r = new Regla(new CondicionNOT(new CondicionEQUAL(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true)),
				ACTION_INITIATE_BRAKING);

		Assert.assertEquals(ACTION_INITIATE_BRAKING, r.getAction());

		Assert.assertEquals(
				"if ![car-in-front-is-braking==true] then Action[name==initiate-braking].",
				r.toString());

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, false)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_INDICATING, true)));
	}

	@Test
	public void testANDRule() {
		Regla r = new Regla(new CondicionAND(new CondicionEQUAL(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true), new CondicionEQUAL(
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)),
				ACTION_EMERGENCY_BRAKING);

		Assert.assertEquals(ACTION_EMERGENCY_BRAKING, r.getAction());

		Assert.assertEquals(
				"if [car-in-front-is-braking==true && car-in-front-tires-smoking==true] then Action[name==emergency-braking].",
				r.toString());

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, false,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, false)));
	}

	@Test
	public void testORRule() {
		Regla r = new Regla(new CondicionOR(new CondicionEQUAL(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true), new CondicionEQUAL(
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)),
				ACTION_EMERGENCY_BRAKING);

		Assert.assertEquals(ACTION_EMERGENCY_BRAKING, r.getAction());

		Assert.assertEquals(
				"if [car-in-front-is-braking==true || car-in-front-tires-smoking==true] then Action[name==emergency-braking].",
				r.toString());

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, false,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, true)));

		Assert.assertEquals(true, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, true,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, false)));

		Assert.assertEquals(false, r.evaluate(new PercepcionDinamica(
				ATTRIBUTE_CAR_IN_FRONT_IS_BRAKING, false,
				ATTRIBUTE_CAR_IN_FRONT_TIRES_SMOKING, false)));
	}
}