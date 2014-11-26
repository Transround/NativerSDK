package com.transround.dev.localizationdemo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FruitDetailListAdapter extends ArrayAdapter<DetailListItem> {

	Context mContext;
	int mLayoutResourceId;

	public FruitDetailListAdapter(Context context, Fruit fruit) {
		super(context, R.layout.custom_list_item, getFruitDetailList(context,
				fruit));
		this.mLayoutResourceId = R.layout.custom_list_item;
		this.mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(mLayoutResourceId, parent, false);
		}

		DetailListItem item = getItem(position);

		TextView textViewTitle = (TextView) convertView
				.findViewById(R.id.itemTitle);
		textViewTitle.setText(item.getTitle());

		TextView textViewValue = (TextView) convertView
				.findViewById(R.id.itemValue);
		textViewValue.setText(item.getValue());
		return convertView;
	}

	private static List<DetailListItem> getFruitDetailList(Context context,
			Fruit fruit) {
		List<DetailListItem> languageDetailListItems = new ArrayList<DetailListItem>();
		NumberFormat formatter = NumberFormat.getInstance();
		languageDetailListItems.add(new DetailListItem(context.getResources()
				.getString(R.string.localization_demo_label_calories), String.valueOf(formatter
				.format(fruit.getCalories()))));

		languageDetailListItems.add(new DetailListItem(context.getResources()
				.getString(R.string.localization_demo_label_protein), String.valueOf(formatter
				.format(fruit.getProtein()))));

		languageDetailListItems.add(new DetailListItem(context.getResources()
				.getString(R.string.localization_demo_label_carbohydrate), String
				.valueOf(formatter.format(fruit.getCarbohydrate()))));

		languageDetailListItems.add(new DetailListItem(context.getResources()
				.getString(R.string.localization_demo_label_fibre), String.valueOf(formatter
				.format(fruit.getFibre()))));

		languageDetailListItems.add(new DetailListItem(context.getResources()
				.getString(R.string.localization_demo_label_daily_value), String.valueOf(fruit
				.getDailyValue() + "%")));

		return languageDetailListItems;
	}

}
