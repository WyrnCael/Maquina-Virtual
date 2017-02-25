package tp.pr5.mv.ins.comparation;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;



public class Flip extends CompOp{
	
	public Flip(){
	}
	
	public void execute(int cima, int subcima, OperandStack<Integer> operand){
		operand.apilar(cima);
		this.result = subcima;
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
			return new Flip();
			}
			else{ return null; }
		}
		else{ return null; }
	}	

	public String toString(){
		return "FLIP";
	}
}
