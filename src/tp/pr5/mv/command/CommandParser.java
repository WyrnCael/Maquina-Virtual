package tp.pr5.mv.command;

import tp.pr5.mv.command.step.Run;
import tp.pr5.mv.command.step.Step;
import tp.pr5.mv.command.step.Steps;


public class CommandParser {

	private static CommandInterpreter[] commands = { new Step(), new Steps(), new Run(), new Quit(), new PushExec(), new PopExec(), new WriteExec() };
	
	public static CommandInterpreter parseCommand(String line){ 
		CommandInterpreter commandPars = null;
		for (CommandInterpreter comm: commands){
			CommandInterpreter command = comm.parseComm(line);
			if (command != null){
				commandPars = command;
			}
		}		
		return commandPars;
	}	
}
