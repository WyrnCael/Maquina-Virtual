package tp.pr5.mv.observers;

import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public interface CPUObserver {
   public void onStartInstrExecution(Instruction instr);
   public void onEndInstrExecution(int pc, Memory<Integer> mem, OperandStack<Integer> operand);
   public void onStartRun();
   public void onEndRun(); // Parte opcional
   public void onError(String msg);
   public void onHalt();
   public void onReset(ProgramMV program); // OPCIONAL
   
}
