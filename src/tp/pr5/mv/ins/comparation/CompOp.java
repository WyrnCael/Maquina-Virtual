package tp.pr5.mv.ins.comparation;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class CompOp implements Instruction {
	
	protected int result;
	
	protected abstract void execute(int cima, int subcima, OperandStack<Integer> operand);
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands{
		if (operand.numeroElementos() > 1){
			int cima = (Integer) operand.cima();
			operand.desapilar();
			int subcima = (Integer) operand.cima();
			operand.desapilar();
			this.execute(cima,subcima, operand);
			operand.apilar(this.result);
		}		
		else{ throw new FaltanOperands(this.toString(), operand.numeroElementos()); }		
	}
	
	public abstract Instruction parseOp(String line);
}
