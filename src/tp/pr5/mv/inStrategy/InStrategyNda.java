package tp.pr5.mv.inStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InStrategyNda implements InStrategy {
	
	public void open() throws FileNotFoundException {}	
	public void close() throws IOException {}	
	public int read() throws IOException {
		return -1;
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
