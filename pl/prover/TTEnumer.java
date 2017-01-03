package pl.prover;

import java.util.ArrayList;
import pl.core.KB;
import pl.core.Models;
import pl.core.Sentence;
import pl.core.Symbol;

public class TTEnumer implements Prover{
	ArrayList<Symbol> symbols;
	public boolean entails(KB kb, Sentence alpha){
		symbols = new ArrayList<Symbol>();
		for(Symbol s: kb.symbols()){
			symbols.add(s);
		}
		return checkT(kb, alpha, 0, new Models());
	}
	public boolean checkT(KB kb, Sentence alpha, int i, Models model){
		if(i>=symbols.size()){
			//System.out.println("---------------------");
			if(model.satisfies(kb)){
				//System.out.println("test");
				if(model.satisfies(alpha)){
					model.dump();
				}
				return model.satisfies(alpha);
			}
			else{
				return true;
			}
		}
		else{
			Symbol s = symbols.get(i);
			i++;
			boolean b1 = checkT(kb, alpha, i, model.set(s, true));
			boolean b2 = checkT(kb, alpha, i, model.set(s, false));
			return b1&b2;
		}
	}
}
