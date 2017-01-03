package pl.examples;

import pl.core.Biconditional;
import pl.core.Conjunction;
import pl.core.Implication;
import pl.core.KB;
import pl.core.Negation;
import pl.core.Sentence;
import pl.core.Symbol;
import pl.prover.TTEnumer;

public class MoreLiarsTruthers extends KB {
	static Sentence alpha;
	public MoreLiarsTruthers() {
		super();
		Symbol A = intern("Amy");
		Symbol B = intern("Bob");
		Symbol C = intern("Cal");
		Symbol D = intern("Dee");
		Symbol E = intern("Eli");
		Symbol F = intern("Fay");
		Symbol G = intern("Gil");
		Symbol H = intern("Hal");
		Symbol I = intern("Ida");
		Symbol J = intern("Jay");
		Symbol K = intern("Kay");
		Symbol L = intern("Lee");
		alpha = new Biconditional(J, K);
		add(new Biconditional(A, new Conjunction(H, I)));
		add(new Biconditional(B, new Conjunction(A, L)));
		add(new Biconditional(C, new Conjunction(B, G)));
		add(new Biconditional(D, new Conjunction(E, L)));
		add(new Biconditional(E, new Conjunction(C, H)));
		add(new Biconditional(F, new Conjunction(D, I)));
		add(new Biconditional(G, new Negation(new Conjunction(E, J))));
		add(new Biconditional(H, new Negation(new Conjunction(F, K))));
		add(new Biconditional(I, new Negation(new Conjunction(G, K))));
		add(new Biconditional(J, new Negation(new Conjunction(A, C))));
		add(new Biconditional(K, new Negation(new Conjunction(D, F))));
		add(new Biconditional(L, new Negation(new Conjunction(B, J))));
	}



	public static void main(String[] argv) {
		MoreLiarsTruthers ltA = new MoreLiarsTruthers();
		TTEnumer tte = new TTEnumer();
		if(tte.entails(ltA,alpha)){
			System.out.println("Is true");
		}
		else{
			System.out.println("Is false");
		}
	}

}











/*
package pl.examples;

import pl.core.Biconditional;
import pl.core.Conjunction;
import pl.core.KB;
import pl.core.Negation;
import pl.core.Sentence;
import pl.core.Symbol;
import pl.prover.TTEnumer;

public class MoreLiarsTruthers extends KB {
	static Sentence alpha;
	public MoreLiarsTruthers() {
		super();
		Symbol A = intern("Amy");
		Symbol B = intern("Bob");
		Symbol C = intern("Cal");
		Symbol D = intern("Dee");
		Symbol E = intern("Eli");
		Symbol F = intern("Fay");
		Symbol G = intern("Gil");
		Symbol H = intern("Hal");
		Symbol I = intern("Ida");
		Symbol J = intern("Jay");
		Symbol K = intern("Kay");
		Symbol L = intern("Lee");
		alpha = new Conjunction(A, new Conjunction(B, new Conjunction(C,
				new Conjunction(D, new Conjunction(E, new Conjunction(F,
				new Conjunction(G, new Conjunction(H, new Conjunction(I,
				new Conjunction(J, new Conjunction(K, L)))))))))));
		add(new Biconditional(A, new Conjunction(H, I)));
		add(new Biconditional(B, new Conjunction(A, L)));
		add(new Biconditional(C, new Conjunction(B, G)));
		add(new Biconditional(D, new Conjunction(E, L)));
		add(new Biconditional(E, new Conjunction(C, H)));
		add(new Biconditional(F, new Conjunction(D, I)));
		add(new Biconditional(G, new Negation(new Conjunction(E, J))));
		add(new Biconditional(H, new Negation(new Conjunction(F, K))));
		add(new Biconditional(I, new Negation(new Conjunction(G, K))));
		add(new Biconditional(J, new Negation(new Conjunction(A, C))));
		add(new Biconditional(K, new Negation(new Conjunction(D, F))));
		add(new Biconditional(L, new Negation(new Conjunction(B, J))));
	}



	public static void main(String[] argv) {
		MoreLiarsTruthers ltA = new MoreLiarsTruthers();
		TTEnumer tte = new TTEnumer();
		if(tte.entails(ltA,alpha)){
			System.out.println("Is true");
		}
		else{
			System.out.println("Is false");
		}
	}

}












*/
