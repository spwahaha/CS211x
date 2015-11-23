package ast;


import java.util.Map;
import java.util.Random;

public class BinaryExpr extends NodeImp implements Expr {
	
	protected Expr l;
	protected Expr r;
	protected Operator op;
	
	public BinaryExpr(Expr l, Operator op, Expr r){
		this.l = l;
		this.r = r;
		this.op = op;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.l.size() + this.r.size() + 1;
	}

	@Override
	public Node nodeAt(int index) {
		// TODO Auto-generated method stub
		if(index == 0) return this;
		index--;
		int lsize = this.l.size();
		if(index < lsize)
			return this.l.nodeAt(index);
		else{
			return this.r.nodeAt(index - lsize);
		}
	}

	@Override
	public StringBuilder prettyPrint(StringBuilder sb) {
		// TODO Auto-generated method stub
		l.prettyPrint(sb);
    	sb.append(" ");
		sb.append(op);
    	sb.append(" ");
		r.prettyPrint(sb);
		return sb;
	}
	
	public enum Operator{
	    PLUS("+"),
	    MINUS("-"),
	    MUL("*"),
	    DIV("/"),
	    MOD("mod");
		
		private String rep;
		
		Operator(String op){
			this.rep = op;
		}
		
		public String toString(){
			return this.rep;
		}
		
		public static final Map<String, Operator> map = 
				NodeImp.createLookupMap(values());
	    
	}

	@Override
	public Node copy() {
		// TODO Auto-generated method stub
		return new BinaryExpr((Expr)this.l.copy(),this.op, (Expr)this.r.copy());
	}

	@Override
	public boolean hasChildern() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Node getChildren() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return rand.nextDouble()>0.5?this.l:this.r;	}

}
