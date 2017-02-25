package tp.pr5.mv.ins.unary;
import java.io.IOException;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public class In extends UnaryOp {

	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, IOException {
		operand.apilar(in.read());
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new In();
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String toString(){
		return "IN";
	}

	protected String compString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void execute(OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands {
		// TODO Auto-generated method stub
		
	}
}
