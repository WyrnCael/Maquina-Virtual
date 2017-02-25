package tp.pr5.mv.ins.unary;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public class Pop extends UnaryOp {

	public void execute(OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands {
		if(operand.esVacia()){
			throw new FaltanOperands(this.toString(), operand.numeroElementos()); 
		}	
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Pop();
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String toString(){
		return "POP";
	}
}
