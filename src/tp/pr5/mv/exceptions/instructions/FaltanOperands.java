package tp.pr5.mv.exceptions.instructions;

@SuppressWarnings("serial")
public class FaltanOperands extends InstructionException {
	
	public FaltanOperands(String instr, int operands){
		super( instr + ": faltan operandos en la pila (hay " + operands + ")" );
	}
}
