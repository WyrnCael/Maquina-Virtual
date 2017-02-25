package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;

public class Push extends OperandOp {

	private int pos;
	
	public Push(){
	}
	
	public Push(int pos){
		this.pos = pos;
	}
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand) {
		this.value = this.pos;
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new Push(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "PUSH";
	}
	
	public String toString(){
		return "PUSH "+this.pos;
	}
}
