package tp.pr5.mv.cpu;


public class ExecutionManager {
	
	private int currentPC;
	private int nextPC;
	private boolean halt;
	
	public ExecutionManager(){
		this.currentPC = 0;
		this.nextPC = 0;
		this.halt = false;
	}
	
	public void incrementPC(){
		this.nextPC++;
		this.currentPC = this.nextPC;
	}
	
	public int getCurrentPC(){
		return this.currentPC;
	}
	
	public int getNextPC(){
		return this.nextPC;
	}
	
	public boolean setNextPC(int num){
		if(num >= -1){
			this.nextPC = num;
			return true;
		}
		else{ return false; }
	}
	
	public void halt(){
		this.halt = true;
	}
	
	public boolean isHalted(){
		return this.halt;
	}
	
}
