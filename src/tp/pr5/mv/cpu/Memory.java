package tp.pr5.mv.cpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tp.pr5.mv.exceptions.instructions.DireccionIncorrecta;
import tp.pr5.mv.observers.*;



public class Memory<T> implements Observable<MemoryObserver<T>>  {
	
	ArrayList<MemCell<T>> memoria;
	private ArrayList<MemoryObserver<T>> observers;
	
	public Memory(){
		this.memoria = new ArrayList<MemCell<T>>();
		this.observers = new ArrayList<MemoryObserver<T>>();
	}
	
	public void escribirCelda(int direccion, T valor){
		boolean encontrado = false;
		for (int i = 0; i < memoria.size() ; i++){
			if ( memoria.get(i).getPos() == direccion ){
				memoria.set(i, new MemCell<T>(direccion, valor));
				encontrado = true;
			}
		}
		if (!encontrado){ this.memoria.add(new MemCell<T>(direccion, valor)); }
		Collections.sort(memoria, new Comparator<MemCell<T>>() {
	        @Override
	        public int compare(MemCell<T>  cell1, MemCell<T>  cell2)
	        {
	        	if(cell1.pos > cell2.pos){
	            return 1;
	        	}
	        	else if (cell1.pos == cell2.pos){
	        		return 0;
	        	}
	        	else{ return -1; }
	        }
	    });
		for (MemoryObserver<T> o : observers){
			o.onWrite(direccion, valor);
		}
	}
	
	public void reset(){
		this.memoria = new ArrayList<MemCell<T>>();
		for (MemoryObserver<T> o : observers){
			o.onMemReset();
		}
	}
	
	public T leerCelda(int direccion) throws DireccionIncorrecta{ 
		boolean found = false;
		int i = 0;
		while ( (i < this.memoria.size()) && !found ){
			if (this.memoria.get(i).getPos() == direccion){
				found = true;
			}
			i++;
		}
		if(found) {
			return this.memoria.get(i-1).getValue();
		}
		else{
			throw new DireccionIncorrecta("LOAD", direccion);
		}
	}

	public String toString(){
		String cadena = "Memoria: ";
		if (this.memoria.isEmpty())
		{
			cadena += " <vacia>";
		}
		else{			
			for (int i = 0; i < this.memoria.size() ; i++) {
			    cadena += " [" + this.memoria.get(i).getPos() + "]: " + this.memoria.get(i).getValue();
			}
		}
		return cadena;		
	}
	
	public ArrayList<MemCell<T>> getArray(){
		return this.memoria;
	}
	
	public static class MemCell<T> {
	    int pos;
	    T value;
	    
	    public MemCell(int pos, T value) {
	     this.pos = pos;
	     this.value = value;
	    }
	    public int getPos() {
	     return pos;
	    }
	    public T getValue() {
	     return value;
	    }
	 }

	@Override
	public void addObserver(MemoryObserver<T> o) {
		this.observers.add(o);		
	}

	@Override
	public void removeObserver(MemoryObserver<T> o) {
		this.observers.remove(o);		
	} 
}
