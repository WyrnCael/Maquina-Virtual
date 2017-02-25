package tp.pr5.mv.command.step;

import tp.pr5.mv.command.CommandInterpreter;


public class Steps extends Step {
	
	public Steps(){		
	}
	
	public Steps(int steps){
		this.steps = steps;
	}
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.toString())){
				if (Integer.parseInt(tok[1]) > 0){
					return new Steps(Integer.parseInt(tok[1]));		
				}
				else{ return null; }
			}
			else{ return null;	}		
		}
		else{ return null; }
	}
	
	public String toString(){
		return "STEP";
	}
}
