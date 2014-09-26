/**
 * The object used to for any viewable lists that maybe created by the app.
 * This object allows the user to manipulate data within the lists made, including:
 * add, remove
 * The object also allows the controller access to the arrays in order to manipulate it.
 * Listeners are also housed here
 */

package com.ec10.assign1toDoList;

import java.util.ArrayList;

public class ListModel {
	
	protected ArrayList<toDoItem> toDoList = null;
	protected ArrayList<Listener> listeners = null;
	
	public ListModel() {
		toDoList = new ArrayList<toDoItem>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners==null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<toDoItem> getToDo() {
		return toDoList;
	}

	public toDoItem getItem(int i) {
		return toDoList.get(i);
	}
	
	public void addToDo (toDoItem item) {
		toDoList.add(item);
		//notifyListeners();
	}
	
	public void removeToDo (toDoItem item) {
		toDoList.remove(item);
		//notifyListeners();
	}
	
	public void notifyListeners() {
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	
}
