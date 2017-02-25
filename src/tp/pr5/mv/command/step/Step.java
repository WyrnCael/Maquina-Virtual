package tp.pr5.mv.command.step;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;


public class Step extends CommandInterpreter {

	protected int steps;
	
	public Step(){
		this.steps = 1;
	}
	
	public void executeCommand() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException, MVError{
		int i = 0;
		while ( (i < this.steps) || ((this.steps == -1) && !this.cpu.isHalted())){
			if ((this.cpu.getNextPC() < this.cpu.nInstructions()) && !this.cpu.isHalted()){
				this.cpu.step();
			}
			else{
				this.cpu.halt();
			}	
			i++;
		}
	}
		
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new Step();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public String toString(){
		return "STEP";
	}
}
