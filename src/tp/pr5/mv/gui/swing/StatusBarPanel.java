package tp.pr5.mv.gui.swing;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.observers.StackObserver;

@SuppressWarnings("serial")

public class StatusBarPanel extends JPanel implements StackObserver<Integer>, MemoryObserver<Integer>, CPUObserver {
   
	private int numInstr;
	private Checkbox memoryMod;
	private Checkbox operandMod;
	private JLabel numInst;
	private JLabel halt;
	
	public StatusBarPanel(Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
       stack.addObserver(this);
       memory.addObserver(this);
       cpu.addObserver(this);
       numInstr = 0;
       initGUI();
   }
	
	private void initGUI(){
		
		halt = new JLabel();
		halt.setFont(new Font("Courier", Font.PLAIN, 16));
		halt.setForeground(Color.RED);
		this.add(halt);
		
		numInst = new JLabel("Num. Instrucciones ejecutadas: "+this.numInstr);
		numInst.setFont(new Font("Courier", Font.PLAIN, 16));
		this.add(numInst);
		
		memoryMod = new Checkbox();
		memoryMod.setEnabled(false);
		memoryMod.setState(false);
		memoryMod.setVisible(true);
		this.add(memoryMod);
		
		JLabel mMod = new JLabel("Memoria modificada");
		mMod.setFont(new Font("Courier", Font.PLAIN, 16));
		this.add(mMod);
		
		operandMod = new Checkbox();
		operandMod.setEnabled(false);
		operandMod.setState(false);
		operandMod.setVisible(true);
		this.add(operandMod);
		
		JLabel oMod = new JLabel("Pila modificada");
		oMod.setFont(new Font("Courier", Font.PLAIN, 16));
		this.add(oMod);
	}
	
	@Override
	public void onStartInstrExecution(Instruction instr) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			memoryMod.setState(false); 		
    			operandMod.setState(false);
    			operandMod.setEnabled(false);
    			memoryMod.setEnabled(false);
    		}
    	});			
		
	}
	
	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> mem, OperandStack<Integer> operand) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			numInstr++;
    			numInst.setText("Num. Instrucciones ejecutadas: "+numInstr);
    		}
    	});
	}
	
	@Override
	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			halt.setText("");
    		}
    	});
	}
	
	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			halt.setText("Maquina Parada");
    		}
    	});
		
	}
	
	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			halt.setText("Maquina Parada");
    		}
    	});
	}
	
	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			halt.setText("Maquina Parada");
    		}
    	});
	}
	
	@Override
	public void onReset(ProgramMV program) {
		this.numInstr = 0;
		this.memoryMod.setState(false);
		memoryMod.setEnabled(false);
		this.operandMod.setState(false);
		operandMod.setEnabled(false);
		numInst.setText("Num. Instrucciones ejecutadas: "+numInstr);
		halt.setText("");
	}
	
	@Override
	public void onWrite(int index, Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			memoryMod.setState(true);
    			operandMod.setState(false);
    			operandMod.setEnabled(false);
    			memoryMod.setEnabled(false);
    		}
    	});
	}
	
	@Override
	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			memoryMod.setState(false);
    			memoryMod.setEnabled(false);
    		}
    	});
	}
	
	@Override
	public void onPush(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			operandMod.setState(true);
    			operandMod.setEnabled(false);
    			memoryMod.setState(false);
    			memoryMod.setEnabled(false);
    		}
    	});
	}
	
	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			operandMod.setState(true);
    			operandMod.setEnabled(false);
    			memoryMod.setState(false);
    			memoryMod.setEnabled(false);
    		}
    	});
	}
	
	@Override
	public void onStackReset() {
		operandMod.setState(false);
		operandMod.setEnabled(false);
	}
}
