package ds;

public interface Queue<X> {

	X access(int position);

	boolean contains(X item);

	X deQueue();

	void enQueue(X item);

	int size();

}
