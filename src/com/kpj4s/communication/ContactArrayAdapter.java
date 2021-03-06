package com.kpj4s.communication;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filterable;
import android.widget.TextView;

import com.kpj4s.way2sms.R;

public class ContactArrayAdapter extends ArrayAdapter<Model> implements
		Filterable {

	private final Activity context;
	private final int resourceId;

	public ContactArrayAdapter(Activity context, int resourceId,
			List<Model> list) {
		super(context, resourceId, list);
		this.context = context;
		this.resourceId = resourceId;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(resourceId, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Model element = (Model) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());

						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(getItem(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(getItem(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(getItem(position).getName());
		holder.checkbox.setChecked(getItem(position).isSelected());
		return view;
	}
}