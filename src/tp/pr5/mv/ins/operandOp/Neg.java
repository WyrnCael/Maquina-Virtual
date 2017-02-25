package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;



public class Neg extends OperandOp {

	public void execute(Memory<Integer> memory, OperandStack<Integer> operand) throws FaltanOperands {
		if(!operand.esVacia()){
			int elem = (Integer) operand.cima();
			operand.desapilar();
			operand.apilar(0-elem);
		}
		else {  throw new FaltanOperands(this.toString(), operand.numeroElementos()); }
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Neg();
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String toString(){
		return "NEG";
	}

	@Override
	protected String compString() {
		return "NEG";
	}
}
