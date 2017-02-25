package tp.pr5.mv.ins.jump;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;



public class BF extends JumpOp{
		
	private int nextPC;
	
	public BF(){;
	}
	
	public BF(int pos){
		this.nextPC = pos;
	}
	
	public void execute(OperandStack<Integer> operand, ExecutionManager execution) throws FaltanOperands{
		boolean cima = false;
		if (!operand.esVacia()){
			if ((Integer) operand.cima() != 0)
			{
				cima = true;
			}
			else{ cima = false;	}
			operand.desapilar();
			if (!cima){
				this.value = this.nextPC-1;
			}
			else{
				this.value = execution.getNextPC();
			}			
		}
		else{ throw new FaltanOperands(this.toString(), operand.numeroElementos()); }
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new BF(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "BF";
	}
	
	public String toString(){
		return "BF "+this.nextPC;
	}
}
