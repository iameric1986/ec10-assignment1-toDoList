/**
 * Serialization method and listener implementation method as shown by Dr. Abram Hindle's
 * YouTube tutorials.
 * Videos obtained and viewed between Sept. 15, 2014 and Sept. 22, 2014.
 * 
 * This object performs basic operations on the ArrayList used to store information on
 * the active items on the to-do list ie. Add, remove etc.
 */

package com.ec10.assign1toDoList;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveList implements Serializable {
	/**
	 * ActiveList serialization ID
	 */
	private static final long serialVersionUID = 4719820401095784830L;
	protected ArrayList<toDoItem> toDoList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ActiveList() {
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
