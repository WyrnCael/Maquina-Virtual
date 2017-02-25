package tp.pr5.mv.exceptions;

@SuppressWarnings("serial")
public class ReadProgramException extends Exception {
	
	public ReadProgramException(String program, int line, String instr){
		super("Error en el programa \"" + program + "\". [Linea " + line + "]: " + instr );	
	}
}
