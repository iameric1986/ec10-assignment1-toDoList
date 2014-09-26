package com.ec10.assign1toDoList;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.active_tab);
		ListManager.initActiveManager(this.getApplicationContext());
		ListManager.initArchiveManager(this.getApplicationContext());
		
		final ListView listView = (ListView) findViewById(R.id.active_list);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ArrayList<toDoItem> items = ListCommunicator.getActiveList().getToDo();
		final ArrayList<toDoItem> list = new ArrayList<toDoItem>(items);
		final ArrayAdapter<toDoItem> itemAdapter = new ArrayAdapter<toDoItem>(this, android.R.layout.simple_list_item_multiple_choice, list);
		listView.setAdapter(itemAdapter);
		initListView(listView);
		
		/**
		 * Listener used to tell the ArrayList what to do when a change occurs.  
		 * Credit: Dr. Abram Hindle's YouTube tutorials.  
		 * Obtained Sept. 15, 2014.
		 */
		ListCommunicator.getActiveList().addListener(new Listener() {
			@Override
			public void update(){
				list.clear();
				ArrayList<toDoItem> items = ListCommunicator.getActiveList().getToDo();
				list.addAll(items);
				ListManager.saveActive();
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
				if (ListCommunicator.getActiveList().getItem(position).isSelected()) {
					ListCommunicator.getActiveList().getItem(position).setSelected(false);
					//view.setTag(position);
					ListCommunicator.getActiveList().notifyListeners();
				} else {
					ListCommunicator.getActiveList().getItem(position).setSelected(true);
					//view.setTag(position);
					ListCommunicator.getActiveList().notifyListeners();
				}
			}
		});
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Takes a ListView from onCreate @param lv as an argument.
	 * This method is used to initialize the ListView so that the check boxes are correctly selected upon re-launching of the
	 * program.  Done by check the selected attribute for each item loaded into the list and sets the lv to checked if the
	 * selected attribute is true.
	 */
	public void initListView(ListView lv) {
		for (int i = 0; i < ListCommunicator.getActiveList().getToDo().size(); i++) {
			if (ListCommunicator.getActiveList().getToDo().get(i).isSelected()) {
				lv.setItemChecked(i, true);
			}	
		}
	}
	/**
	 * Below are the methods that allow the view to call the ListCommunicator (controller) to perform some function on the list.
	 */
	public void switchScreen(MenuItem menu) {
		Intent intent = new Intent(MainActivity.this, Archive.class);
		startActivity(intent);
	}
	
	public void moveToArchive(MenuItem menu) {
		ListCommunicator.moveSelected(ListCommunicator.getActiveList().getToDo(), 0);
		ListView listView = (ListView) findViewById(R.id.active_list);
		listView.clearChoices();
	}
	
	public void archiveAll(MenuItem menu) {
		ListCommunicator.moveAll(ListCommunicator.getActiveList().getToDo(), 0);
		ListView listView = (ListView) findViewById(R.id.active_list);
		listView.clearChoices();
	}
	
	public void delAll(MenuItem menu) {
		ListCommunicator.delAll(ListCommunicator.getActiveList().getToDo(), 0);
		ListView listView = (ListView) findViewById(R.id.active_list);
		listView.clearChoices();
	}
	
	public void deleteActive(MenuItem menu) {
		ListCommunicator.delSelected(ListCommunicator.getActiveList().getToDo(), 0);
		ListView listView = (ListView) findViewById(R.id.active_list);
		listView.clearChoices();
	}
	
	/**
	 * On button press, checks the string and adds the string if conditions are met
	 * If false is returned, the item is not added.
	 * See the ListCommunicator for more details on string checking.
	 */
	public void addItem (View view) {
		EditText itemAdd = (EditText) findViewById(R.id.add_item);
		String itemString = itemAdd.getText().toString();
		if (ListCommunicator.check(itemString)) {
			toDoItem itemObject = new toDoItem(itemString);
			ListCommunicator.getActiveList().addToDo(itemObject);
			itemAdd.setText("");
		} else {
			if (itemString.equals("")) {
				Toast.makeText(this,  "Nothing to add!", Toast.LENGTH_SHORT).show();
			} else {
			Toast.makeText(this, "You haven't finished doing this yet!", Toast.LENGTH_SHORT).show();
			}
		}
		ListCommunicator.getActiveList().notifyListeners();
	}
	
	/**
	 * Both e-email methods use a line of code from:
	 * http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application/12804063#12804063
	 * Credit: fiXedd
	 * Obtained: Sept. 22, 2014
	 * See ListReader.java for more details.
	 */
	public void email (MenuItem menu) {
		Intent emailIntent =  ListReader.composeSelectEmail(ListCommunicator.getActiveList().getToDo(), 0);
			startActivity(Intent.createChooser(emailIntent, "E-mailing list.."));	
	}
	
	public void emailAll (MenuItem menu) {
		Intent emailIntent = ListReader.composeAllEmail();
		startActivity(Intent.createChooser(emailIntent, "E-mailing list.."));
	}
	
	public void checkStats (MenuItem menu) {
		String print = ListReader.getCount(ListCommunicator.getActiveList().getToDo());
		Toast.makeText(this, print, Toast.LENGTH_LONG).show();
	}
}