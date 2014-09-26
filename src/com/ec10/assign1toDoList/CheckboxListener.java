package com.ec10.assign1toDoList;
/**
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CheckboxListener extends ArrayAdapter<toDoItem> {

	protected final ArrayList<toDoItem> list;
	protected final Activity context;
	
	public CheckboxListener(Activity context, ArrayList<toDoItem> list) {
		super(context, R.layout.check, list);
		this.context = context;
		this.list = list;
	}
	
	public CheckboxListener getCheckboxListener() {
		return this;
	}
	
	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View checks = null;
		if (view == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			checks = inflater.inflate(R.layout.check, null);
			final ViewHolder holder = new ViewHolder();
			holder.text = (TextView) checks.findViewById(R.id.label);
			holder.checkbox = (CheckBox) checks.findViewById(R.id.checkbox);
			holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					toDoItem item = (toDoItem) holder.checkbox.getTag();
					item.setSelected(buttonView.isChecked());
				}
			});
			checks.setTag(holder);
			holder.checkbox.setTag(list.get(position));
		} else {
			checks = view;
			((ViewHolder) checks.getTag()).checkbox.setTag(list.get(position));
		}	
		final ViewHolder holder2 = (ViewHolder) checks.getTag();
		holder2.text.setText(list.get(position).toString());
		holder2.checkbox.setChecked(list.get(position).isSelected());
		holder2.text.setClickable(true);
		holder2.text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toDoItem item = (toDoItem) holder2.checkbox.getTag();
				if (holder2.checkbox.isChecked()) {
					holder2.checkbox.setChecked(false);
					item.setSelected(false);
				} else{
					holder2.checkbox.setChecked(true);;
					item.setSelected(holder2.checkbox.isChecked());
				}
			}
		});
		return checks;
	}
}*/
