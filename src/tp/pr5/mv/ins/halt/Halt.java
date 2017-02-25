package tp.pr5.mv.ins.halt;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public class Halt implements Instruction {
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out){
		execution.halt();
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Halt();
			}
			else{ return null; }
		}
		else{ return null; }
	}	
	
	public String toString(){
		return "HALT";
	}
}
