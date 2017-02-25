package tp.pr5.mv.inStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InStrategyStd implements InStrategy {
	
	public void open() throws FileNotFoundException {}	
	public void close() throws IOException {}
	
	public int read() throws IOException {
		int c = System.in.read();
		while ( System.in.available() > 0 ) System.in.read();
		return c;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
