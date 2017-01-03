package pl.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.Stack;

public class Models implements Model{
	Hashtable<String, Boolean> hashMod;
	Hashtable<String, Boolean> tempH;
	String hashS;
	public Models(){
		hashMod = new Hashtable<String, Boolean>();
		tempH = new Hashtable<String, Boolean>();
	}
	public Models set(Symbol sym, boolean value) {
		hashMod.put(sym.toString(), value);
		return this;
	}
	public boolean get(Symbol sym) {
		return hashMod.get(sym.toString());
	}
	public boolean satisfies(KB kb) {
		boolean b = true;
		hashS = hashMod.toString();
		for(Sentence s : kb.sentences()){
			tempH = hashMod;
			if(!satisfies(s))
			{
				b=false;
			}
		}
		if(b){
			dump();
		}
		return b;
	}

	public boolean satisfies(Sentence sentence) {
		tempH = hashMod;
		String[] asdf = {"NOT","AND","OR","IMPLIES","IFF"};
		ArrayList<String> opers = new ArrayList<String>(Arrays.asList(asdf));
		Stack<String> stack = new Stack<String>();
		Queue<String> queue = new LinkedList<String>();
		stack.clear();
		queue.clear();
		StringTokenizer st = new StringTokenizer(sentence.toString());
		while(st.hasMoreTokens()){
			String tk = st.nextToken();
			int op = opers.indexOf(tk);
			if(op!=-1){
				if(stack.isEmpty()||!tk.equals("NOT")){
					stack.push(tk);
				}
				else{
					while(!stack.isEmpty()){
						int newPrec = op;
						int oldPrec = (opers.indexOf(stack.peek()));
						if(oldPrec>=newPrec){
							queue.add(stack.pop());
						}
						else{
							break;
						}
					}
					stack.push(tk);
				}
			}
			else if(tk.equals("(")){
				stack.push(tk);
			}
			else if(tk.equals(")")){
				while(!stack.peek().equals("(")){
					queue.add(stack.pop());
				}
				stack.pop();
			}
			else{
				queue.add(tk);
			}
		}
		while(!stack.isEmpty()){
			queue.add(stack.pop());
		}
		boolean boo = eval(queue);
		return boo;
	}
	public boolean eval(Queue<String> queue){
		tempH = hashMod;
		Stack<String> stack = new Stack<String>();
		stack.clear();
		boolean endLoop =false;
		while(!queue.isEmpty()){
			String elem = queue.poll();
			if(queue.isEmpty()){
				endLoop = true;
			}
			if(elem.equals("NOT")||elem.equals("AND")||elem.equals("OR")||elem.equals("IMPLIES")||elem.equals("IFF")){
				String b = stack.pop();
				String a = "";
				if(!stack.isEmpty()&&!elem.equals("NOT")){
					a = stack.pop();
				}
				switch(elem){
				case "NOT":
					tempH.put(b, !tempH.get(b));
					stack.push(b);
					break;
				case "AND":
					tempH.put(b, (tempH.get(a)&&tempH.get(b)));
					stack.push(b);
					break;
				case "OR":
					tempH.put(b, (tempH.get(a)||tempH.get(b)));
					stack.push(b);
					break;
				case "IMPLIES":
					tempH.put(b, (tempH.get(a)!=true||tempH.get(b)!=false));
					stack.push(b);
					break;
				case "IFF":
					tempH.put(b, (tempH.get(a)==tempH.get(b)));
					stack.push(b);
					break;
				}	
			}
			else{
				stack.push(elem);
			}
			if (endLoop){
				break;
			}
		}
		boolean bb = tempH.get(stack.pop());
		return (bb);
	}
	public void dump() {
//		for (String key : Collections.list(hashMod.keys())){
//			System.out.println(key+"  "+hashMod.get(key));
//		} 
		System.out.println(hashS);
		// TODO Auto-generated method stub
		
	}

}
