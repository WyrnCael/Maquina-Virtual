package tp.pr5.mv;

import java.io.IOException;

import javax.swing.text.BadLocationException;

import tp.pr5.mv.controlers.SwingControler;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
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
import tp.pr5.mv.views.MainWindow;

public class WindowMode{

	private Options opt;
	private CPU cpu;
	private ProgramMV prog;
	private InStrategy in;
	private OutStrategy out;
	
	public WindowMode(Options opt){
		this.opt = opt;
		this.cpu = new CPU();
		this.prog = new ProgramMV();
	}
	
	@SuppressWarnings("unused")
	public void modoWindow() throws ReadProgramException, FaltanOperands, DireccionIncorrecta, DivCero, IOException, MVError, BadLocationException{
		configure();
		
		ReadProgram read = new ReadProgram();
		this.prog = read.readProgram(this.opt.getAsmFile());		
		this.cpu.loadProgram(this.prog);
		
		SwingControler ctrl = new SwingControler(cpu);
		MainWindow view = new MainWindow(ctrl,cpu, cpu.getOperandStack(), cpu.getMemory());
		ctrl.start(); 
		
	}
	
	public void configure() throws MVError, IOException{
		
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
		this.cpu.setDelay(200);
	}
}