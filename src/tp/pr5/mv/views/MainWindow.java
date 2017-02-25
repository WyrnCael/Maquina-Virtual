package tp.pr5.mv.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.observers.StackObserver;
import tp.pr5.mv.gui.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements CPUObserver {
    private Controler ctrl;
    private Observable<CPUObserver> cpu;     
    private Observable<StackObserver<Integer>> stack;
    private Observable<MemoryObserver<Integer>> memory;
    private ToolBarPanel toolBar;
	private ProgramPanel programView;
	private StackPanel stackView;
	private MemoryPanel memoryView;
	private InputPanel inputView;
	private OutputPanel outputView;
	private StatusBarPanel statusBar;
    
    public MainWindow(Controler ctrl,  Observable<CPUObserver> cpu,  Observable<StackObserver<Integer>> stack,  Observable<MemoryObserver<Integer>> memory) {
        super("Virtual Machine");
        cpu.addObserver(this);
        this.cpu = cpu;
        this.stack = stack;
        this.memory = memory;
        this.ctrl = ctrl;
        SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			initGUI();
    		}
    	});
    }
    
    private void initGUI() {
    	toolBar = new ToolBarPanel(ctrl, cpu);
        programView = new ProgramPanel(ctrl,cpu);
        stackView = new StackPanel(ctrl, stack, cpu);
        memoryView = new MemoryPanel(ctrl, memory, cpu);
        inputView = new InputPanel(ctrl);
        outputView = new OutputPanel(ctrl);
        statusBar = new StatusBarPanel(stack, memory, cpu);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	        	JFrame frame = new JFrame();
		    	int n = JOptionPane.showOptionDialog(frame, "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		    	frame.setAlwaysOnTop(true);
		    	if(n == 0){		    	
		    		ctrl.quit();
		    	}
		    	frame.setVisible(false);
	        }
	    });
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; c.gridy = 0; c.gridheight = 1; c.gridwidth = 3; c.fill = GridBagConstraints.BOTH; c.weightx = 0; c.weighty = 0;
		mainPanel.add( toolBar, c );
		c.gridx = 0; c.gridy = 1; c.gridheight = 3; c.gridwidth = 1; c.fill = GridBagConstraints.BOTH; c.weightx = 1; c.weighty = 0;
		mainPanel.add( programView, c );
		c.gridx = 1; c.gridy = 1; c.gridheight = 1; c.gridwidth = 1; c.fill = GridBagConstraints.BOTH; c.weightx = 1; c.weighty = 1;
		mainPanel.add( stackView, c );
		c.gridx = 2; c.gridy = 1; c.gridheight = 1; c.gridwidth = 1; c.fill = GridBagConstraints.BOTH; 
		mainPanel.add( memoryView, c );
		c.gridx = 1; c.gridy = 2; c.gridheight = 1; c.gridwidth = 2; c.fill = GridBagConstraints.BOTH; c.weightx = 0; c.weighty = 1;
		mainPanel.add( inputView, c );
		c.gridx = 1; c.gridy = 3; c.gridheight = 1; c.gridwidth = 2; c.fill = GridBagConstraints.BOTH;
		mainPanel.add( outputView, c );
		c.gridx = 0; c.gridy = 4; c.gridheight = 1; c.gridwidth = 3; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1; c.weighty = 0;
		mainPanel.add( statusBar, c );
		
		this.pack();
		this.setVisible(true);
    }

	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> mem,
			OperandStack<Integer> operand) {
		// TODO Auto-generated method stub
		
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
		JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
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
