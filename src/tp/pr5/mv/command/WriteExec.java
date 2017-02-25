package tp.pr5.mv.command;
import java.io.IOException;

import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.memory.Write;



public class WriteExec extends CommandInterpreter {
	
	private int pos;
	private int value;	
	
	public WriteExec(){
	}
	
	public WriteExec(int pos, int value){
		this.pos = pos;
		this.value = value;
	}
	
	public void executeCommand() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException{
		Instruction write = new Write(this.pos, this.value);
		this.cpu.executeIntruction(write);
	}		
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 3){
			if (tok[0].equalsIgnoreCase(this.compString())){
				return new WriteExec(Integer.parseInt(tok[1]), Integer.parseInt(tok[2]));				
			}
			else{ return null; }	
		}
		else{ return null; }
	}
	
	public String compString(){
		return "WRITE";
	}
	
	public String toString(){
		return "WRITE "+this.pos+" "+this.value;
	}	
}
