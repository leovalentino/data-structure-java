package ds;

public interface Stack<X> {

	int size();

	X access(X item);

	boolean contains(X item);

	X pop();

	void push(X newItem);

}
