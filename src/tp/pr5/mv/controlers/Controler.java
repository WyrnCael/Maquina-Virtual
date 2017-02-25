package tp.pr5.mv.controlers;

import java.io.File;
import java.io.IOException;

import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.inStrategy.*;
import tp.pr5.mv.outStrategy.OutStrategy;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.ReadProgramException;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;

public abstract class Controler {
	
    protected CPU cpu;
    
    public Controler(CPU cpu) {
        this.cpu = cpu;
    }
    
    public void step() {
        try {
			cpu.step();
		} catch (FaltanOperands | DireccionIncorrecta | DivCero | IOException | MVError e) {
			// Tratar con la excepcion
		}
    }
    
    public void stepn(int n) { 
    	try {
			this.cpu.stepn(n);
		} catch (FaltanOperands | DireccionIncorrecta | DivCero | IOException | MVError e) {
			// Tratar excepcion
		} 
    } 
    
    public void run() {
    	try {
			this.cpu.run();
		} catch (FaltanOperands | DireccionIncorrecta | DivCero | IOException | MVError | InterruptedException e) {
			// Tratar excepcion
		}
    }

    public void pop() {
    	try {
			this.cpu.pop();
		} catch (Exception e) {
			// Tratar excepcion
		}
    }
    
    public void push(int v) {
    	this.cpu.push(v);
    }
    
    public void memorySet(int i, int v) {
    	this.cpu.setMem(i, v);
    } 
    
    public ProgramMV getProgram() {
    	return this.cpu.getProgram();
    }
    
    public void setInStream(InStrategy in) {
    	try {
			this.cpu.setInStream(in);
		} catch (MVError e) {
			// tratar excepcion
		}
    }       
    
    public InStrategy getInStream() {
    	return this.cpu.getInStream();    	
    }
    
    public void setOutStream(OutStrategy out) {
    	try {
			this.cpu.setOutStream(out);
		} catch (MVError e) {
			// tratar excepcion
		}  
    }
    
    public OutStrategy getOutStream() {
    	return this.cpu.getOutStream();
    }
    
    public void pause() {
    	this.cpu.pause();    	
    } // ejecuta el pause del cpu
    
    public void reset(){
    	try {
			this.cpu.reset();
		} catch (IOException e) {
			
		}
    }
    
    public void loadProgram(File program){
    	try {
    		ReadProgram read = new ReadProgram();    		
			try {
				this.cpu.loadProgram(read.readProgram(program.toString()));
			} catch (ReadProgramException e) {
				
			}
		} catch (IOException e) {
			
		}
    }
    
	 public abstract void start();
	 
	 public void quit() {
		 try {
			this.cpu.getOutStream().close();
			this.cpu.getInStream().close();
		} catch (IOException e) {
			// tratar excepcion
		}		 		 
		System.exit(0);
	 }
}