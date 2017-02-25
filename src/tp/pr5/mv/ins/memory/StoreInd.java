package tp.pr5.mv.ins.memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;

public class StoreInd extends MemoryOp {

	public StoreInd(){
	}
	
	public void execute(OperandStack<Integer> operand) {
		this.value = (Integer) operand.cima();
		operand.desapilar();
		this.pos = (Integer) operand.cima();
		operand.desapilar();
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new StoreInd();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "STOREIND";
	}

	@Override
	protected String compString() {
		// TODO Auto-generated method stub
		return null;
	}
}
