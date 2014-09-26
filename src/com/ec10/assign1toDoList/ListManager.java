/**
 * The ListManager implementation is inspired by Dr. Abram Hindle's YouTube tutorials.
 * Obtained and viewed: Sept. 21, 2014
 * Instead of using persistent state, auto-save is implemented using Gson as taught in
 * the CMPUT301 lab.
 */

package com.ec10.assign1toDoList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

public class ListManager {
	
	private Context context;
	private static ListManager activeListManager = null;
	private static ListManager archiveListManager = null;
	private static final String fileName = "active.sav";
	private static final String fileName2 = "archive.sav";
	
	public ListManager(Context context) {
		this.context = context;
	}
	
	public static void initActiveManager(Context context) {
		if (activeListManager == null) {
			activeListManager = new ListManager(context);
			ListManager.loadActive();
		}
	}
	
	public static void initArchiveManager(Context context) {
		if (archiveListManager == null) {
			archiveListManager = new ListManager(context);
			ListManager.loadArchive();
		}
	}
	
	public Context getContext() {
		return this.context;
	}
	
	public static void loadActive() {
		try {
			FileInputStream fIn = activeListManager.getContext().openFileInput(fileName);
			InputStreamReader isr = new InputStreamReader(fIn);
			Gson gson = new GsonBuilder().create();
			ArrayList<toDoItem> items = gson.fromJson(isr, new TypeToken<ArrayList<toDoItem>>() {}.getType());
			for (int i = 0; i < items.size(); i++) {
				ListCommunicator.getActiveList().getToDo().add(items.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadArchive() {
		try {
			FileInputStream fIn = archiveListManager.getContext().openFileInput(fileName2);
			InputStreamReader isr = new InputStreamReader(fIn);
			Gson gson = new GsonBuilder().create();
			ArrayList<toDoItem> items = gson.fromJson(isr, new TypeToken<ArrayList<toDoItem>>() {}.getType());
			for (int i = 0; i < items.size(); i++) {
				ListCommunicator.getArchiveList().getToDo().add(items.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveActive() {
		try {
			FileOutputStream fOut = activeListManager.getContext().openFileOutput(fileName, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			Gson gson = new GsonBuilder().create();
			gson.toJson(ListCommunicator.getActiveList().getToDo(), osw);
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveArchive() {
		try {
			FileOutputStream fOut = archiveListManager.getContext().openFileOutput(fileName2, 0);
			OutputStreamWriter osw = new OutputStreamWriter(fOut);
			Gson gson = new GsonBuilder().create();
			gson.toJson(ListCommunicator.getArchiveList().getToDo(), osw);
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

