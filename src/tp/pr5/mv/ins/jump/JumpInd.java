package tp.pr5.mv.ins.jump;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;



public class JumpInd extends JumpOp{
		
	public JumpInd(){		
	}
	
	public void execute(OperandStack<Integer> operand, ExecutionManager execution) throws FaltanOperands{
		if (!operand.esVacia()){
			this.value = (Integer) operand.cima()-1;
			operand.desapilar();
		}
		else{
			 throw new FaltanOperands(this.toString(), operand.numeroElementos());
		}		
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new JumpInd();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "JUMPIND";
	}

	@Override
	protected String compString() {
		// TODO Auto-generated method stub
		return null;
	}
}
