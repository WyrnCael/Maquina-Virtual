package tp.pr5.mv.ins.comparation;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;



public class LT extends CompOp{
	
	public LT(){
	}
	
	public void execute(int cima, int subcima, OperandStack<Integer> operand){
		if (subcima < cima){
			this.result = 1;
		}
		else{ this.result = 0; }
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
			return new LT();
			}
			else{ return null; }
		}
		else{ return null; }
	}	

	public String toString(){
		return "LT";
	}
}
