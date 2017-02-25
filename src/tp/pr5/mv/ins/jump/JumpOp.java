package tp.pr5.mv.ins.jump;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class JumpOp implements Instruction {
	
	protected int value;
	
	protected abstract String compString();	
	protected abstract void execute(OperandStack<Integer> operand, ExecutionManager execution) throws FaltanOperands;
	public abstract Instruction parseOp(String line);
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws DireccionIncorrecta, FaltanOperands{
		this.execute(operand, execution);
		if (!execution.setNextPC(this.value)){ throw new DireccionIncorrecta(this.toString(), this.value);}		
	}
}
