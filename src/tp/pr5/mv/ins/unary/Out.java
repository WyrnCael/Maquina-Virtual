package tp.pr5.mv.ins.unary;
import java.io.IOException;

import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.outStrategy.OutStrategy;



public class Out extends UnaryOp {

	public void execute(OperandStack<Integer> operand, ExecutionManager execution, InStrategy in, OutStrategy out) throws FaltanOperands, IOException {
		if(!operand.esVacia()){
			Integer caracter = operand.cima();
			out.write(caracter);			
		}
		else { throw new FaltanOperands(this.toString(), operand.numeroElementos()); }
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Out();
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String toString(){
		return "OUT";
	}
}
