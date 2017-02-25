package tp.pr5.mv;

import tp.pr5.mv.exceptions.*;

public class Options {

	private final String a = "-a";
	private final String as = "--asm";
	private final String h = "-h";
	private final String he = "--help";
	private final String in = "-i";
	private final String inn = "--in";
	private final String m = "-m";
	private final String mo = "--mode";
	private final String o = "-o";
	private final String ou = "--out";

	private String asmFile;
	private boolean help;
	private String inFile;
	private String outPutFile;
	private String mode;
	private int executionMode = Main._INTER_MODE;
	private int inStreamMode = Main._NULL_STREAM;
	private int outStreamMode = Main._NULL_STREAM;
	
	public void parse(String[] args) throws CmdLineExc {
		this.asmFile = null;
		this.inFile = null;
		mode = null;
		this.help = false;
		this.outPutFile = null;
		this.help = false;

		int i = 0;
		while (i < args.length) {
			if (args[i].equals(a) || args[i].equals(as)) {
				i++;
				this.asmFile = args[i];
				if (this.asmFile.equals(a) || this.asmFile.equals(as) || this.asmFile.equals(h) || this.asmFile.equals(he) || this.asmFile.equals(in) ||  this.asmFile.equals(inn) || this.asmFile.equals(m) || this.asmFile.equals(mo) || this.asmFile.equals(o) ||  this.asmFile.equals(ou) || this.asmFile == null ){ throw new CmdLineExc("Missing argument for option: " + a); }
				i++;
			} else if (args[i].equals(h) || args[i].equals(he)) {
				this.help = true;
				i++;
			} else if (args[i].equals(in) || args[i].equals(inn)) {
				i++;
				this.inFile = args[i];
				if (this.inFile.equals(a) || this.inFile.equals(as) || this.inFile.equals(h) || this.inFile.equals(he) || this.inFile.equals(in) ||  this.inFile.equals(inn) || this.inFile.equals(m) || this.inFile.equals(mo) || this.inFile.equals(o) ||  this.inFile.equals(ou) || this.inFile == null ){ throw new CmdLineExc("Missing argument for option: " + in); }
				i++;
			} else if (args[i].equals(m) || args[i].equals(mo)) {
				i++;
				this.mode = args[i];
				i++;
			} else if (args[i].equals(o) || args[i].equals(ou)) {
				i++;
				this.outPutFile = args[i];
				if (this.outPutFile.equals(a) || this.outPutFile.equals(as) || this.outPutFile.equals(h) || this.outPutFile.equals(he) || this.outPutFile.equals(in) ||  this.outPutFile.equals(inn) || this.outPutFile.equals(m) || this.outPutFile.equals(mo) || this.outPutFile.equals(o) ||  this.outPutFile.equals(ou) || this.outPutFile == null ){ throw new CmdLineExc("Missing argument for option: " + o); }
				i++;
			}else {
				throw new CmdLineExc("Opción desconocida: " +  args[i]);
			}
		}
		validate();
	}

	private void validate() throws CmdLineExc {
		
		if (this.help){
			printHelp();
		}
		
		if(this.mode.equalsIgnoreCase("interactive") || (this.mode == null))
		{
			this.executionMode = Main._INTER_MODE;
			if(getInFile() != null)	{
				this.inStreamMode = Main._FILE_STREAM;		
			}
			
			if(getOutputFile() != null)	{
				this.outStreamMode = Main._FILE_STREAM;			
			}
		}		
		else if(this.mode.equalsIgnoreCase("batch")){
			this.executionMode = Main._BATCH_MODE;
			if(getInFile() != null)	{
				this.inStreamMode = Main._FILE_STREAM;	
			}
			
			if(getOutputFile() != null)	{
				this.outStreamMode = Main._FILE_STREAM;		
			}
		}
		
		else if(this.mode.equalsIgnoreCase("window"))
		{
			this.executionMode = Main._WINDOW_MODE;
			if(getInFile() != null)	{
				this.inStreamMode = Main._FILE_STREAM;		
			}
			
			if(getOutputFile() != null)	{
				this.outStreamMode = Main._FILE_STREAM;			
			}
		}
		else{
			throw new CmdLineExc("Modo incorrecto (parametro -m|--mode)");
		}
		
		if ( ((this.executionMode == Main._BATCH_MODE) || (this.executionMode == Main._WINDOW_MODE)) && (this.asmFile == null) && (!this.help) ){
			throw new CmdLineExc("Fichero ASM no especificado.");
		}		
	}
	
	public String getAsmFile() {
		return this.asmFile;
	}
	
	public String getInFile() {
		return this.inFile;
	}
	
	public int getExecutionMode() {
		return this.executionMode;
	}
	
	public int getInStreamMode() {
		return this.inStreamMode;
	}
	
	public int getOutStreamMode() {
		return this.outStreamMode;
	}
	
	public String getOutputFile() {
		return this.outPutFile;
	}	
	
	public boolean isHelp() {
		return this.help;
	}
	
	private static void printHelp(){	
		System.out.println("usage: tp.pr4.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]");
		System.out.println();
		System.out.println("	-a,--asm <asmfile> 	Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.");
		System.out.println("	-h,--help 		Muestra esta ayuda.");
		System.out.println("	-i,--in <infile> 	Entrada del programa de la maquina-p.");
		System.out.println("	-m,--mode <mode> 	Modo de funcionamiento (batch | interactive). Por defecto, batch.");
		System.out.println("	-o,--out <outfile> 	Fichero donde se guarda la salida del programa de la maquina-p.");
		System.exit(0);
	}
}
