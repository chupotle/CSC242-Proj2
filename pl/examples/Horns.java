package pl.examples;

import pl.core.Conjunction;
import pl.core.Disjunction;
import pl.core.Implication;
import pl.core.KB;
import pl.core.Negation;
import pl.core.Symbol;
import pl.prover.TTEnumer;
import pl.prover.WalkSAT;

public class Horns extends KB {
	static Symbol alphaMy;
	static Symbol alphaMa;
	static Symbol alphaHo;
	public Horns() {
		super();
		Symbol Myt = intern("Mythical");
		Symbol Mam = intern("Mammal");
		Symbol Imm = intern("Immortal");
		Symbol Hor = intern("Horned");
		Symbol Mag = intern("Magical");
		alphaMy = Myt;
		alphaMa = Mag;
		alphaHo = Hor;
		add(new Implication(Imm, Myt));
		add(new Implication(new Conjunction(new Negation(Imm), Mam), new Negation(Myt)));
		add(new Implication(Hor, new Disjunction(Imm, Mam)));
		add(new Implication(Hor, Mag));
	}

	public static void main(String[] argv) {
		Horns hns= new Horns();
		TTEnumer tte = new TTEnumer();
		//WalkSAT wsat = new WalkSAT();
		if(tte.entails(hns,alphaMy)) {
			System.out.print("We can ");
		}
		else{
			System.out.print("We cannot ");
		}
		System.out.println("prove that the unicorn is mythical.");
		System.out.println();
		if(tte.entails(hns,alphaMa)) {
			System.out.print("We can ");
		}
		else{
			System.out.print("We cannot ");
		}
		System.out.println("prove that the unicorn is a mammal.");
		System.out.println();
		if(tte.entails(hns,alphaHo)) {
			System.out.print("We can ");
		}
		else{
			System.out.print("We cannot ");
		}
		System.out.println("prove that the unicorn is horned.");
	}

}
