package pl.examples;

import pl.core.Biconditional;
import pl.core.Disjunction;
import pl.core.KB;
import pl.core.Negation;
import pl.core.Symbol;
import pl.prover.TTEnumer;
import pl.prover.WalkSAT;

public class WumpusWorldKB extends KB {
	static Symbol alpha;
	public WumpusWorldKB() {
		super();
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");
		alpha = p12;
		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

	public static void main(String[] argv) {
		WumpusWorldKB ww = new WumpusWorldKB();
		WalkSAT wsat = new WalkSAT();
		TTEnumer tte = new TTEnumer();
		if(tte.entails(ww,alpha)){
			System.out.print("We can ");
		}
		else{
			System.out.print("We cannot ");
		}
		System.out.println("prove that whether there is a pit at location [1,2]).");
	}

}
