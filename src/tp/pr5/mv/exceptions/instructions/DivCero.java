package tp.pr5.mv.exceptions.instructions;

@SuppressWarnings("serial")
public class DivCero extends Exception {
	
	public DivCero(){
		super("Divisi�n por cero");	
	}
}
