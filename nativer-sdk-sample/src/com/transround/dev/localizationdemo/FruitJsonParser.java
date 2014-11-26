package com.transround.dev.localizationdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FruitJsonParser implements FiguresJsonParser<Fruit> {

	@Override
	public List<Fruit> parseJsonStream(InputStream jsonStream)
			throws IOException, JSONException {

		List<Fruit> fruitResultList = new ArrayList<Fruit>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				jsonStream, "UTF-8"), 8);

		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}

		String jsonString = sb.toString();

		JSONObject jsonObject = new JSONObject(jsonString);

		JSONArray fruits = jsonObject.getJSONArray("fruits");
		for (int i = 0; i < fruits.length(); i++) {
			JSONObject jsonFruitElement = fruits.getJSONObject(i);
			Fruit newFruit = new Fruit();
			newFruit.setName(jsonFruitElement.getString("name"));
			newFruit.setCalories(jsonFruitElement.getDouble("calories"));
			newFruit.setProtein(jsonFruitElement.getDouble("protein"));
			newFruit.setCarbohydrate(jsonFruitElement.getDouble("carbohydrate"));
			newFruit.setFibre(jsonFruitElement.getDouble("fibre"));
			newFruit.setDailyValue(jsonFruitElement.getDouble("dailyValue"));
			fruitResultList.add(newFruit);
		}
		return fruitResultList;
	}

}
