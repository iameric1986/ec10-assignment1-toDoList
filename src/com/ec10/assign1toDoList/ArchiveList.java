/**
 * Serialization method and listener implementation method as shown by Dr. Abram Hindle's
 * YouTube tutorials.
 * Videos obtained and viewed between Sept. 15, 2014 and Sept. 22, 2014
 * 
 * This object performs basic operations on the ArrayList used to store information on
 * the archived items on the to-do list ie. Add, remove etc.
 */

package com.ec10.assign1toDoList;

import java.io.Serializable;
import java.util.ArrayList;

public class ArchiveList implements Serializable {
	/**
	 * ArchiveList serialization ID
	 */
	private static final long serialVersionUID = -8404150174144093495L;
	protected ArrayList<toDoItem> archiveList = null;
	protected transient ArrayList<Listener> listeners = null;
	
	public ArchiveList() {
		archiveList = new ArrayList<toDoItem>();
		listeners = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeners==null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}
	
	public ArrayList<toDoItem> getToDo() {
		return archiveList;
	}
	
	public void addToArchive (toDoItem item) {
		archiveList.add(item);
		//notifyListeners();
	}
	
	public void removeToDo (toDoItem item) {
		archiveList.remove(item);
		//notifyListeners();
	}
	
	public toDoItem getItem(int i) {
		return archiveList.get(i);
	}
		
	public void notifyListeners() {
		for (Listener listener: getListeners()) {
			listener.update();
		}
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}
}
