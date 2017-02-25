package tp.pr5.mv.ins.unary;
import java.io.IOException;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class UnaryOp implements Instruction {
	
	protected abstract void execute(OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, IOException;
	public abstract Instruction parseOp(String line);
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, IOException {
		this.execute(operand, execution, in, out);
		operand.desapilar();
	}
}
