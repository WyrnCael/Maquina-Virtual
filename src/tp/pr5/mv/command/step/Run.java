package tp.pr5.mv.command.step;

import tp.pr5.mv.command.CommandInterpreter;


public class Run extends Step {

	public Run(){
		this.steps = -1;
	}
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Run();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "RUN";
	}	
}
