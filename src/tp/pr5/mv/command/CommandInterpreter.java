package tp.pr5.mv.command;
import java.io.IOException;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;



public abstract class CommandInterpreter {

	protected CPU cpu;
	protected boolean isQuit;
		
	public CommandInterpreter(){
		this.cpu = new CPU();
		this.isQuit = false;
	}
	
	public abstract void executeCommand() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException, MVError;
	public abstract CommandInterpreter parseComm(String cadena);
	
	public void configureCommandInterpreter(CPU cpu){
		this.cpu = cpu;
	}	
	
	public boolean isQuit(){
		return this.isQuit;
	}
}
