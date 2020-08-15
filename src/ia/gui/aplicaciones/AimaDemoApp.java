package ia.gui.aplicaciones;

import ia.gui.aplicaciones.busqueda.csp.MapColoringApp;
import ia.gui.aplicaciones.busqueda.juegos.ConnectFourApp;
import ia.gui.aplicaciones.busqueda.juegos.EightPuzzleApp;
import ia.gui.aplicaciones.busqueda.juegos.ApNReinas;
import ia.gui.aplicaciones.busqueda.juegos.TicTacToeApp;
import ia.gui.aplicaciones.busqueda.mapa.RouteFindingAgentApp;
import ia.gui.aplicaciones.aspiradora.VacuumApp;
import ia.gui.demo.agente.TrivialVacuumDemo;
import ia.gui.demo.aprendizaje.LearningDemo;
import ia.gui.demo.logica.DPLLDemo;
import ia.gui.demo.logica.FolDemo;
import ia.gui.demo.logica.PLFCEntailsDemo;
import ia.gui.demo.logica.PLResolutionDemo;
import ia.gui.demo.logica.TTEntailsDemo;
import ia.gui.demo.logica.WalkSatDemo;
import ia.gui.demo.probabilidad.ProbabilityDemo;
import ia.gui.demo.busqueda.EightPuzzleDemo;
import ia.gui.demo.busqueda.MapColoringCSPDemo;
import ia.gui.demo.busqueda.DemoNReinas;
import ia.gui.demo.busqueda.NondeterministicVacuumEnvironmentDemo;
import ia.gui.demo.busqueda.TicTacToeDemo;

/**
 * The all-in-one demo application. Shows everything within one frame.
 * 
 * @author Ruediger Lunde
 */
public class AimaDemoApp {

	/** Registers agent applications and console program demos. */
	public static void registerDemos(AimaDemoFrame frame) {
		frame.addApp(VacuumApp.class);
		frame.addApp(RouteFindingAgentApp.class);
		frame.addApp(EightPuzzleApp.class);
		frame.addApp(ApNReinas.class);
		frame.addApp(TicTacToeApp.class);
		frame.addApp(ConnectFourApp.class);
		frame.addApp(MapColoringApp.class);

		frame.addDemo(TrivialVacuumDemo.class);
		
		frame.addDemo(EightPuzzleDemo.class);
		frame.addDemo(TicTacToeDemo.class);
		frame.addDemo(DemoNReinas.class);
		frame.addDemo(MapColoringCSPDemo.class);
		frame.addDemo(NondeterministicVacuumEnvironmentDemo.class);

		frame.addDemo(TTEntailsDemo.class);
		frame.addDemo(PLFCEntailsDemo.class);
		frame.addDemo(PLResolutionDemo.class);
		frame.addDemo(DPLLDemo.class);
		frame.addDemo(WalkSatDemo.class);
		frame.addDemo(FolDemo.class);

		frame.addDemo(ProbabilityDemo.class);

		frame.addDemo(LearningDemo.class);
	}

	/** Starts the demo. */
	public static void main(String[] args) {
		AimaDemoFrame frame = new AimaDemoFrame();
		registerDemos(frame);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}
