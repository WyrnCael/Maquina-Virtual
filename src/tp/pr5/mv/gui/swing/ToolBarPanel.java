package tp.pr5.mv.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.ThreadRun;
import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.views.MainWindow;

@SuppressWarnings("serial")
public class ToolBarPanel extends JPanel implements CPUObserver{
	
    private Controler guiCtrl;
    private JButton stepButton;
    private JButton runButton;
    private JButton quitButton;
    private JButton pauseButton;
    private JButton resetButton;
    private JButton programButton;
    private ThreadRun run;    
    private JFileChooser fc;
    
    public ToolBarPanel(Controler guiCtrll, Observable<CPUObserver> cpu) {
    	cpu.addObserver(this);
        this.guiCtrl = guiCtrll;
        this.fc = new JFileChooser();
        initGUI();
    }
    
    private void initGUI() {
    	this.setBorder(new TitledBorder("Toolbar"));    	
    	this.setVisible(true);
    	
        stepButton = new JButton();
        stepButton.setIcon(createImageIcon("step.png"));
        stepButton.setToolTipText("Step");
        this.add(stepButton);
        stepButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	resetButton.setEnabled(true);
		    	guiCtrl.step();				
		    } 
		});


        runButton = new JButton();
        runButton.setIcon(createImageIcon("run.png"));
        runButton.setSize(300, 300);
        runButton.setToolTipText("Run");
        this.add(runButton);
        runButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	pauseButton.setEnabled(true);
		    	run = new ThreadRun(guiCtrl);
		    	run.start();
		    } 
		});
        
        pauseButton = new JButton();
        pauseButton.setIcon(createImageIcon("pause.png"));
        pauseButton.setSize(300, 300);
        pauseButton.setToolTipText("Pause");
        pauseButton.setEnabled(false);
        this.add(pauseButton);
        pauseButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	guiCtrl.pause();
		    } 
		});
        
        resetButton = new JButton();
        resetButton.setIcon(createImageIcon("reset.png"));
        resetButton.setSize(300, 300);
        resetButton.setToolTipText("Reset");
        resetButton.setEnabled(false);
        this.add(resetButton);
        resetButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	guiCtrl.reset();
		    	resetButton.setEnabled(false);
		    } 
		});
        
        programButton = new JButton();
        programButton.setIcon(createImageIcon("open.png"));
        programButton.setSize(300, 300);
        programButton.setToolTipText("New Program");
        programButton.setEnabled(true);
        this.add(programButton);
        programButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	int returnVal = fc.showOpenDialog(null); 
		    	if (returnVal == JFileChooser.APPROVE_OPTION) {
		    		File file = fc.getSelectedFile();
		    		guiCtrl.loadProgram(file);
		    	} 
		    } 
		});
        
        quitButton = new JButton();
        quitButton.setIcon(createImageIcon("exit.png"));
        quitButton.setSize(300, 300);
        quitButton.setToolTipText("Quit");
        this.add(quitButton);
        quitButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	JFrame frame = new JFrame();
		    	int n = JOptionPane.showOptionDialog(frame, "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		    	frame.setAlwaysOnTop(true);
		    	if(n == 0){		    	
		    		guiCtrl.quit();
		    	}
		    	frame.setVisible(false);
		    } 
		});
        
        
    }
    
    private static ImageIcon createImageIcon(String path) {
    	java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) return new ImageIcon(imgURL);
        else return null;
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
		runButton.setEnabled(false);
    	stepButton.setEnabled(false); 
    	resetButton.setEnabled(false);
    	programButton.setEnabled(false);
	}

	@Override
	public void onEndRun() {
		runButton.setEnabled(true);
    	stepButton.setEnabled(true);  
    	resetButton.setEnabled(true);
    	programButton.setEnabled(true);
    	pauseButton.setEnabled(false);
	}

	@Override
	public void onError(String msg) {
		runButton.setEnabled(true);
	    stepButton.setEnabled(true); 
		resetButton.setEnabled(true);
    	programButton.setEnabled(true);
    	pauseButton.setEnabled(false);
	}

	@Override
	public void onHalt() {
	}

	@Override
	public void onReset(ProgramMV program) {
		runButton.setEnabled(true);
    	stepButton.setEnabled(true);  
    	resetButton.setEnabled(false);
    	programButton.setEnabled(true);
    	pauseButton.setEnabled(false);
	}
}
