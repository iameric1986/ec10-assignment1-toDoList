/**
 * Saving and loading implementation is inspired by Dr. Abram Hindle's YouTube tutorials
 * Obtained and viewed: Sept 21,2014
 * As well as the CMPUT301 lab for implementation of Gson.  Gson is not used in this
 * class (see ListManager.java), but this class is associated.
 * 
 * This class is a controller and provides a means for the user to interact and
 * communicate with the data structure from the UI.  The purpose of this controller is
 * to provide the user to MANIPULATE the data.  In the case of this application, that
 * includes: adding new data, removing stored data, and moving data between data
 * structures.
 * 
 * The reason for separating and differentiating the manipulation of data with
 * simply reading input and giving output is to avoid creating a "blob" class as discussed
 * in the lectures.
 */

package com.ec10.assign1toDoList;

import java.util.ArrayList;


public class ListCommunicator {
	private static ListModel activeList = null;
	private static ListModel archiveList = null;
	
	// Throws a runTimeException.
	public static ListModel getActiveList() {
		if (activeList == null) {
			activeList = new ListModel();
			activeList.addListener(new Listener() {
				@Override
				public void update() {
					ListManager.saveActive();
				}
			});		
		}
		return activeList;
	}
	
	public static ListModel getArchiveList() {
		if (archiveList == null) {
			archiveList = new ListModel();
			archiveList.addListener(new Listener() {
				@Override
				public void update() {
					ListManager.saveArchive();
				}
			});
		}
		return archiveList;
	}
	
	/**
	 * Deletes the items marked "selected" from @param list
	 * @param k determines which list to remove items from.
	 * k==0 for activeList; k==1 for archiveList
	 */
	public static void delSelected(ArrayList<toDoItem> list, int k) {
		ArrayList<toDoItem> delList = new ArrayList<toDoItem>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				delList.add(list.get(i));
			}
			list.get(i).setSelected(false);
		}
		for (int j = 0; j < delList.size(); j++) {
			if (k==0) {
				getActiveList().removeToDo(delList.get(j));
			}
			if (k==1) {
				getArchiveList().removeToDo(delList.get(j));
			}
		}
		if (k==0) {
			getActiveList().notifyListeners();
		}
		if (k==1) {
			getArchiveList().notifyListeners();
		}
	}
	/**
	 * Deletes all items from  @param list
	 * @param k determines which list to remove items from.
	 * k==0 for activeList; k==1 for archiveList
	 */
	public static void delAll(ArrayList<toDoItem> list, int k) {
		ArrayList<toDoItem> delAllList = new ArrayList<toDoItem>();
		for (int i = 0; i < list.size(); i++) {
			delAllList.add(list.get(i));
		}
		for (int j = 0; j < delAllList.size(); j++) {
			if (k==0) {
				getActiveList().removeToDo(delAllList.get(j));
			}
			if (k==1) {
				getArchiveList().removeToDo(delAllList.get(j));
			}
		}
		if (k==0) {
			getActiveList().notifyListeners();
		}
		if (k==1) {
			getArchiveList().notifyListeners();
		}
	}
	
	/**
	 * Moves items from  @param list to the other
	 * @param k determines which list to remove items from.
	 * k==0 for activeList; k==1 for archiveList
	 */
	public static void moveSelected(ArrayList<toDoItem> list, int k) {
		ArrayList<toDoItem> moveList = new ArrayList<toDoItem>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				moveList.add(list.get(i));
			}
		}
		for (int j = 0; j <moveList.size(); j++) {
			moveList.get(j).setSelected(false);
			if (k==0) {
				getArchiveList().addToDo(moveList.get(j));
				getActiveList().removeToDo(moveList.get(j));
			}
			if (k==1) {
				getActiveList().addToDo(moveList.get(j));
				getArchiveList().removeToDo(moveList.get(j));
			}
		}
			getActiveList().notifyListeners();
			getArchiveList().notifyListeners();
	}
	
	/**
	 * Moves all items from  @param list to the other
	 * @param k determines which list to remove items from.
	 * k==0 for activeList; k==1 for archiveList
	 */
	public static void moveAll(ArrayList<toDoItem> list, int k) {
		ArrayList<toDoItem> moveAllList = new ArrayList<toDoItem>();
		for (int i = 0; i < list.size(); i++) {
			moveAllList.add(list.get(i));
		}
		for (int j = 0; j < moveAllList.size(); j++) {
			moveAllList.get(j).setSelected(false);
			if (k==0) {
				getArchiveList().addToDo(moveAllList.get(j));
				getActiveList().removeToDo(moveAllList.get(j));
			}
			if (k==1) {
				getActiveList().addToDo(moveAllList.get(j));
				getArchiveList().removeToDo(moveAllList.get(j));
			}
		}
			getActiveList().notifyListeners();
			getArchiveList().notifyListeners();
	}
	
	/**
	 * Checks if @param s meets the following conditions
	 * 1. If s is a blank string, return false
	 * 2. If string is already in the active list, return false
	 * 3. If string is in the archive, remove that item from the archive and add it to the active
	 *    list.  This MUST be an EXACT match ie. "Get milk" and "Get milk" will work but "Get milk"
	 *    and "buy milk" will not work.
	 * 4. In all other cases, return true.
	 * The check is case insensitive (using .toLowerCase() for all comparisons)
	 */
	public static boolean check(String s) {
		String caseInsensitive = s.toLowerCase();
		if (s.equals("")) {
			return false;
		}
		for (int i = 0; i < getActiveList().getToDo().size(); i++) {
			if (getActiveList().getToDo().get(i).toString().toLowerCase().equals(caseInsensitive)) {
				return false;
			}
		}
		for (int j = 0; j < getArchiveList().getToDo().size(); j++) {
			if (getArchiveList().getToDo().get(j).toString().toLowerCase().equals(caseInsensitive)) {
				getArchiveList().removeToDo(getArchiveList().getToDo().get(j));
				return true;
			}
		}
		return true;
	}
}
