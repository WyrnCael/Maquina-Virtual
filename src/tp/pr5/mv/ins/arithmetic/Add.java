package tp.pr5.mv.ins.arithmetic;
import tp.pr5.mv.ins.Instruction;

public class Add extends ArithmeticOp{
	
	public Add(){
		super();
	}
	
	public void execute(int primerOperando, int segundoOperando){
		this.result = primerOperando+segundoOperando;
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Add();
			}
			else{ return null; }
		}
		else{ return null; }
	}	

	public String toString(){
		return "ADD";
	}
}
