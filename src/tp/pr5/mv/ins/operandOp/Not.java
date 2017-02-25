package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;



public class Not extends OperandOp{
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand) throws FaltanOperands{
		if (!operand.esVacia()){
			if ((Integer) operand.cima() != 0){
				this.value = 0;
			}
			else{ this.value = 1; }
			operand.desapilar();
		}		
		else{ throw new FaltanOperands(this.toString(), operand.numeroElementos());	}
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Not();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "NOT";
	}
	
	@Override
	protected String compString() {
		// TODO Auto-generated method stub
		return null;
	}
}
