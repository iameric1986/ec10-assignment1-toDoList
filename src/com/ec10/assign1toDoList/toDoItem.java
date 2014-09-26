/** 
 * The data object that contains the information entered by the user.
 * Also contains a boolean to inform the controller if an individual item
 * is selected and to set individual items as selected (boolean)
 */

package com.ec10.assign1toDoList;

public class toDoItem {
	
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
	
}
