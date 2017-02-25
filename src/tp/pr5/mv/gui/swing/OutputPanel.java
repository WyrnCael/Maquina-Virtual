package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.controlers.Controler;
import tp.pr5.mv.outStrategy.OutStrategy;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
	private static Controler guiCtrl;
	private static JTextArea outputTextArea;
	
	public OutputPanel(Controler guiCtrl){
		OutputPanel.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI(){
		this.setBorder(new TitledBorder("Output"));
		this.setPreferredSize(new Dimension(650, 150));
		outputTextArea = new JTextArea();
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		outputTextArea.setEditable(false);
		this.setLayout(new BorderLayout());
        this.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

		OutStrategy outNew = new OutStreamGUI();
		guiCtrl.setOutStream( outNew );
		this.setVisible(true);
        
	} 
	
	private class OutStreamGUI implements OutStrategy {
		   OutStrategy old;
		   StringBuilder content;
		   
		   public OutStreamGUI() {
			   this.old = guiCtrl.getOutStream();
			   content = new StringBuilder();
		   }
		   
		   public void open() {   
		}
		   public void close() throws IOException {   
			   old.close();
		}
		   
		public void write(final int c) throws IOException {
			SwingUtilities.invokeLater(new Runnable() {
	    		public void run() {
	    			// 1. pasar c al OutStream original
	    			try {
						old.write(c);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						
					}
	    			// 2. concatenar c al contenido del outputTextArea
	    			content.append((char) c);
	    			outputTextArea.setText(content.toString());   	
	    		}
	    	});  
		}

		@Override
		public void reset() throws IOException {
			content = new StringBuilder();
			old.close();
			old.open();
			outputTextArea.setText("");   
		}
	}
}
