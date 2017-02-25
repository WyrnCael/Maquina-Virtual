package tp.pr5.mv.exceptions.instructions;

@SuppressWarnings("serial")
public class DireccionIncorrecta extends InstructionException {
	
	public DireccionIncorrecta(String instr, int adress){
		super( instr + ": direcci�n incorrecta (" + adress + ")" );	
	}
}
