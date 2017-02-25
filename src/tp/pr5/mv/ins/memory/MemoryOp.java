package tp.pr5.mv.ins.memory;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class MemoryOp implements Instruction {
	
	protected int pos;
	protected int value;
	
	protected abstract void execute(OperandStack<Integer> operand);
	public abstract Instruction parseOp(String line);
	protected abstract String compString();	
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) {
		this.execute(operand);
		memory.escribirCelda(this.pos, this.value);
	}
}

