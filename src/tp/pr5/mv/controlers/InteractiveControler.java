package tp.pr5.mv.controlers;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.CommandParser;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.exceptions.InteractiveException;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;

public class InteractiveControler extends Controler {
	 
	private java.util.Scanner sc = new java.util.Scanner(System.in);
	
	public InteractiveControler(CPU cpu) {
		super(cpu);
	}

	public void start() { 
		boolean quit = false;
		do{
			System.out.print("> ");
			String cadena = this.sc.nextLine();
			CommandInterpreter comand = null;
			comand = CommandParser.parseCommand(cadena);
			try{
				if (comand != null){
					comand.configureCommandInterpreter(this.cpu);
					comand.executeCommand();
					quit = comand.isQuit();
				}
				else{ throw new InteractiveException("No lo entiendo");	}	
			} catch (InteractiveException | FaltanOperands | DivCero | DireccionIncorrecta | MVError | IOException  a){
		    	quit = false;
			} 
		}while(!cpu.isHalted() && !quit);	
		
		try {
			this.cpu.getInStream().close();
			this.cpu.getOutStream().close();
		} catch (IOException e) {
			
		}
	} 
}
