package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.ins.Instruction;

public class Load extends OperandOp {

	private int pos;
	
	public Load(){
	}
	
	public Load(int pos){
		this.pos = pos;
	}
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand) throws DireccionIncorrecta {
		this.value = (Integer) memory.leerCelda(this.pos);		
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new Load(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "LOAD";
	}
	
	public String toString(){
		return "LOAD "+this.pos;
	}
}
