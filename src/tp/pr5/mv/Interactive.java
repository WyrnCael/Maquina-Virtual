package tp.pr5.mv;

import java.io.IOException;

import tp.pr5.mv.controlers.InteractiveControler;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exceptions.InteractiveException;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.ReadProgramException;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.inStrategy.InStrategyFile;
import tp.pr5.mv.inStrategy.InStrategyNda;
import tp.pr5.mv.inStrategy.ReadProgram;
import tp.pr5.mv.outStrategy.OutStrategy;
import tp.pr5.mv.outStrategy.OutStrategyFile;
import tp.pr5.mv.outStrategy.OutStrategyNda;
import tp.pr5.mv.views.InteractiveView;

public class Interactive {
	
	private Options opt;
	private CPU cpu;
	private ProgramMV prog;	
	private InStrategy in;
	private OutStrategy out;
	
	public Interactive(Options opt){
		this.opt = opt;
		this.cpu = new CPU();
		this.prog = new ProgramMV();
	}
	
	@SuppressWarnings("unused")
	public void modoInteractive() throws ReadProgramException, FaltanOperands, DireccionIncorrecta, DivCero, IOException, InteractiveException, MVError{
		this.configue();
		
		ReadProgram read = new ReadProgram();		
		if (this.opt.getAsmFile() != null){
			this.prog = read.readProgram(this.opt.getAsmFile());
		}
		else{
			this.prog = read.readProgram();
		}
		System.out.println(this.prog);		
		this.cpu.loadProgram(this.prog);
						
		InteractiveControler ctrl = new InteractiveControler(cpu);
		InteractiveView view = new InteractiveView(cpu);
		ctrl.start(); 

	}
	
	private void configue() throws MVError, IOException{
		
		switch (this.opt.getInStreamMode()){
			case Main._FILE_STREAM:
				this.in = new InStrategyFile(this.opt.getInFile()); break;
			case Main._NULL_STREAM:
				this.in = new InStrategyNda(); break;
		}
		
		switch (this.opt.getOutStreamMode()){
		case Main._FILE_STREAM:
			this.out = new OutStrategyFile(this.opt.getOutputFile()); break;
		case Main._NULL_STREAM:
			this.out = new OutStrategyNda(); break;
		}
		
		this.in.open();		
		this.out.open();	
		this.cpu.setInStream(this.in);
		this.cpu.setOutStream(this.out);
	}	
}
