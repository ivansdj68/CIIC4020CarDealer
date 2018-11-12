package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarComparator;

public class CircularSortedDoublyLinkedList<E> implements SortedList<E> {

	private static class Node<E>{

		E element;
		Node<E> next, prev;

		public Node() {}

		public Node(E element) {
			super();
			this.element = element;
		}

		public Node(E element, Node<E> next, Node<E> prev) {
			super();
			this.element = element;
			this.next = next;
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

	}

	private Node<E> header;
	private int currentSize;
	private Comparator<E> cp;

	public CircularSortedDoublyLinkedList(Comparator<E> cp) {
		super();
		this.header = new Node<E>(null, this.header, this.header);
		this.currentSize = 0;
		this.cp = cp;
	}

	public CircularSortedDoublyLinkedList() {
		super();
		this.header = new Node<E>(null, this.header, this.header);
		this.currentSize = 0;
	}

	public CircularSortedDoublyLinkedList(int currentSize) {
		super();
		this.header = new Node<E>(null, this.header, this.header);
		this.currentSize = currentSize;
	}

	public Iterator<E> iterator() {
		return new ListIteratorLR();
	}

	private class ListIteratorLR implements Iterator<E> {
		Node<E> current;

		public ListIteratorLR(){
			this.current = header.getNext();
		}
		public boolean hasNext(){ 
			return this.current != header; 
		}

		public E next() {
			if(hasNext()){
				E n = this.current.getElement();
				this.current = this.current.getNext();
				return n;
			}
			else{
				throw new NoSuchElementException("No more elements to iterate over.");
			}
		}

		public void remove() {
			throw new UnsupportedOperationException("Remove method not implemented."); 
		}
	}

	@Override
	public boolean add(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if(this.isEmpty()) {
			this.header.setNext(newNode);
			this.header.setPrev(newNode);
			newNode.setNext(this.header);
			newNode.setPrev(this.header);
			this.currentSize++;
			return true;
		}
		else {
			Node<E> nextNode = this.header.getNext();
			while(nextNode != this.header) {
				if(cp.compare(newNode.getElement(), nextNode.getElement()) <= 0) {
					newNode.setPrev(nextNode.getPrev());
					nextNode.getPrev().setNext(newNode);
					nextNode.setPrev(newNode);
					newNode.setNext(nextNode);
					this.currentSize++;
					return true;
				}
				nextNode = nextNode.getNext();
			}
			this.header.getPrev().setNext(newNode);
			this.header.setPrev(newNode);
			newNode.setNext(this.header);
			this.currentSize++;
			return true;
		}
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		Node<E> removed = this.header.getNext();
		if(this.isEmpty()) {
			return false;
		}
		else {
			while(removed != this.header) {
				if(obj.equals(removed.getElement())) {
					removed.getPrev().setNext(removed.getNext());
					removed.getNext().setPrev(removed.getPrev());
					removed.setElement(null);
					removed.setNext(null);
					removed.setPrev(null);
					this.currentSize--;
					return true;

				}
				removed = removed.getNext();
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		this.checkBounds(index, 0, this.size()-1);

		Node<E> removed  = this.findPosition(index);

		if(this.isEmpty()) {
			return false;
		}

		removed.getPrev().setNext(removed.getNext());
		removed.getNext().setPrev(removed.getPrev());
		removed.setElement(null);
		removed.setNext(null);
		removed.setPrev(null);

		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int result = 0;

		if(this.isEmpty()) {
			return 0;
		}

		for(int i=0; i<this.size(); i++) {
			if(remove(obj) == true) {
				result++;
			}
		}

		return result;
	}

	@Override
	public E first() {
		return this.header.getNext().getElement();
	}

	@Override
	public E last() {
		return this.header.getPrev().getElement();
	}

	@Override
	public E get(int index) {
		this.checkBounds(index, 0, this.size()-1);
		return this.findPosition(index).getElement();
	}

	@Override
	public void clear() {
		int i = this.currentSize;
		while(i>0) {
			remove(i);
			i--;
		}
	}

	@Override
	public boolean contains(E e) {
		for(int i=0; i<this.size(); i++) {
			if(this.get(i).equals(e)) {
				return true;
			}
		}
		return false;
		//return e.equals(findPosition(firstIndex(e)).getElement());
	}

	@Override
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	@Override
	public int firstIndex(E e) {
		int index = 0;
		Node<E> temp = this.header.getNext();

		while(!e.equals(temp.getElement())) {
			temp = temp.getNext();
			index++;

			if (index > this.currentSize) {
				return -1;
			}
		}
		return index;
	}


	@Override
	public int lastIndex(E e) {
		int index = this.size();
		Node<E> temp = this.header.getPrev();

		while(!e.equals(temp.getElement())) {
			temp = temp.getPrev();
			index--;

			if (index < 0) {
				return -1;
			}
		}
		return index;
	}

	// Finds and returns the node at index i
	public Node<E> findPosition(int index){
		Node<E> temp = this.header.getNext();
		int i=0;

		while (i<index) {
			temp = temp.getNext();
			i++;
		}

		return temp;
	}
	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		E[] carArray=(E[]) new Object[this.size()];

		Node<E> refNode=header.getNext();
		for(int i=0;i<this.size();i++){
			carArray[i] = refNode.getElement();
			refNode=refNode.getNext();
		}	
		return carArray;
	}

/*	@SuppressWarnings("unchecked")
	public E[] toArray () {
		Object[] result = (E[]) new Object[this.currentSize];
		int i = 0;
		Node<E> temp = this.header.getNext();
		while(temp != this.header) {
			result[i] = temp.getElement();
			i++;
			temp = temp.getNext();
		}
		return (E[]) result;
	}*/

	// Checks that the index is valid for the list
	private void checkBounds(int index, int min, int max) {
		if(index<min || index>max) {
			throw new IndexOutOfBoundsException();
		}
	}

}

