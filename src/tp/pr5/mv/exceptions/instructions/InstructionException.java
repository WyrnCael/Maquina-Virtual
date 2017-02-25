package tp.pr5.mv.exceptions.instructions;

@SuppressWarnings("serial")
public class InstructionException extends Exception {
	
	public InstructionException(String cadena){
		super("Error ejecutando " + cadena  );	
	}
	
}
