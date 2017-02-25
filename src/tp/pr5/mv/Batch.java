package tp.pr5.mv;

import java.io.IOException;

import tp.pr5.mv.controlers.BatchControler;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.ReadProgramException;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.inStrategy.InStrategyFile;
import tp.pr5.mv.inStrategy.InStrategyStd;
import tp.pr5.mv.inStrategy.ReadProgram;
import tp.pr5.mv.outStrategy.OutStrategy;
import tp.pr5.mv.outStrategy.OutStrategyFile;
import tp.pr5.mv.outStrategy.OutStrategyStd;
import tp.pr5.mv.views.BatchView;

public class Batch {
	
	private Options opt;
	private CPU cpu;
	private ProgramMV prog;
	private InStrategy in;
	private OutStrategy out;
	
	public Batch(Options opt){
		this.opt = opt;
		this.cpu = new CPU();
		this.prog = new ProgramMV();
	}
	
	@SuppressWarnings("unused")
	public void modoBatch() throws ReadProgramException, FaltanOperands, DireccionIncorrecta, DivCero, IOException, MVError{
		configure();
		
		ReadProgram read = new ReadProgram();
		this.prog = read.readProgram(this.opt.getAsmFile());		
		this.cpu.loadProgram(this.prog);
		
		BatchControler ctrl = new BatchControler(cpu);
		BatchView view = new BatchView(cpu);
		ctrl.start();	
	}
	
	public void configure() throws MVError, IOException{
		
		switch (this.opt.getInStreamMode()){
		case Main._FILE_STREAM:
			this.in = new InStrategyFile(this.opt.getInFile()); break;
		case Main._NULL_STREAM:
			this.in = new InStrategyStd(); break;
		}
		
		switch (this.opt.getOutStreamMode()){
		case Main._FILE_STREAM:
			this.out = new OutStrategyFile(this.opt.getOutputFile()); break;
		case Main._NULL_STREAM:
			this.out = new OutStrategyStd(); break;
		}
		
		this.in.open();		
		this.out.open();	
		this.cpu.setInStream(this.in);
		this.cpu.setOutStream(this.out);
	}
}
