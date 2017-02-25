package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.Observable;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements CPUObserver {
    private Controler guiCtrl;
    private JTextArea programTextArea;
    private ProgramMV program; 
    private int pc;
    
    public ProgramPanel(Controler ctrl, Observable<CPUObserver> cpu){
    	cpu.addObserver(this);
    	this.guiCtrl = ctrl;
        initGUI();
    }
    private void initGUI(){
        this.setLayout(new BorderLayout());
        this.setBorder(new TitledBorder("Program"));
        this.setPreferredSize(new Dimension(180, 0));
        programTextArea = new JTextArea();
        programTextArea.setLineWrap(false);
        programTextArea.setWrapStyleWord(true);
        programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
        programTextArea.setEditable(false);
        this.setVisible(true);
        this.add(new JScrollPane(programTextArea));
        program = this.guiCtrl.getProgram(); 
        pc = 0; 
        try {
			showProgram();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			
		}
    }
    
    private void showProgram() throws BadLocationException { 
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			programTextArea.setText(program.toString());
    	    	int iniLine;
				try {
					iniLine = programTextArea.getLineStartOffset(pc);
					if ( ((iniLine < programTextArea.getSelectionEnd()) || iniLine == 0) && (programTextArea.getText().length() > 0) ) {
	    	    		programTextArea.replaceRange("*", iniLine, iniLine+1);
	    	    	}
	    	    	programTextArea.setCaretPosition(iniLine);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					
				}    	    	
    		}
    	});
    }
    
	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> mem, OperandStack<Integer> operand) {
		this.pc = pc;
		try {
			showProgram();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			
		}
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(ProgramMV program) {
		this.program = this.guiCtrl.getProgram();
		this.pc = 0;
		try {
			showProgram();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			
		}
	}
}