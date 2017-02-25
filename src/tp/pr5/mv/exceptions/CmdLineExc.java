package tp.pr5.mv.exceptions;

import tp.pr5.mv.Constantes;

@SuppressWarnings("serial")
public class CmdLineExc extends Exception {
	
	public CmdLineExc(String cadena){
		super("Uso incorrecto: " + cadena + Constantes.SALTO + "Use -h|--help para más detalles.");	
	}
}
