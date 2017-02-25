package tp.pr5.mv.ins.operandOp;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.ins.Instruction;

public class LoadInd extends OperandOp {

	private int pos;
	
	public LoadInd(){
	}
	
	public void execute(Memory<Integer> memory, OperandStack<Integer> operand) throws DireccionIncorrecta {
		this.pos = (Integer) operand.cima();
		operand.desapilar();
		this.value = (Integer) memory.leerCelda(this.pos);
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new LoadInd();
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "LOADIND";
	}
	
	public String toString(){
		return "LOADIND "+this.pos;
	}
}
