package tp.pr5.mv.ins.jump;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;



public class RJump extends JumpOp{
		
	private int nextPC;
	
	public RJump(){		
	}
	
	public RJump(int pos){
		this.nextPC = pos;
	}
	
	public void execute(OperandStack<Integer> operand, ExecutionManager execution){
		if (this.nextPC == 0){
			this.value = execution.getNextPC();
		}
		else if (this.nextPC == 1){
			this.value = execution.getNextPC();
		}
		else{
			this.value = execution.getNextPC()+(this.nextPC-1);
		}		
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new RJump(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String compString(){
		return "RJUMP";
	}
	
	public String toString(){
		return "RJUMP "+this.nextPC;
	}
}
