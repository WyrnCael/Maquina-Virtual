package tp.pr5.mv.ins.arithmetic;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.ins.Instruction;

public class Div extends ArithmeticOp{
	
	public Div(){
		super();
	}
	
	public void execute(int primerOperando, int segundoOperando) throws DivCero{
		if (segundoOperando != 0){
			this.result = primerOperando/segundoOperando;
		}
		else{
			throw new DivCero();
		}
	}
	
	public Instruction parseOp(String line){
		String [] tok = line.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Div();
			}
			else{ return null; }
		}
		else{ return null; }
	}	

	public String toString(){
		return "DIV";
	}	
}
