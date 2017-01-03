package pl.prover;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import pl.cnf.CNFConverter;
import pl.cnf.Clause;
import pl.core.Biconditional;
import pl.core.Disjunction;
import pl.core.KB;
import pl.core.Models;
import pl.core.Sentence;
import pl.core.Symbol;

public class WalkSAT implements Prover{
	ArrayList<Symbol> symbols;
	public boolean entails(KB kb, Sentence alpha){
		Models m = new Models();
		symbols = new ArrayList<Symbol>();
		for(Symbol s: kb.symbols()){
			symbols.add(s);
			m.set(s, true);
			
		}
		return checkT(kb, alpha,m);
	}
	public boolean checkT(KB kb, Sentence alpha, Models model){
		Random random = new Random();
		Random r = new Random();
		for(int i =0;i<20000; i++){
			if(model.satisfies(kb)){
				boolean boo = model.satisfies(alpha);
				if(boo){
					model.dump();
				}
				
				return boo;
			}
			else{
				int rnd = (int)(Math.random() * symbols.size());
				model.set(symbols.get(rnd), model.get(symbols.get(rnd)));
			}
		}
		return false;
	}
}
