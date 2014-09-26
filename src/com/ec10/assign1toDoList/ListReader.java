/**
 * This class is used to READ and provide information about the data.
 * IT DOES NOT MANIPULATE THE DATA ie. It does not add or remove data.
 * It simply provides a means of taking data that is stored and processes
 * it to provide information to the user or to provide that information
 * for another application.  The data itselfis NOT affected.
 */

package com.ec10.assign1toDoList;

import java.util.ArrayList;

import android.content.Intent;

public class ListReader {

	/**
	 * Takes and counts the number of selected items in the @param list
	 * and puts the number of selected, unselected, and total items in a
	 * string that is returned.
	 */
	public static String getCount(ArrayList<toDoItem> list) {
		int checked=0, unchecked=0, total=0;
		for (int i=0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				checked++;
			} else {
				unchecked++;
			}
		}
		total = checked + unchecked;
		return "Unchecked: "+unchecked+ " || Checked: "+checked+ " || Total: "+total;
		}
	/**
	 * Both e-email methods use a line of code from:
	 * http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application/12804063#12804063
	 * Credit: fiXedd
	 * Obtained: Sept. 22, 2014
	 * This line is for the setType() method to set it to "message/rfc822" which specifics to Android what type of
	 * message it is attempting to send. Line: <var_name>.setType("message/rfc822");
	 * My original code used: <var_name>setType("text/plain");
	 * 
	 * The @param k determines which header and footer to append to the e-mail.
	 * k==0 for activeList; k==1 for archiveList.  Headers and footers are my additions.
	 */
	public static Intent composeSelectEmail(ArrayList<toDoItem> list, int k) {
		Intent emailIntent;
		String emailBody="";
		
		if (k==0) {
			emailBody = "Things to do:\n\n";
		} 
		if (k==1) {
			emailBody = "Things that are finished:\n\n";
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).selected) {
				emailBody = emailBody + list.get(i).toString() + "\n";
			}
		}
		
		if (k==0) {
			emailBody = emailBody + "\nPlease finish these tasks.";
		}
		if (k==1) {
			emailBody = emailBody+ "\nI worked really hard!";
		}
		
		emailIntent = new Intent(Intent.ACTION_SEND);
		String[] emailAddress = {"recipient@emailaddress.here"};
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tasks");
		emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
		return emailIntent;
	}
	
	public static Intent composeAllEmail() {
		Intent emailIntent;
		String emailBody="";
		
		emailBody = "Things to do:\n";
		for (int i = 0; i < ListCommunicator.getActiveList().getToDo().size(); i++) {
			emailBody = emailBody + ListCommunicator.getActiveList().getToDo().get(i).toString() + "\n";
		}
		emailBody = emailBody + "\nPlease finish these tasks.\n\n";
		
		emailBody = emailBody + "Things that are finished:\n";
		for (int i = 0; i < ListCommunicator.getArchiveList().getToDo().size(); i++) {
			emailBody = emailBody + ListCommunicator.getArchiveList().getToDo().get(i).toString() + "\n";
		}
		emailBody = emailBody+ "\nI worked really hard!";

		emailIntent = new Intent(Intent.ACTION_SEND);
		String[] emailAddress = {"recipient@emailaddress.here"};
		emailIntent.setType("message/rfc822");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Tasks");
		emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
		return emailIntent;
	}
}
