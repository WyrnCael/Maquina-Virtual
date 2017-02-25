package tp.pr5.mv.observers;

public interface StackObserver<T> {
  public void onPush(T value);
  public void onPop(T value);
  public void onStackReset(); // OPCIONAL -- en caso que ten�is el m�todo reset
}
