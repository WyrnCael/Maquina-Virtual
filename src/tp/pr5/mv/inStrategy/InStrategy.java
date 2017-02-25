package tp.pr5.mv.inStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InStrategy {

	public abstract void open() throws FileNotFoundException;	
	public abstract void reset() throws IOException;	
	public abstract void close() throws IOException;
	public abstract int read() throws IOException;
}