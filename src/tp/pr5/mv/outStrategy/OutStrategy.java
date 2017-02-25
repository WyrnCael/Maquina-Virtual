package tp.pr5.mv.outStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OutStrategy {
	public abstract void open() throws FileNotFoundException, IOException;
	public abstract void reset() throws IOException;
	public abstract void close() throws IOException;
	public abstract void write(int c) throws IOException;
}
