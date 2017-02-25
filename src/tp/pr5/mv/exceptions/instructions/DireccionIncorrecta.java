package tp.pr5.mv.exceptions.instructions;

@SuppressWarnings("serial")
public class DireccionIncorrecta extends InstructionException {
	
	public DireccionIncorrecta(String instr, int adress){
		super( instr + ": dirección incorrecta (" + adress + ")" );	
	}
}
