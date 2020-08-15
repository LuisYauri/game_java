package ia.prueba.core.unit.environment.vacuum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.entorno.aspiradora.VacuumEnvironmentViewActionTracker;
import ia.core.entorno.aspiradora.ModelBasedReflexVacuumAgent;
import ia.core.entorno.aspiradora.VacuumEnvironment;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * 
 */
public class ModelBasedReflexVacuumAgentTest {
	private ModelBasedReflexVacuumAgent agent;

	private StringBuilder envChanges;

	@Before
	public void setUp() {
		agent = new ModelBasedReflexVacuumAgent();
		envChanges = new StringBuilder();
	}

	@Test
	public void testCleanClean() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		tve.addEnvironmentView(new VacuumEnvironmentViewActionTracker(envChanges));

		tve.pasoHastaTerminar();

		Assert.assertEquals("Action[name==Right]Action[name==NoOp]",
				envChanges.toString());
	}

	@Test
	public void testCleanDirty() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		tve.addEnvironmentView(new VacuumEnvironmentViewActionTracker(envChanges));

		tve.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==Right]Action[name==Suck]Action[name==NoOp]",
				envChanges.toString());
	}

	@Test
	public void testDirtyClean() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		tve.addEnvironmentView(new VacuumEnvironmentViewActionTracker(envChanges));

		tve.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==Suck]Action[name==Right]Action[name==NoOp]",
				envChanges.toString());
	}

	@Test
	public void testDirtyDirty() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		tve.addEnvironmentView(new VacuumEnvironmentViewActionTracker(envChanges));

		tve.pasoHastaTerminar();

		Assert.assertEquals(
				"Action[name==Suck]Action[name==Right]Action[name==Suck]Action[name==NoOp]",
				envChanges.toString());
	}

	@Test
	public void testAgentActionNumber1() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // cleans location A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // moves to lcation B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Dirty,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // cleans location B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(19, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber2() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_B);

		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // cleans location B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // moves to lcation A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Dirty,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // cleans location A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(19, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber3() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // moves to location B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(-1, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber4() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_B);

		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // moves to location A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(-1, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber5() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // moves to B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Dirty,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // cleans location B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(9, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber6() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Clean,
				VacuumEnvironment.LocationState.Dirty);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_B);

		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // cleans B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // moves to A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(9, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber7() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_A);

		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // cleans A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // moves to B
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(9, tve.getMedidaPerformance(agent), 0.001);
	}

	@Test
	public void testAgentActionNumber8() {
		VacuumEnvironment tve = new VacuumEnvironment(
				VacuumEnvironment.LocationState.Dirty,
				VacuumEnvironment.LocationState.Clean);
		tve.addAgent(agent, VacuumEnvironment.LOCATION_B);

		Assert.assertEquals(VacuumEnvironment.LOCATION_B,
				tve.getAgentLocation(agent));
		Assert.assertEquals(1, tve.getAgentes().size());
		tve.paso(); // moves to A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Dirty,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // cleans A
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		tve.paso(); // NOOP
		Assert.assertEquals(VacuumEnvironment.LOCATION_A,
				tve.getAgentLocation(agent));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_A));
		Assert.assertEquals(VacuumEnvironment.LocationState.Clean,
				tve.getLocationState(VacuumEnvironment.LOCATION_B));
		Assert.assertEquals(9, tve.getMedidaPerformance(agent), 0.001);
	}
}