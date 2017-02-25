package tp.pr5.mv.ins.memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;

public class Store extends MemoryOp {

	public Store(){
	}
	
	public Store(int pos){
		this.pos = pos;
	}
	
	public void execute(OperandStack<Integer> operand) {
		this.value = (Integer) operand.cima();
		operand.desapilar();
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new Store(Integer.parseInt(tok[1]));
			}
			else{ return null; }
		}
		else{ return null; }
	}

	public String compString(){
		return "STORE";
	}
	
	public String toString(){
		return "STORE "+this.pos;
	}
}
