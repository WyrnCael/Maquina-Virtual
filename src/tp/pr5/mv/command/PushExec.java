package tp.pr5.mv.command;
import java.io.IOException;

import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.operandOp.Push;



public class PushExec extends CommandInterpreter {
	
	private int value;
	
	public PushExec(){
	}
	
	public PushExec(int value){
		this.value = value;
	}
	
	public void executeCommand() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException{
		Instruction push = new Push(this.value);
		this.cpu.executeIntruction(push);
	}		
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 2){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new PushExec(Integer.parseInt(tok[1]));				
			}
			else{ return null; }	
		}
		else{ return null; }
	}
	
	public String compString(){
		return "PUSH";
	}
	
	public String toString(){
		return "PUSH "+this.value;
	}	
}
