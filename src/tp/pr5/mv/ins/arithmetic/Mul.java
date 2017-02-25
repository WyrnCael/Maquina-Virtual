package tp.pr5.mv.ins.arithmetic;
import tp.pr5.mv.ins.Instruction;



public class Mul extends ArithmeticOp{
	
	public Mul(){
		super();
	}
	
	public void execute(int primerOperando, int segundoOperando){
		this.result = primerOperando*segundoOperando;
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Mul();
			}
			else{ return null; }
		}
		else{ return null; }
	}	

	public String toString(){
		return "MUL";
	}	
}
