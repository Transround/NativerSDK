package com.transround.dev.localizationdemo;

import android.content.Context;

public class EmailTextBuilder {
	public static String buildFruitEmailText(Context context, Fruit fruit) {
		StringBuffer sb = new StringBuffer();
		sb.append(context.getResources().getString(R.string.localization_demo_label_fruit));
		sb.append("\t\t");
		sb.append(fruit.getName());
		sb.append("\n");
		sb.append(context.getResources().getString(R.string.localization_demo_label_calories));
		sb.append("\t\t");
		sb.append(fruit.getCalories());
		sb.append("\n");
		sb.append(context.getResources().getString(R.string.localization_demo_label_protein));
		sb.append("\t\t");
		sb.append(fruit.getProtein());
		sb.append("\n");
		sb.append(context.getResources().getString(R.string.localization_demo_label_carbohydrate));
		sb.append("\t\t");
		sb.append(fruit.getCarbohydrate());
		sb.append("\n");
		sb.append(context.getResources().getString(R.string.localization_demo_label_fibre));
		sb.append("\t\t");
		sb.append(fruit.getFibre());
		sb.append("\n");
		sb.append(context.getResources().getString(R.string.localization_demo_label_daily_value));
		sb.append("\t\t");
		sb.append(fruit.getDailyValue());
		sb.append("\n\n");
		sb.append("Experience how easy to translate and test an app using an Android phone like never before. Try Nativer free localization service to get it to more users !");
		sb.append("\n");
		sb.append("Visit us at: ");
		sb.append(context.getResources().getString(R.string.localization_demo_transround_link));
		return sb.toString();
	}
}
