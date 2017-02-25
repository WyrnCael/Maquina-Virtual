package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;
import tp.pr5.mv.observers.MemoryObserver;
import tp.pr5.mv.observers.Observable;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements MemoryObserver<Integer>,
		CPUObserver {
	private Controler guiCtrl;
	private MVTableModel model;
	private JTextField memPos;
	private JTextField memValue;
	private JTable table;
	private JButton setButton;

	public MemoryPanel(Controler ctrl, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
		memory.addObserver(this);
		this.guiCtrl = ctrl;
		initGUI();
	}

	private void initGUI() {
		this.setBorder(new TitledBorder("Memory"));
		this.setPreferredSize(new Dimension(350, 250));
		this.setLayout(new BorderLayout());

		model = new MVTableModel();
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setOpaque(true);
		table.setFont(new Font("Courier", Font.PLAIN, 16));

		this.add(new JScrollPane(table));

		JPanel bot = new JPanel();
		bot.setVisible(true);

		JLabel position = new JLabel("Position");
		position.setPreferredSize(new Dimension(60, 25));
		position.setVisible(true);
		bot.add(position, BorderLayout.LINE_START);

		memPos = new JTextField();
		memPos.setVisible(true);
		memPos.setPreferredSize(new Dimension(60, 25));
		memPos.setFont(new Font("Courier", Font.PLAIN, 16));
		bot.add(new JScrollPane(memPos), BorderLayout.LINE_START);

		JLabel value = new JLabel("Value");
		value.setPreferredSize(new Dimension(60, 25));
		value.setVisible(true);
		bot.add(value, BorderLayout.CENTER);

		memValue = new JTextField();
		memValue.setVisible(true);
		memValue.setPreferredSize(new Dimension(60, 25));
		memValue.setFont(new Font("Courier", Font.PLAIN, 16));
		bot.add(new JScrollPane(memValue), BorderLayout.CENTER);

		setButton = new JButton();
		setButton.setText("Set");
		setButton.setToolTipText("Set");
		bot.add(setButton, BorderLayout.LINE_END);
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					guiCtrl.memorySet(Integer.parseInt(memPos.getText()), Integer.parseInt(memValue.getText()));
				} catch (NumberFormatException a){
		    		String msg = "El valor introducido no es valido";
		    		JOptionPane.showMessageDialog(MemoryPanel.this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
		    	}
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
    			setButton.setEnabled(false);
    		}
    	});
	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			setButton.setEnabled(true);
    		}
    	});
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			setButton.setEnabled(true);
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
	public void onWrite(final int index, final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			model.setValue(index, value);
    		}
    	});
	}

	@Override
	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			model.reset();
    		}
    	});
	}
	
	private class MVTableModel extends AbstractTableModel {
		
		String [] colNames = {"Address","Value"};
		HashMap<Integer, Integer> content; 
	
        public MVTableModel() { 
        	content = new HashMap<Integer, Integer>();   
        }
        
        // onWrite llama a setValue cuando hay cambios en la memoria          
        public void setValue(int index, int value) { 
        	this.content.put(index, value);
        	fireTableDataChanged();        	
        }
        
		// onMemReset (de la memoria) llama a este método         
		public void reset() {
			this.content.clear();
			fireTableDataChanged();
		}
		
        public Object getValueAt(int rowIndex, int columnIndex) {
        	Object[] keys = this.content.keySet().toArray();
        	if (columnIndex == 1){
        		return this.content.get(keys[rowIndex]);
        	}
        	else{
        		return keys[rowIndex];
        	}        	
        }
		@Override
		public int getColumnCount() {
			return 2;
		}
		@Override
		public int getRowCount() {
			return this.content.size();
		} 
		
		public String getColumnName(int col) {
		      return colNames[col];
		}
    }
}
