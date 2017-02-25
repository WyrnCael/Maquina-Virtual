package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class OperandOp implements Instruction {
	
	protected int value;
	
	protected abstract void execute(Memory<Integer> memory, OperandStack<Integer> operand) throws FaltanOperands, DireccionIncorrecta;
	public abstract Instruction parseOp(String line);
	protected abstract String compString();	
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, DireccionIncorrecta {
		this.execute(memory, operand);
		operand.apilar(this.value);	
	}
}
