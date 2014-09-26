/**
 * Persistant state implementation method as shown by Dr. Abram Hindle's
 * YouTube tutorials.
 * Videos viewed on Sept. 21, 2014
 */

/**package com.ec10.assign1toDoList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ActiveListManager {
	private Context context;
	static final String prefFile = "ActiveList";
	static final String activeKey = "activeList";
	static private ActiveListManager activeListManager = null;
	private static final String fileName = "active.sav";
	
	public ActiveListManager(Context context) {
		this.context = context;
	}
	
	public static void initManager(Context context) {
		if (activeListManager == null) {
			if (context==null) {
				throw new RuntimeException("Missing context for ActiveListManager");
			}
			activeListManager = new ActiveListManager(context);
			ActiveListManager.loadFromFile();
		}
	}
	
	public Context getContext() {
		return this.context;
	}
	
	public static ActiveListManager getManager() {
		if (activeListManager==null) {
			throw new RuntimeException("Did not initialize manager");
		}
		return activeListManager;
	}
	
	public static void loadFromFile() {
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
	
	public static void saveToFile() {
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
}
	/**
	public ActiveList loadActiveList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String activeListData = settings.getString(activeKey, "");
		if (activeListData.equals("")) {
			return new ActiveList();
		} else {
			return activeListFromString(activeListData);
		}
	}
	
	public void saveActiveList(ActiveList active) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(activeKey, activeListToString(active));
		editor.commit();
	}
	
	private String activeListToString(ActiveList active) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(active);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	private ActiveList activeListFromString(String activeListData) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(activeListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ActiveList) oi.readObject();
	}
}*/
