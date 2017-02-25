package tp.pr5.mv.ins.arithmetic;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public abstract class ArithmeticOp implements Instruction {
	
	protected int result;	
	
	protected abstract void execute(int primerOperando,int segundoOperando) throws DivCero;	
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, DivCero{
		if (operand.numeroElementos() > 1){
			int segundoOperando = (Integer) operand.cima();
			operand.desapilar();
			int primerOperando = (Integer) operand.cima();
			operand.desapilar();
			this.execute(primerOperando,segundoOperando);
			operand.apilar(this.result);
		}		
		else{ throw new FaltanOperands(this.toString(), operand.numeroElementos()); }	
	}	
	
	public abstract Instruction parseOp(String line); 
}