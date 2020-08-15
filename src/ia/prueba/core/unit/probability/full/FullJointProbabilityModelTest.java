package ia.prueba.core.unit.probability.full;

import org.junit.Test;

import ia.core.probabilidad.ejemplo.FullJointDistributionBurglaryAlarmModel;
import ia.core.probabilidad.ejemplo.FullJointDistributionMeningitisStiffNeckModel;
import ia.core.probabilidad.ejemplo.FullJointDistributionPairFairDiceModel;
import ia.core.probabilidad.ejemplo.FullJointDistributionToothacheCavityCatchModel;
import ia.core.probabilidad.ejemplo.FullJointDistributionToothacheCavityCatchWeatherModel;
import ia.prueba.core.unit.probability.CommonFiniteProbabilityModelTests;

public class FullJointProbabilityModelTest extends
		CommonFiniteProbabilityModelTests {

	//
	// ProbabilityModel Tests
	@Test
	public void test_RollingPairFairDiceModel() {
		test_RollingPairFairDiceModel(new FullJointDistributionPairFairDiceModel());
	}

	@Test
	public void test_ToothacheCavityCatchModel() {
		test_ToothacheCavityCatchModel(new FullJointDistributionToothacheCavityCatchModel());
	}

	@Test
	public void test_ToothacheCavityCatchWeatherModel() {
		test_ToothacheCavityCatchWeatherModel(new FullJointDistributionToothacheCavityCatchWeatherModel());
	}

	@Test
	public void test_MeningitisStiffNeckModel() {
		test_MeningitisStiffNeckModel(new FullJointDistributionMeningitisStiffNeckModel());
	}

	@Test
	public void test_BurglaryAlarmModel() {
		test_BurglaryAlarmModel(new FullJointDistributionBurglaryAlarmModel());
	}

	//
	// FiniteProbabilityModel Tests
	@Test
	public void test_RollingPairFairDiceModel_Distributions() {
		test_RollingPairFairDiceModel_Distributions(new FullJointDistributionPairFairDiceModel());
	}

	@Test
	public void test_ToothacheCavityCatchModel_Distributions() {
		test_ToothacheCavityCatchModel_Distributions(new FullJointDistributionToothacheCavityCatchModel());
	}

	@Test
	public void test_ToothacheCavityCatchWeatherModel_Distributions() {
		test_ToothacheCavityCatchWeatherModel_Distributions(new FullJointDistributionToothacheCavityCatchWeatherModel());
	}

	@Test
	public void test_MeningitisStiffNeckModel_Distributions() {
		test_MeningitisStiffNeckModel_Distributions(new FullJointDistributionMeningitisStiffNeckModel());
	}

	@Test
	public void test_BurglaryAlarmModel_Distributions() {
		test_BurglaryAlarmModel_Distributions(new FullJointDistributionBurglaryAlarmModel());
	}
}
