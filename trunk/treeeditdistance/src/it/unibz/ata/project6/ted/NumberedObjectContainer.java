package it.unibz.ata.project6.ted;

public class NumberedObjectContainer<T> {

	private int id_;
	T object_;
	
	public NumberedObjectContainer(int id, T object) {
		id_ = id;
		object_ = object;
	}
	
	public void setId(int id) {
		id_ = id;
	}
	
	public void setObject(T object) {
		object_ = object;
	}
	
	public int getId() {
		return id_;
	}
	
	public T getObject() {
		return object_;
	}
	
	public String toString() {
		return id_ + ": " + object_.toString();
	}
}
