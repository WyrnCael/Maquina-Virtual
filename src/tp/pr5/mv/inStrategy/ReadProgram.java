package tp.pr5.mv.inStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.InteractiveException;
import tp.pr5.mv.exceptions.ReadProgramException;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class ReadProgram {
	
	private java.util.Scanner sc = new java.util.Scanner(System.in);
	
	@SuppressWarnings("resource")
	public ProgramMV readProgram(String fname) throws ReadProgramException, FileNotFoundException{
		ProgramMV prog = new ProgramMV();
		int line = 0;
		
		char comment = ';';
		Scanner s = new Scanner(new FileReader(fname));
		while ( s.hasNext() ) {
			Instruction instruccion = null;
			String nextLine = s.nextLine();
			int pos = nextLine.indexOf(comment);
			if (pos > 0)
			{
				instruccion = InstructionParser.parse(nextLine.substring(0, pos));
			}
			else if ((pos == -1) && !nextLine.equalsIgnoreCase("")){
				instruccion = InstructionParser.parse(nextLine);
			}
			if((instruccion != null) && (pos != 0)){
				prog.addInstruction(instruccion);
			}
			if((instruccion == null) && (pos != 0)&& !nextLine.equalsIgnoreCase("")){
				throw new ReadProgramException(fname, line+1, nextLine);
			}
			line++;
		}
		s.close();
		
		return prog;
	}
	
	public ProgramMV readProgram() throws IOException, InteractiveException{
		boolean end = false;		
		String cadena = null;
		ProgramMV prog = new ProgramMV();
		System.out.println("Introduce el programa fuente");
		while(!end){	
			cadena = sc.nextLine();
			Instruction instruccion = InstructionParser.parse(cadena);
		    try{
				if(instruccion != null){
					prog.addInstruction(instruccion);
				}
				else{
					if (cadena.equalsIgnoreCase("END")){
						end = true;
					}
					else{ throw new InteractiveException("Error: Instrucción incorrecta"); }				
				}
		    } catch (InteractiveException a){
		    	end = false;
		    	System.err.println(a.getMessage());
		    }
		}
		return prog;
	}
}
