/**
 * Persistant state implementation method as shown by Dr. Abram Hindle's
 * YouTube tutorials.
 * Videos viewed on Sept. 21, 2014
 */
/**
package com.ec10.assign1toDoList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ArchiveListManager {
	private Context context;
	static final String prefFile = "ArchiveList";
	static final String archiveKey = "archiveList";
	static private ArchiveListManager archiveListManager = null;
	
	public ArchiveListManager(Context context) {
		this.context = context;
	}
	
	public static void initManager(Context context) {
		if (archiveListManager == null) {
			if (context==null) {
				throw new RuntimeException("Missing context for ArchiveListManager");
			}
			archiveListManager = new ArchiveListManager(context);
		}
	}
	
	public static ArchiveListManager getManager() {
		if (archiveListManager==null) {
			throw new RuntimeException("Did not initialize manager");
		}
		return archiveListManager;
	}
	
	public ArchiveList loadArchiveList() throws ClassNotFoundException, IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		String archiveListData = settings.getString(archiveKey, "");
		if (archiveListData.equals("")) {
			return new ArchiveList();
		} else {
			return archiveListFromString(archiveListData);
		}
	}
	
	public void saveArchiveList(ArchiveList archive) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(archiveKey, archiveListToString(archive));
		editor.commit();
	}
	
	private String archiveListToString(ArchiveList archive) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(archive);
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	private ArchiveList archiveListFromString(String archiveListData) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(archiveListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ArchiveList) oi.readObject();
	}
}*/
