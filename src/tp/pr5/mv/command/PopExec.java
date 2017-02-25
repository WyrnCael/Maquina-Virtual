package tp.pr5.mv.command;
import java.io.IOException;

import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.unary.Pop;



public class PopExec extends CommandInterpreter {
	
	public PopExec(){
	}
	
	public void executeCommand() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException{
		Instruction pop = new Pop();
		this.cpu.executeIntruction(pop);
	}		
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return new PopExec();				
			}
			else{ return null; }		
		}
		else{ return null; }
	}
	
	public String toString(){
		return "POP";
	}	
}
