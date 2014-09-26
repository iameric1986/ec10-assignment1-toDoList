package com.ec10.assign1toDoList;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Archive extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.archive_tab);
		
		final ListView listView = (ListView) findViewById(R.id.archive_list);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ArrayList<toDoItem> items = ListCommunicator.getArchiveList().getToDo();
		final ArrayList<toDoItem> list = new ArrayList<toDoItem>(items);
		final ArrayAdapter<toDoItem> itemAdapter = new ArrayAdapter<toDoItem>(this, android.R.layout.simple_list_item_multiple_choice, list);
		listView.setAdapter(itemAdapter);
		initListView(listView);
		/**
		 * Listener used to tell the ArrayList what to do when a change occurs.  
		 * Credit: Dr. Abram Hindle's YouTube tutorials.  
		 * Obtained Sept. 15, 2014.
		 */
		ListCommunicator.getArchiveList().addListener(new Listener() {
			@Override
			public void update(){
				list.clear();
				ArrayList<toDoItem> items = ListCommunicator.getArchiveList().getToDo();
				list.addAll(items);
				ListManager.saveArchive();
				//listView.clearChoices();
				itemAdapter.notifyDataSetChanged();
			}
		});
		
		/**
		 * ListView listener that changes the "selected" state of the toDoItem inside the list.
		 */		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (ListCommunicator.getArchiveList().getItem(position).isSelected()) {
					ListCommunicator.getArchiveList().getItem(position).setSelected(false);
					ListCommunicator.getArchiveList().notifyListeners();
				} else {
					ListCommunicator.getArchiveList().getItem(position).setSelected(true);
					ListCommunicator.getArchiveList().notifyListeners();
				}
			}
		});
	}
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive, menu);
		return true;
	}
	/**
	 * Takes a ListView from onCreate @param lv as an argument.
	 * This method is used to initialize the ListView so that the check boxes are correctly selected upon re-launching of the
	 * program.  Done by check the selected attribute for each item loaded into the list and sets the lv to checked if the
	 * selected attribute is true.
	 */
	public void initListView(ListView lv) {
		for (int i = 0; i < ListCommunicator.getArchiveList().getToDo().size(); i++) {
			if (ListCommunicator.getArchiveList().getToDo().get(i).isSelected()) {
				lv.setItemChecked(i, true);
			}	
		}
	}
	
	/**
	 * Below are the methods that allow the view to call the ListCommunicator (controller) to perform some function on the list.
	 */	
	public void deleteFromArchive(MenuItem menu) {
		ListCommunicator.delSelected(ListCommunicator.getArchiveList().getToDo(), 1);
		ListView listView = (ListView) findViewById(R.id.archive_list);
		listView.clearChoices();
	}
	
	public void deleteAllFromArchive(MenuItem menu) {
		ListCommunicator.delAll(ListCommunicator.getArchiveList().getToDo(), 1);
		ListView listView = (ListView) findViewById(R.id.archive_list);
		listView.clearChoices();
	}
	
	public void returnToActive(MenuItem menu) {
		ListCommunicator.moveSelected(ListCommunicator.getArchiveList().getToDo(), 1);
		ListView listView = (ListView) findViewById(R.id.archive_list);
		listView.clearChoices();
	}
	
	public void returnAllToActive(MenuItem menu) {
		ListCommunicator.moveAll(ListCommunicator.getArchiveList().getToDo(), 1);
		ListView listView = (ListView) findViewById(R.id.archive_list);
		listView.clearChoices();
	}
	
	/**
	 * Both e-email methods use a line of code from:
	 * http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application/12804063#12804063
	 * Credit: fiXedd
	 * Obtained: Sept. 22, 2014
	 * See ListReader.java for more details
	 */	
	public void emailArchive (MenuItem menu) {
		Intent emailIntent =  ListReader.composeSelectEmail(ListCommunicator.getArchiveList().getToDo(), 1);
		try {
			startActivity(Intent.createChooser(emailIntent, "E-mailing list.."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(this, "No program found capable of sending e-mail", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void emailAllArchive (MenuItem menu) {
		Intent emailIntent =  ListReader.composeAllEmail();
		startActivity(Intent.createChooser(emailIntent, "E-mailing list.."));
	}
	
	public void checkStats (MenuItem menu) {
		String print = ListReader.getCount(ListCommunicator.getArchiveList().getToDo());
		Toast.makeText(this, print, Toast.LENGTH_LONG).show();
	}
}
