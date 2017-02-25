package tp.pr5.mv.outStrategy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutStrategyFile implements OutStrategy {
	
	private String fname; 
	private FileWriter w;
	private PrintWriter pw;
	
	public OutStrategyFile(String fname) {
		this.fname = fname;
	}
	
	public void open() throws IOException { 
		this.w = new FileWriter(fname);
		pw = new PrintWriter( w );		
	}
	
	public void close() throws IOException {		
		this.w.close();
	}
	
	public void write(int c) { 
		pw.print((char)c);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
