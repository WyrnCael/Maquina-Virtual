package tp.pr5.mv.ins.booleans;
import tp.pr5.mv.ins.Instruction;



public class Or extends BooleanOp{
	
	public void execute(boolean cima, boolean subcima){
		if ( cima || subcima){
			this.result = 1;
		}
		else{this.result = 0; }
	}

	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Or();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "OR";
	}
}
