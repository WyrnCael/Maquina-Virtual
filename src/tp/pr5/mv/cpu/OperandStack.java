package tp.pr5.mv.cpu;

import java.util.ArrayList;
import java.util.LinkedList;

import tp.pr5.mv.observers.Observable;
import tp.pr5.mv.observers.StackObserver;


public class OperandStack<T>  implements Observable<StackObserver<T>> {
	
	LinkedList<T> elementos;
	private ArrayList<StackObserver<T>> observers;
	
	public OperandStack()
	{
		elementos = new LinkedList<T>();
		this.observers = new ArrayList<StackObserver<T>>();
	}
	
	public boolean esVacia(){
		return this.elementos.isEmpty();
	}
	
	public void apilar(T paramet){
		this.elementos.addLast(paramet);
		for (StackObserver<T> o : observers){
			o.onPush(paramet);
		}
	}
	
	public void desapilar(){
		for (StackObserver<T> o : observers){
			o.onPop(this.elementos.getLast());
		}
		this.elementos.removeLast();		
	}
	
	public T cima(){
		return this.elementos.getLast();
	}
	
	public int numeroElementos(){
		return this.elementos.size();
	}
	
	public void reset() { 
		elementos = new LinkedList<T>();
		for (StackObserver<T> o : observers){
			o.onStackReset();
		}
	}
	
	public LinkedList<T> getOperandStack(){
		return this.elementos;
	}
	
	public String toString(){
		String pilaString = "Pila de operandos: ";
		if ( this.elementos.isEmpty() )		{
			pilaString += "<vacia>";
		}
		else{
			for ( int i = 0 ; i < this.elementos.size() ; i++){
				pilaString += this.elementos.get(i);
				pilaString += " ";
			}
		}
		return pilaString;
	}

	public void addObserver(StackObserver<T> o) {
		observers.add(o);		
	}

	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);		
	}	
}
