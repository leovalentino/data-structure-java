package app;

import ds.BasicLinkedList;

public class NumberLinkedList {

	public static void main(String[] args) {
		BasicLinkedList<Integer> bkl = new BasicLinkedList<Integer>();
		bkl.add(1);
		bkl.add(2);
		bkl.add(3);
		bkl.add(4);
		
		bkl.insert(5, 2);
	}
	
}
