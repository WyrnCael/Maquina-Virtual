package tp.pr5.mv;


public class Main {

	public static final int _BATCH_MODE = 0;
	public static final int _INTER_MODE = 1;
	public static final int _WINDOW_MODE = 2;
	public static final int _NULL_STREAM = 3;
	public static final int _STD_STREAM = 4;
	public static final int _FILE_STREAM = 5;
	
	public static void main(String [] args) {
		startMV(args); 
	}
	
	public static void startMV(String [] args) { 
		Options opt = new Options();
		try { 
			opt.parse(args);
			switch (opt.getExecutionMode()) {
				case _INTER_MODE:{
					Interactive interactive = new Interactive(opt);
					interactive.modoInteractive(); break;
				}
				case _BATCH_MODE:{
					Batch batch = new Batch(opt);
					batch.modoBatch(); break;
				}
				case _WINDOW_MODE:{
					WindowMode window = new WindowMode(opt);
					window.modoWindow(); break;
				}
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}
	}
}
