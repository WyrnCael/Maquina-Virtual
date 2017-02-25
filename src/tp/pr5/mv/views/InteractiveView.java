package tp.pr5.mv.views;


import tp.pr5.mv.Constantes;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.Observable;

public class InteractiveView implements CPUObserver {
	
	
	public InteractiveView(Observable<CPUObserver> cpu) {
		 cpu.addObserver(this);
	} 

	@Override
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecucion de "+instr.toString());
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memory, OperandStack<Integer> operand){
		System.out.println("El estado de la maquina tras ejecutar la instrucción es:"+Constantes.SALTO+memory.toString()+Constantes.SALTO+operand.toString());		
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		System.err.println(msg);
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMV program) {
		// TODO Auto-generated method stub
		
	}
}
