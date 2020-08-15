package ia.gui.demo.logica;

import ia.core.logica.proposicional.inferencia.PLFCEntails;
import ia.core.logica.proposicional.bc.KnowledgeBase;
import ia.core.logica.proposicional.analizadorsint.sat.PropositionSymbol;

/**
 * @author Ravi Mohan
 * 
 */
public class PLFCEntailsDemo {
	private static PLFCEntails plfce = new PLFCEntails();

	public static void main(String[] args) {

		System.out.println("\nPLFCEntailsDemo\n");
		KnowledgeBase kb = new KnowledgeBase();
		kb.tell("P => Q");
		kb.tell("L & M => P");
		kb.tell("B & L => M");
		kb.tell("A & P => L");
		kb.tell("A & B => L");
		kb.tell("A");
		kb.tell("B");

		System.out.println("Example from  page 220 of AIMA 2nd Edition");
		System.out.println("KnowledgeBsse consists of sentences");
		System.out.println("P => Q");
		System.out.println("L & M => P");
		System.out.println("B & L => M");
		System.out.println("A & P => L");
		System.out.println("A & B => L");
		System.out.println("A");
		System.out.println("B");

		displayPLFCEntailment(kb, "Q");
	}

	private static void displayPLFCEntailment(KnowledgeBase kb, String q) {
		System.out.println("Running PLFCEntailment on knowledge base"
				+ " with query " + q + " gives " + plfce.plfcEntails(kb, new PropositionSymbol(q)));
	}
}
