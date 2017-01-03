package pl.examples;

import pl.core.*;
import pl.prover.*;
public class ModusPonensKB extends KB {
	static Symbol alpha;
	public ModusPonensKB() {
		super();
		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
		alpha=p;
	}
	
	public static void main(String[] argv) {
		ModusPonensKB mp = new ModusPonensKB();
		TTEnumer tte = new TTEnumer();
		WalkSAT wsat = new WalkSAT();
		System.out.println("{P, P=>Q} = Q");
		if(wsat.entails(mp,alpha)){
			System.out.println("Is true");
		}
		else{
			System.out.println("Is false");
		}
	}

}
