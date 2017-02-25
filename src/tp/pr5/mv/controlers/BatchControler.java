package tp.pr5.mv.controlers;

import java.io.IOException;

import tp.pr5.mv.command.CommandInterpreter;
import tp.pr5.mv.command.step.Run;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;

public class BatchControler extends Controler {

	public BatchControler(CPU cpu) {
		super(cpu);
	}

	public void start() {	
		try {
			CommandInterpreter command = new Run();
			command.configureCommandInterpreter(this.cpu);
			command.executeCommand();
		} catch(FaltanOperands a){
			System.err.println(a.getMessage());
		} catch(DivCero a){
			System.err.println(a.getMessage());
		} catch(DireccionIncorrecta a){
			System.err.println(a.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		} catch (MVError e) {
			// TODO Auto-generated catch block
			
		} finally {			
			try {
				this.cpu.getOutStream().close();
				this.cpu.getInStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}
	}
}