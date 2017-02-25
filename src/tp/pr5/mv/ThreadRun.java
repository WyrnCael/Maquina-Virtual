package tp.pr5.mv;

import tp.pr5.mv.controlers.Controler;

public class ThreadRun extends Thread {
    private Controler ctrl;
    
    public ThreadRun(Controler contrl) {
        this.ctrl = contrl;
    }
    
    public void run() {
       while(!Thread.interrupted()){
        	this.ctrl.run();
        	this.interrupt();
        }        
    }
}
