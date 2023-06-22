package cs3500.pa05.model;

@FunctionalInterface
public interface CheckedRunnable<E extends Exception> {
  void run() throws E;
}
