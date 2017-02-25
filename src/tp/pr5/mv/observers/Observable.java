package tp.pr5.mv.observers;

public interface Observable<T> { 
	public void addObserver(T o);
	public void removeObserver(T o); 
}