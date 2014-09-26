/**
 * Serialization and hashcode methodology as shown by Dr. Abram Hindle's
 * YouTube tutorials.
 * Videos obtained and viewed on Sept. 21, 2014
 * 
 * The data object that contains the information entered by the user.
 * Also contains a boolean to inform the controller if an individual item
 * is selected and to set individual items as selected (boolean)
 */

package com.ec10.assign1toDoList;

import java.io.Serializable;

public class toDoItem implements Serializable {
	/**
	 * toDoItem serialization ID
	 */
	private static final long serialVersionUID = -8453039755653387486L;
	protected String item;
	protected boolean selected;
	
	public toDoItem(String item) {
		this.item = item;
	}
	
	public String getItem() {
		return this.item;
	}
	
	public String toString() {
		return getItem().toString();
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public int hashCode() {
		return ("Item:"+getItem()).hashCode();
	}
}
