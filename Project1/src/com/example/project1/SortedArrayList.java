package com.example.project1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public  class SortedArrayList<E extends Comparable<E>> implements SortedList<E>{
	private int currentSize;
	private E elements[];
	
	
	private void reAllocate() {
		// create a new array with the twice the size
		E newElements[] = (E[]) new Object[2*this.elements.length];
		// copy all values into the new array
		for (int i=0; i < this.currentSize; ++i){
			newElements[i] = this.elements[i];
		}
		// replace old elmenets with newElements
		this.elements = newElements;
	}
	
	private class ListIterator<E> implements Iterator<E>{
		
		private int currentPosition;
		
		public ListIterator(){
			this.currentPosition = 0;
		}

		@Override
		public boolean hasNext() {
			return this.currentPosition < size();
		}

		@Override
		public E next() {
			if (this.hasNext()){
				return (E) elements[this.currentPosition++];
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public SortedArrayList(){
	
	this.currentSize = 0;
	this.elements = (E[]) new Object[5];
}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<E>();
	}

	@Override
	public boolean add(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument object cannot be null.");
		}
		else {
			if (this.currentSize == this.elements.length){
				reAllocate();
			}
			int i = this.currentSize-1;
			if(obj.compareTo(elements[i])<0)
			{
				elements[i+1]=obj;
				return true;
			}
			else{
			
				while(i>=0 && obj.compareTo(elements[i])>=0)
				{
				
					elements[i+1]= elements[i];
				
				}
			elements[i]= obj;
			return true;
		
			}
	
		}
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Argument object cannot be null.");
		}
		// first find obj in the array
		int target = -1;
		for (int i=0; i < this.currentSize; ++i){
			if (this.elements[i].equals(obj)){
				// found it
				target = i;
				break;
			}
		}
		if (target == -1){
			return false;
		}
		else {
			for (int i= target; i < this.currentSize - 1; ++i){
				this.elements[i] = this.elements[i+1];
			}
			// reduce size of list and remove extra last reference
			this.elements[--this.currentSize] = null;
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if (index >= 0 && index < this.currentSize){
			for (int i= index; i < this.currentSize - 1; ++i){
				this.elements[i] = this.elements[i+1];
			}
			// reduce size of list and remove extra last reference
			this.elements[--this.currentSize] = null;
			return true;

		}
		else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while (this.remove(obj)){
			counter++;
		}
		return counter;
	}

	@Override
	public E first() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.elements[0];
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.elements[this.currentSize-1];
		}
	}

	@Override
	public E get(int index) {
		if (index>=0 && index < this.size()){
			return this.elements[index];
		}
		else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public void clear() {
		for (int i=0; i < this.currentSize; ++i){
			this.elements[i] = null;
		}
		this.currentSize = 0;
	}

	@Override
	public boolean contains(E e) {
		// TODO Auto-generated method stub
		return this.firstIndex(e) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public Iterator<E> iterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int firstIndex(E e) {
		for (int i=0; i < this.currentSize; ++i){
			if (this.elements[i].equals(e)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		for (int i= this.currentSize-1; i >= 0; --i){
			if (this.elements[i].equals(e)){
				return i;
			}
		}
		return -1;
	}


}
