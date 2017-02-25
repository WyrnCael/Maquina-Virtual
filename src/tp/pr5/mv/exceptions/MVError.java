package tp.pr5.mv.exceptions;

@SuppressWarnings("serial")
public class MVError extends Exception {
	public MVError(String cadena){
		super(cadena);	
	}
}
