package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.observers.StackObserver;

@SuppressWarnings("serial")
public class StackPanel extends JPanel implements StackObserver<Integer>, CPUObserver {
    private Controler guiCtrl;
    private JList<Integer> stackArea;
    private JTextField stackElem;
    private DefaultListModel<Integer> model;
    private JButton pushButton;
    private JButton popButton;
    
    public StackPanel(Controler ctrl, Observable<StackObserver<Integer>> stack, Observable<CPUObserver> cpu) {
        cpu.addObserver(this);
        stack.addObserver(this);
    	this.guiCtrl = ctrl;
        initGUI();
    }
    private void initGUI() {
    	this.setBorder(new TitledBorder("Operand Stack"));
    	this.setPreferredSize(new Dimension(350, 250));
    	this.model = new DefaultListModel<Integer>();
        stackArea = new JList<Integer>(model);
        stackArea.setFont(new Font("Courier", Font.PLAIN, 16));
        this.setLayout(new BorderLayout());        
        this.add(new JScrollPane(stackArea));
        
        JPanel bot = new JPanel();
        bot.setVisible(true);
        
        JLabel value = new JLabel("Value");
        value.setPreferredSize(new Dimension(60, 25));
        value.setVisible(true);
        bot.add(value, BorderLayout.LINE_START);
        
        stackElem = new JTextField();
        stackElem.setVisible(true);
        stackElem.setPreferredSize(new Dimension(60, 25));
        stackElem.setFont(new Font("Courier", Font.PLAIN, 16));
        bot.add(new JScrollPane(stackElem), BorderLayout.LINE_START);
        
        pushButton = new JButton();
        pushButton.setText("Push");
        pushButton.setToolTipText("Push");
        bot.add(pushButton, BorderLayout.LINE_END);
        pushButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	try{
			    	guiCtrl.push(Integer.parseInt(stackElem.getText()));		    		
		    	} catch (NumberFormatException a){
		    		String msg = "El valor introducido no es valido";
		    		JOptionPane.showMessageDialog(StackPanel.this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
		    	}
		    } 
		});
        
        popButton = new JButton();
        popButton.setText("Pop");
        popButton.setToolTipText("Pop");
        bot.add(popButton, BorderLayout.LINE_END);
        popButton.addActionListener(new ActionListener() {        	
		    public void actionPerformed(ActionEvent e) {
		    	guiCtrl.pop();
		    } 
		});
        
        this.add(bot, BorderLayout.PAGE_END);
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
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			popButton.setEnabled(false);
    			pushButton.setEnabled(false);
    		}
    	});
	}
	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			popButton.setEnabled(true);
    			pushButton.setEnabled(true);
    		}
    	});
	}
	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			popButton.setEnabled(true);
    			pushButton.setEnabled(true);
    		}
    	});
	}
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(ProgramMV program) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			model.addElement(value);
    		}
    	});
	}
	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			model.remove(model.size()-1);
    		}
    	});
	}
	@Override
	public void onStackReset() {
		model.clear();
	}
}
