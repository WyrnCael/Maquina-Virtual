package tp.pr5.mv.ins.jump;
import tp.pr5.mv.cpu.ExecutionManager;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;



public class Jump extends JumpOp{
		
	private int nextPC;
	
	public Jump(){		
	}
	
	public Jump(int pos){
		this.nextPC = pos;
	}
	
	public void execute(OperandStack<Integer> operand, ExecutionManager execution){
		this.value = this.nextPC-1;
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new Jump(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "JUMP";
	}
	
	public String toString(){
		return "JUMP "+this.nextPC;
	}
}
