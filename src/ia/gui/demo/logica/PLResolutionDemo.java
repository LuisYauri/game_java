package ia.gui.demo.logica;

import ia.core.logica.proposicional.inferencia.PLResolution;
import ia.core.logica.proposicional.bc.KnowledgeBase;
import ia.core.logica.proposicional.analizadorsint.PLParser;

/**
 * @author Ravi Mohan
 * 
 */
public class PLResolutionDemo {
	private static PLResolution plr = new PLResolution();

	public static void main(String[] args) {
		KnowledgeBase kb = new KnowledgeBase();
		String fact = "(B11 => ~P11) & B11)";
		kb.tell(fact);
		System.out.println("\nPLResolutionDemo\n");
		System.out.println("adding " + fact + "to knowldegebase");
		displayResolutionResults(kb, "~B11");
	}

	private static void displayResolutionResults(KnowledgeBase kb, String query) {
		PLParser parser = new PLParser();
		System.out.println("Running plResolution of query " + query
				+ " on knowledgeBase  gives " + plr.plResolution(kb, parser.parse(query)));
	}
}
