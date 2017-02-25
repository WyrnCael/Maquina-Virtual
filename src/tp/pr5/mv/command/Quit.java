package tp.pr5.mv.command;


public class Quit extends CommandInterpreter {

	public void executeCommand(){
		this.isQuit = true;
	}
	
	public CommandInterpreter parseComm(String cadena){
		String [] tok = cadena.split(" ");
		if (tok.length == 1){
			if (tok[0].equalsIgnoreCase(this.toString())){
				return create();
			}
			else{ return null; }
		}
		else{ return null; }
	}
	
	public CommandInterpreter create(){
		return new Quit();
	}
	
	public String toString(){
		return "QUIT";
	}
}
