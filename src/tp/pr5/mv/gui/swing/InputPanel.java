package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.inStrategy.InStrategy;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.observers.CPUObserver;

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements CPUObserver {
	private static Controler guiCtrl;
	private static JTextArea inputTextArea;
	
	public InputPanel(Controler guiCtrl) {
		InputPanel.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		this.setBorder(new TitledBorder("Input"));
		this.setPreferredSize(new Dimension(650, 150));
		inputTextArea = new JTextArea();
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		inputTextArea.setEditable(false);
		this.setLayout(new BorderLayout());        
        this.add(new JScrollPane(inputTextArea));

		InStrategy inNew;
		try {
			inNew = new InStreamGUI();
			guiCtrl.setInStream( inNew );	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}		
		this.setVisible(true);
	} 
	
	private class InStreamGUI implements InStrategy {
		   InStrategy old;
		   StringBuilder content;
		   int pos;
		   
		   public InStreamGUI() throws IOException {
		      this.old = guiCtrl.getInStream();
		      pos = 0;
		      // 1. leer toda la entrada del old, y construir el StringBuilder content
		      content = new StringBuilder();
		      int character = old.read();
		      while (character != -1){	    	  
		    	  content.append((char) character);
		    	  character = old.read();
		      }
		      // 2. mostrar el contenido de content en el inputTextArea
		      inputTextArea.setText(content.toString());
		      inputTextArea.updateUI();
		   }
		   
		   public void open() {} // suponemos que old ya está abierto
		   
		   public void reset() throws IOException{
			   old.close();
			   old.open();
			   pos = 0;
			      // 1. leer toda la entrada del old, y construir el StringBuilder content
			      content = new StringBuilder();
			      int character = old.read();
			      while (character != -1){	    	  
			    	  content.append((char) character);
			    	  character = old.read();
			      }
			      // 2. mostrar el contenido de content en el inputTextArea
			      inputTextArea.setText(content.toString());
			      inputTextArea.updateUI();
		   }
		   
		   public void close() throws IOException { 
			   old.close(); 
			   guiCtrl.getOutStream().close();
		   } // cerrar old también
		   
		   public int read() {
			   // 1. si pos == content.length() entonce ya no hay más caracteres
			   if(pos != content.length()){
				   // 2. consultar el carácter c el la posición pos del content
				   char c = content.charAt(pos);
				   // 3. si c no es salto de linea (c!=10 y c!=13) lo cambiamos con * en content!
				   if (c != 10 && c != 13){
					   content.setCharAt(pos, '*');
				   }   
				   // 4. actualizar el inputTextArea;
				   inputTextArea.setText(content.toString());
				   // 5. actualizar pos
				   pos++;
				   // 6. devolver c;
				   return c;
			   }
			   else{
				   return -1;
			   }
		   }
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
		// TODO Auto-generated method stub
		
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
