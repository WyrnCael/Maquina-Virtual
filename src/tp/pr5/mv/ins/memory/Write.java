package tp.pr5.mv.ins.memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;



public class Write extends MemoryOp {

	public Write(){
	}
	
	public Write(int pos, int value){
		this.pos = pos;
		this.value = value;
	}
	
	public void execute(OperandStack<Integer> operand) {
	}

	public Instruction parseOp(String line){
		return null;
	}

	public String compString(){
		return "WRITE";
	}
	
	public String toString(){
		return "WRITE "+this.pos+" "+this.value;
	}
}
