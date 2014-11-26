package com.transround.dev.localizationdemo;

import android.content.Context;

public class FruitHelper {
	public static final String TAG = "FruitHelper";
	private static final String sFruitNamePrefix = "fruit_display_name_";

	public static CharSequence getFruitDisplayName(Context context,
			String fruitName) {
		final int fruitDisplayNameId = context.getResources().getIdentifier(
				sFruitNamePrefix + fruitName, "string",
				context.getPackageName());

		if (fruitDisplayNameId > 0) {
			return context.getResources().getString(fruitDisplayNameId);
		} else {
			return "UnknownFruit(" + fruitName + ")";
		}	
	}
	
	public static CharSequence getFruitDisplayNameByPosition(Context context, int position){
		return context.getResources().getStringArray(R.array.localization_demo_fruit_array)[position];
	}
}
