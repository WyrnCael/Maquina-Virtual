package tp.pr5.mv.inStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InStrategyFile implements InStrategy {
	
	private String fname; 
	private FileReader f;
	
	public InStrategyFile(String fname){ 
		this.fname = fname; 
	}
	
	public void open() throws FileNotFoundException { 
		f = new FileReader(fname); 
	}
	
	public void close() throws IOException { 
		f.close();
	}
	
	public int read() throws IOException {
		 if ( f.ready() ){
			 return f.read(); 
		 }
		 else{ return -1; }
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
