package ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListStack<X> implements Stack<X> {

	private List<X> data;
	
	public ListStack() {
		data = new ArrayList<>();
	}
	
	@Override
	public int size() {
		return data.size();
	}

	@Override
	public X access(X item) {
		return Optional.of(item).orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public boolean contains(X item) {
		return data.contains(item);
	}

	@Override
	public X pop() {
		return data.remove(data.size() - 1);
	}

	@Override
	public void push(X newItem) {
		data.add(newItem);		
	}

}
