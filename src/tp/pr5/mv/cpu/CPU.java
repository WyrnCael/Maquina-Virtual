package tp.pr5.mv.cpu;
import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.exceptions.MVError;
import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.exceptions.instructions.DivCero;
import tp.pr5.mv.exceptions.instructions.FaltanOperands;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.unary.Pop;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.outStrategy.OutStrategy;



public class CPU implements Observable<CPUObserver> {
	
	private OperandStack<Integer> operandStack;
	private Memory<Integer> memory;
	private ProgramMV program;
	private ExecutionManager execution;
	private boolean halt;
	private InStrategy inStream;
	private OutStrategy outStream;
	private ArrayList<CPUObserver> observers;
	private int delay;
	private boolean pause;
	
	public CPU(){
		this.operandStack = new OperandStack<Integer>();
		this.memory = new Memory<Integer>();
		this.program = new ProgramMV();
		this.execution = new ExecutionManager();
		this.halt = false;
		this.observers = new ArrayList<CPUObserver>();
		this.delay = 0;
		pause = false;
	}
	
	public void  step() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException, MVError{
		if (this.isHalted()){ for (CPUObserver o : observers){ o.onError("No quedan intrucciones por ejecutar"); } throw new MVError("No quedan intrucciones por ejecutar"); }
		if(execution.getNextPC() < program.nInstrucciones()){
			Instruction instrEjec = this.program.getInstruction(execution.getNextPC());
			for (CPUObserver o : observers){ o.onStartInstrExecution(instrEjec);}		
			try {
				instrEjec.execute(this.memory, this.operandStack, this.execution, this.inStream, this.outStream);
			} catch (FaltanOperands | DireccionIncorrecta | DivCero	| IOException e) {
				for (CPUObserver o : observers){ o.onError(e.getMessage()); }
				throw e;
			}
			execution.incrementPC();
			for (CPUObserver o : observers){ o.onEndInstrExecution(execution.getCurrentPC(), this.memory, this.operandStack);}	
			if (execution.getNextPC() == program.nInstrucciones()){	
				this.execution.halt();
				for (CPUObserver o : observers){ o.onHalt();}		
			}
		}
	}
	
	public void stepn(int n) throws FaltanOperands, DireccionIncorrecta, DivCero, IOException, MVError{
		for(int i = 0; i < n ; i++){
			this.step();
		}
	}
	
	public void  run() throws FaltanOperands, DivCero, DireccionIncorrecta, IOException, MVError, InterruptedException{
		if (this.isHalted()){ for (CPUObserver o : observers){ o.onError("No quedan intrucciones por ejecutar"); } throw new MVError("No quedan intrucciones por ejecutar"); }
		for (CPUObserver o : observers){ o.onStartRun();}
		while( (execution.getNextPC() < program.nInstrucciones()) && !pause ){
			this.step();
			Thread.sleep(delay);
		}
		for (CPUObserver o : observers){ o.onEndRun();}	
		pause = false;
	}
	
	public void pop() throws Exception{
		Instruction instruction = new Pop();
		try {
			instruction.execute(this.memory, this.operandStack, this.execution, this.inStream, this.outStream);
		} catch (FaltanOperands | DireccionIncorrecta | DivCero | IOException e) {
			for (CPUObserver o : observers){ o.onError(e.getMessage()); }	
			throw e;
		}
	}
	
	public void push(int n){
		this.operandStack.apilar(n);
	}
	
	public void setMem(int index, int valor) {
		this.memory.escribirCelda(index, valor);
	}
	
	public void pause() { 
		this.pause = true;
	}	
	
	public void reset() throws IOException { 
		this.operandStack.reset();
		this.memory.reset();
		this.execution = new ExecutionManager();
		this.halt = false;
		pause = false;
		this.inStream.reset();
		this.outStream.reset();
		for (CPUObserver o : observers){ o.onReset(program); }	
	}
	
	public boolean isHalted(){
		this.halt = execution.isHalted();
		return this.halt;
	}
	
	public void loadProgram(ProgramMV program) throws IOException{
		this.program = program;
		this.reset();
		if(program.nInstrucciones() == 0){
			for (CPUObserver o : observers){ o.onError("¡El programa no contiene instrucciones!"); }
		}
	}	
	
	public int nInstructions(){
		return this.program.nInstrucciones();
	}
	
	public int getNextPC(){
		return this.execution.getNextPC();
	}
	
	public void setNextPC(int pc){
		execution.setNextPC(pc);
	}
	
	public Instruction nextInstuction(){
		return program.getInstruction(execution.getNextPC());
	}
	
	public void halt(){
		execution.halt();
	}
	
	public void executeIntruction(Instruction instruction) throws FaltanOperands, DivCero, DireccionIncorrecta, IOException {
		instruction.execute(this.memory, this.operandStack, this.execution, this.inStream, this.outStream);
	}
	
	public void setInStream(InStrategy s) throws MVError { 
		if (s == null) throw new MVError("Cannot set inStream to null");
		 else inStream = s;
	}
	
	public void setOutStream(OutStrategy s) throws MVError {
		if (s == null) throw new MVError("Cannot set inStream to null");
		 else outStream = s;
	}
	
	public InStrategy getInStream() { return inStream; }
	
	public OutStrategy getOutStream() { return outStream; }
	
	public OperandStack<Integer> getOperandStack(){
		return this.operandStack;
	}
	
	public void setDelay(int n){
		this.delay = n;
	}
	
	public int getPC(){
		return this.execution.getCurrentPC();
	}
	
	public ProgramMV getProgram(){
		return this.program;
	}
	
	public Memory<Integer> getMemory(){
		return this.memory;
	}
	
	public String toString(){
		String cpuString = "El estado de la maquina tras ejecutar la instrucción es:"+Constantes.SALTO+this.memory.toString()+Constantes.SALTO+this.operandStack.toString();
		return cpuString;
	}
	
	public void addObserver(CPUObserver o) { observers.add(o);  }
	
	public void removeObserver(CPUObserver o) { observers.remove(o);  }
}
