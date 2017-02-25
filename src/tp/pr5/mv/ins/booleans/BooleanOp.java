package tp.pr5.mv.ins.booleans;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class BooleanOp implements Instruction {
	
	protected int result;
	
	protected abstract void execute(boolean cima, boolean subcima);
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands{
		boolean cima = false;
		boolean subcima = false;
		if (operand.numeroElementos() > 1){
			if ((Integer) operand.cima() != 0){
				cima = true;
			}
			else{ cima = false; }
			operand.desapilar();
			if ((Integer) operand.cima() != 0){
				subcima = true;
			}
			else{ subcima = false; }
			operand.desapilar();
			this.execute(cima, subcima);
			operand.apilar(this.result);
		}		
		else{ throw new FaltanOperands(this.toString(), operand.numeroElementos());	}
	}
	
	public abstract Instruction parseOp(String line);
}
