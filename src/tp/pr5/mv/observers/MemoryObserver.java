package tp.pr5.mv.observers;

public interface MemoryObserver<T> {
    public void onWrite(int index, T value);
	public void onMemReset(); // OPCIONAL -- en caso que ten�is el m�todo reset
}