package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements PositionalList<E> {


	private static class Node<E> implements Position<E> { 
		private E element; 
		private Node<E> prev, next;
		public E getElement() {
			return element;
		}
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		public Node(E element) {
			this(element, null, null);
		}
		public Node() {
			this(null, null, null);
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		} 
		public void clean() { 
			element = null; 
			prev = next = null; 
		}
	}


	
	private Node<E> header; 
	private int size; 
	private Iterator<Position<E>> posIterator;
	Comparator<E> cp;

	public LinkedPositionalList(Comparator<E> cp) {
		this.header = new Node<E>(null, this.header, this.header);
		this.size = 0; 
		this.cp = cp;
	}
	
	public LinkedPositionalList(int size) {
		this.header = new Node<E>(null, this.header, this.header);
		this.size = size; 
	}
	
	public LinkedPositionalList() {
		this.header = new Node<E>(null, this.header, this.header);
		this.size = 0; 
	}

	private Node<E> validate(Position<E> p) throws IllegalArgumentException { 
		try { 
			@SuppressWarnings("unchecked")
			Node<E> dp = (Node<E>) p; 
			if (dp.getPrev() == null || dp.getNext() == null) 
				throw new IllegalArgumentException("Invalid internal node."); 
			if(!this.contains(dp)) {
				throw new IllegalArgumentException("Node is not on list.");
			}

			return dp; 
		} catch (ClassCastException e) { 
			throw new IllegalArgumentException("Invalid position type."); 
		}
	}

	@SuppressWarnings("unchecked")
	private Position<E> position(Node<E> dn) { 
		if (dn == header) 
			return null; 
		return (Position<E>) dn; 
	}

	
	private Node<E> addBetween(Node<E> b, Node<E> a, E e) { 
		Node<E> n = new Node<E>(e, b, a); 
		b.setNext(n); 
		a.setPrev(n); 
		size++; 
		return n; 
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	@Override
	public Position<E> last() {
		return position(header.getPrev());
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		return position(validate(p).getPrev());
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		return position(validate(p).getNext());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<E> addFirst(E e) {
		return (Position<E>) addBetween(this.header.getNext(), this.header, e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<E> addLast(E e) {
		return (Position<E>) addBetween(this.header, this.header.getNext(), e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> dp = validate(p); 
		return (Position<E>) addBetween(dp.getPrev(), dp, e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		Node<E> dp = validate(p); 
		return (Position<E>) addBetween(dp, dp.getNext(), e);
	}

	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> dp = validate(p);
		E etr = dp.getElement(); 
		dp.setElement(e);
		return etr;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> dp = validate(p); 
		E etr = dp.getElement(); 
		Node<E> b = dp.getPrev(); 
		Node<E> a = dp.getNext(); 
		b.setNext(a);
		a.setPrev(b);
		dp.clean(); 
		this.size--; 
		return etr;
	}

	@Override
	public Iterable<Position<E>> positions() { 
		return new PositionIterable(); 
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	public boolean contains(Node<E> dn) {
		Node<E> currentNode = this.header.getNext();
		for(int i=0; i<this.size(); i++) {
			if(currentNode.equals(dn)) {
				return true;
			}
			currentNode = currentNode.getNext();
		}
		return false;
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

	// Implementation of Iterator and Iterable...
	private class PositionIterator implements Iterator<Position<E>> {
		private Node<E> cursor = header.getNext(), recent = null; 
		
		@Override
		public boolean hasNext() {
			return cursor != header;
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("No more elements."); 
			recent = cursor; 
			cursor = cursor.getNext(); 
			return (Position<E>) recent;
		} 

		public void remove() throws IllegalStateException { 
			if (recent == null) 
				throw new IllegalStateException("remove() not valid at this state of the iterator."); 
			Node<E> b = recent.getPrev(); 
			Node<E> a = recent.getNext(); 
			b.setNext(a);
			a.setPrev(b);
			recent.clean(); 
			recent = null; 
			size--;          // important because we are removing recent directly....
		}

	}

	private class ElementIterator implements Iterator<E> { 
		Iterator<Position<E>> posIterator = 
				new PositionIterator(); 
		@Override
		public boolean hasNext() {
			return posIterator.hasNext();
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException("No more elements."); 
			return posIterator.next().getElement();
		} 

		public void remove() throws IllegalStateException { 
			posIterator.remove();
		}
	}

	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		} 

	}



}
