package pl.examples;

import pl.core.Biconditional;
import pl.core.Conjunction;
import pl.core.Disjunction;
import pl.core.KB;
import pl.core.Negation;
import pl.core.Sentence;
import pl.core.Symbol;
import pl.prover.TTEnumer;
import pl.prover.WalkSAT;

public class LiarsTruthers extends KB {
	static Sentence alpha;
	public LiarsTruthers() {
		super();
	}
	public void partA(){
		Symbol A = intern("Amy");
		Symbol B = intern("Bob");
		Symbol C = intern("Cal");
		alpha = new Conjunction(A, new Conjunction(B, C));
		add(new Biconditional(A, new Conjunction(C, A)));
		add(new Biconditional(B, new Negation(C)));
		add(new Biconditional(C, new Biconditional(B, new Negation(A))));
	}
	public void partB(){
		Symbol A = intern("Amy");
		Symbol B = intern("Bob");
		Symbol C = intern("Cal");
		alpha = new Conjunction(A, new Conjunction(B, C));
		add(new Biconditional(A, new Negation(C)));
		add(new Biconditional(B, new Conjunction(A, C)));
		add(new Biconditional(C, B));
	}


	public static void main(String[] argv) {
		LiarsTruthers ltA = new LiarsTruthers();
		ltA.partA();
		LiarsTruthers ltB = new LiarsTruthers();
		ltB.partB();
		WalkSAT wsat = new WalkSAT();
		TTEnumer tte = new TTEnumer();
		if(tte.entails(ltA,alpha)){
			System.out.println("Is true");
		}
		else{
			System.out.println("Is false");
		}
		if(tte.entails(ltB,alpha)){
			System.out.println("Is true");
		}
		else{
			System.out.println("Is false");
		}
	}

}
